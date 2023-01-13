package dev.vladimir.mobinttestproject.presentation.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.vladimir.mobinttestproject.databinding.ItemCardBinding
import dev.vladimir.mobinttestproject.domain.models.Company

class CardsAdapter : PagingDataAdapter<Company, CardsAdapter.Holder>(CardsDiffUtilCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.company = getItem(position) ?: return
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    inner class Holder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)
}

class CardsDiffUtilCallback : DiffUtil.ItemCallback<Company>() {

    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }
}
