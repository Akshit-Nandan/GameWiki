package com.example.gamewiki.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamewiki.R
import com.example.gamewiki.model.InfoNode
import org.w3c.dom.Text

interface GameCardClicked {
    fun  onItemClicked(id : Int)
}

class GameListAdapter(private val listener : GameCardClicked) : RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {

    private var gameInfoList = emptyList<InfoNode>()

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var thumbnail = itemView.findViewById<ImageView>(R.id.gameIcon)
        var title = itemView.findViewById<TextView>(R.id.gameTitle)
        var categ = itemView.findViewById<TextView>(R.id.category)
        var gameId = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view =  (layoutInflater.inflate(R.layout.game_card,parent,false))


        return GameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gameInfoList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.title.text = gameInfoList[position].title
        holder.categ.text = gameInfoList[position].genre
        Glide.with(holder.itemView.context)
            .load(gameInfoList[position].thumbnail)
            .into(holder.thumbnail)

        holder.itemView.setOnClickListener{
            gameInfoList[position].id?.let { it1 -> listener.onItemClicked(it1) }
        }
    }
    fun updateList(list : List<InfoNode>){
        gameInfoList = list
        notifyDataSetChanged()
    }
}