package dev.vladimir.mobinttestproject.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.vladimir.mobinttestproject.databinding.ViewLoaderBinding

class DefaultLoadStateAdapter(
    private val onError: (throwable: Throwable) -> Unit
) : LoadStateAdapter<DefaultLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.binding.loaderContainerLl.isVisible = loadState is LoadState.Loading
        if (loadState is LoadState.Error) onError(loadState.error)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewLoaderBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    inner class Holder(internal val binding: ViewLoaderBinding) :
        RecyclerView.ViewHolder(binding.root)
}
