package dev.vladimir.mobinttestproject.presentation.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.vladimir.mobinttestproject.databinding.ItemCardBinding
import dev.vladimir.mobinttestproject.domain.models.Company

class CardsAdapter(
    private val onEyeClick: (companyId: String) -> Unit,
    private val onTrashClick: (companyId: String) -> Unit,
    private val onMoreClick: (companyId: String) -> Unit,
) : PagingDataAdapter<Company, CardsAdapter.Holder>(CardsDiffUtilCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val company = getItem(position) ?: return
        val companyId = company.id

        with(holder.binding) {
            this.company = company

            cardCompanyEyeIv.setOnClickListener { onEyeClick(companyId) }
            cardCompanyTrashIv.setOnClickListener { onTrashClick(companyId) }
            cardCompanyMoreButton.setOnClickListener { onMoreClick(companyId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    inner class Holder(internal val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        @JvmStatic
        @BindingAdapter("logoImage")
        fun loadImage(view: ImageView, logoUrl: String) {
            Glide.with(view.context)
                .load(logoUrl)
                .circleCrop()
                .into(view)
        }
    }
}

class CardsDiffUtilCallback : DiffUtil.ItemCallback<Company>() {

    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem == newItem
    }
}
