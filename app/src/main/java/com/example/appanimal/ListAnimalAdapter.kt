package com.example.appanimal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListAnimalAdapter(private val listAnimal: ArrayList<Animal>) : RecyclerView.Adapter<ListAnimalAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_animal, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, category, food, heavy, areaDistribution) = listAnimal[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.tvCategory.text = category
        holder.tvFood.text = food
        holder.tvHeavy.text = heavy
        holder.tvAreaDistribution.text = areaDistribution
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAnimal[holder.adapterPosition]) }

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_animal", listAnimal[position])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listAnimal.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvCategory: TextView = itemView.findViewById(R.id.tv_item_category)
        val tvFood: TextView = itemView.findViewById(R.id.tv_item_food)
        val tvHeavy: TextView = itemView.findViewById(R.id.tv_item_heavy)
        val tvAreaDistribution: TextView = itemView.findViewById(R.id.tv_item_area_distribution)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Animal)
    }
}