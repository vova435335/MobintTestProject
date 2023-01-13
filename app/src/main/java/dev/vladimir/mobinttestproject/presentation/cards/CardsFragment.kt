package dev.vladimir.mobinttestproject.presentation.cards

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCardsBinding.bind(view)

        initRecycler()
        observeViewModel()
    }

    private fun initRecycler() {
        cardsAdapter = CardsAdapter()

        val footerAdapter = DefaultLoadStateAdapter()

        binding.cardsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardsAdapter.withLoadStateFooter(footerAdapter)
        }

        listenLoadState()
    }

    private fun listenLoadState() {
        cardsAdapter.addLoadStateListener {
            binding.loadStateView.loaderContainerLl.isVisible = it.refresh is LoadState.Loading
        }
    }


    private fun observeViewModel() {
        viewModel.companiesState.observe(this) {
            cardsAdapter.submitData(lifecycle, it)
        }
    }
}
