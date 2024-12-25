package com.example.aston_intensiv_4.task2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_intensiv_4.databinding.UserItemBinding

class UsersAdapter(private val onUserClick: (User) -> Unit) :
    ListAdapter<User, UsersAdapter.UsersViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {

            userImg.setImageResource(item.photo)
            userName.text = item.firstName
            userLastname.text = item.lastName
            userNumber.text = item.phoneNumber

            root.setOnClickListener { onUserClick(item) }


        }


    }


    class UsersViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    class DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

}