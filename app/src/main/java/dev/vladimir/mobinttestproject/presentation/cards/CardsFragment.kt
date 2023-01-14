package dev.vladimir.mobinttestproject.presentation.cards

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.vladimir.mobinttestproject.R
import dev.vladimir.mobinttestproject.data.common.extensions.observe
import dev.vladimir.mobinttestproject.databinding.FragmentCardsBinding
import dev.vladimir.mobinttestproject.presentation.adapters.DefaultLoadStateAdapter
import dev.vladimir.mobinttestproject.presentation.base.BaseFragment

@AndroidEntryPoint
class CardsFragment : BaseFragment<FragmentCardsBinding>() {

    private val viewModel by viewModels<CardsViewModel>()

    private lateinit var cardsAdapter: CardsAdapter
    private lateinit var cardsFooterAdapter: DefaultLoadStateAdapter

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCardsBinding = FragmentCardsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        observeViewModel()
    }

    private fun initRecycler() {
        cardsAdapter = CardsAdapter(
            onEyeClick = { viewModel.showCompanyInfo(R.string.eye_button_name, it) },
            onTrashClick = { viewModel.showCompanyInfo(R.string.trash_button_name, it) },
            onMoreClick = { viewModel.showCompanyInfo(R.string.more_button_name, it) },
        )

        cardsFooterAdapter = DefaultLoadStateAdapter {
            viewModel.handleError(it)
        }

        binding.cardsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardsAdapter.withLoadStateFooter(cardsFooterAdapter)
        }

        observeLoadState()
    }

    private fun observeLoadState() {
        cardsAdapter.loadStateFlow.observe(this) {
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
        viewModel.showCompanyInfoEvent.observe(this) {
            showCompanyInfo(it)
        }
    }

    private fun showCompanyInfo(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(R.string.company_info_dialog_close_text, null)
            .show()
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton(R.string.company_info_dialog_close_text, null)
            .setPositiveButton(R.string.company_info_dialog_retry_text) { _, _ ->
                cardsAdapter.retry()
            }
            .show()
    }
}
