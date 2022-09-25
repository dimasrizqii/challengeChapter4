package com.example.challenge4.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge4.data.local.database.entity.FriendshipEntity
import com.example.challenge4.databinding.ItemMenuBinding

class HomeAdapter(private val listener: HomeItemClickListener) : RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder>() {

    private var items: MutableList<FriendshipEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val view = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HomeItemViewHolder(var binding: ItemMenuBinding, private val listener: HomeItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: FriendshipEntity) {
            with(item) {
                binding.tvFriendName.text = friendName
                binding.tvFriendStatus.text = friendshipStatus
                binding.ivDelete.setOnClickListener{ listener.onDeleteMenuClicked(item) }
            }

        }
    }

    fun setItems(items: List<FriendshipEntity>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<FriendshipEntity>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun deleteItems(item: FriendshipEntity){
        this.items.remove(item)
        notifyDataSetChanged()
    }

}

interface HomeItemClickListener {
    fun onItemClicked(item: FriendshipEntity)
    fun onItemLongClicked(item: FriendshipEntity)
    fun onDeleteMenuClicked(item: FriendshipEntity)
    fun onEditMenuClicked(item: FriendshipEntity)
}