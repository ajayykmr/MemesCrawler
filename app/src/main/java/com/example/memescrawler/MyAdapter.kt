package com.example.memescrawler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private val data: MyDataItem): RecyclerView.Adapter<MyViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view,parent, false)
        return MyViewholder(view)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {

        if (data.memes[position].nsfw) {
            holder.title.text = "NSFW Memes are Filtered"
            holder.image.setImageResource(R.color.black)
        }
        else
        {
            holder.title.text = data.memes[position].title
            Picasso.get().load(data.memes[position].url).into(holder.image)
        }
    }
    override fun getItemCount(): Int {
        return data.memes.size
    }
}

class MyViewholder(itemview: View): RecyclerView.ViewHolder(itemview){
    var title: TextView = itemview.findViewById(R.id.tv_title)
    var image: ImageView = itemview.findViewById(R.id.iv_photo)

}