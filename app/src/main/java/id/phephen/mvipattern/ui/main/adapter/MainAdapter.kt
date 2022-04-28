package id.phephen.mvipattern.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.phephen.mvipattern.R
import id.phephen.mvipattern.data.model.User

/**
 * Created by Phephen on 27/04/2022.
 */
class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            val textViewUser = itemView.findViewById<TextView>(R.id.textViewUserName)
            val textViewEmail = itemView.findViewById<TextView>(R.id.textViewUserEmail)
            val imageView = itemView.findViewById<ImageView>(R.id.imageViewAvatar)
            textViewUser.text = user.name
            textViewEmail.text = user.email
            Glide.with(itemView.context)
                .load(user.avatar)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent, false
            )
        )


    override fun onBindViewHolder(holder: MainAdapter.DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>) {
        users.addAll(list)
    }


}