package dev.vladimir.mobinttestproject.presentation.cards

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.mobinttestproject.R
import dev.vladimir.mobinttestproject.data.common.extensions.observe
import dev.vladimir.mobinttestproject.databinding.FragmentCardsBinding
import dev.vladimir.mobinttestproject.presentation.adapters.DefaultLoadStateAdapter

@AndroidEntryPoint
class CardsFragment : Fragment(R.layout.fragment_cards) {

    private val viewModel by viewModels<CardsViewModel>()

    private lateinit var binding: FragmentCardsBinding

    private lateinit var cardsAdapter: CardsAdapter
    private lateinit var cardsFooterAdapter: DefaultLoadStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCardsBinding.bind(view)

        initRecycler()
        observeViewModel()
    }

    private fun initRecycler() {
        cardsAdapter = CardsAdapter(
            onEyeClick = { showCompanyInfo(getString(R.string.eye_button_name), it) },
            onTrashClick = { showCompanyInfo(getString(R.string.trash_button_name), it) },
            onMoreClick = { showCompanyInfo(getString(R.string.more_button_name), it) }
        )

        cardsFooterAdapter = DefaultLoadStateAdapter(viewModel::handleError)

        binding.cardsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardsAdapter.withLoadStateFooter(cardsFooterAdapter)
        }

        listenLoadState()
    }

    private fun listenLoadState() {
        cardsAdapter.loadStateFlow.observe(this){
            with(binding) {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        loadStateView.loaderContainerLl.isVisible = true
                    }
                    is LoadState.Error -> {
                        loadStateView.loaderContainerLl.isVisible = false
                        viewModel.handleError((it.refresh as LoadState.Error).error)
                    }
                    is LoadState.NotLoading -> {
                        loadStateView.loaderContainerLl.isVisible = false
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.companiesState.observe(this) {
            cardsAdapter.submitData(lifecycle, it)
        }
        viewModel.errorEvent.observe(this) {
            showErrorDialog(it)
        }
    }

    private fun showCompanyInfo(buttonName: String, companyId: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.company_info_dialog_message, buttonName, companyId))
            .setPositiveButton(R.string.company_info_dialog_close_text, null)
            .show()
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(R.string.company_info_dialog_close_text, null)
            .show()
    }
}
