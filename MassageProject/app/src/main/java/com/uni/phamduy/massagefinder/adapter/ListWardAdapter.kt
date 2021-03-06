package com.uni.phamduy.massagefinder.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.uni.phamduy.massagefinder.MoveScreen
import com.uni.phamduy.massagefinder.R
import com.uni.phamduy.massagefinder.ui.Place.PlaceFragment
import java.util.*

/**
 * Created by PhamDuy on 9/13/2017.
 */
class ListWardAdapter(internal var context: Context, internal var list: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val RANDOM = Random()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        return MyHolder(inflater.inflate(R.layout.cell_list_ward, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myHolder = holder as MyHolder
        myHolder.tvStoreName.text = list[position]
        Glide.with(context).load(getRandomCheeseDrawable()).into(myHolder. thumbnail)

    }
    fun getRandomCheeseDrawable(): Int {
        when (RANDOM.nextInt(5)) {
            0 -> return R.drawable.q1
            1 -> return R.drawable.q2
            2 -> return R.drawable.q3
            3 -> return R.drawable.q4
            4 -> return R.drawable.q5
            5 -> return R.drawable.q6
            6 -> return R.drawable.q1
            7 -> return R.drawable.q2
            8 -> return R.drawable.q3
            9 -> return R.drawable.q4
            10 -> return R.drawable.q5
            11 -> return R.drawable.q6
            12 -> return R.drawable.q7
        }
        return 0
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
          var tvStoreName: TextView
         var cardView: CardView
        var thumbnail: ImageView
        private var moveScreen: MoveScreen? = null
        init {
            thumbnail= itemView.findViewById(R.id.thumbnail)
            tvStoreName = itemView.findViewById<TextView>(R.id.tvStoreName)
            cardView = itemView.findViewById(R.id.cardView)
            itemView.setOnClickListener {
                moveScreen = MoveScreen(context)
                moveScreen!!.clickedOn(R.id.content, PlaceFragment())
            }
        }
    }
}
