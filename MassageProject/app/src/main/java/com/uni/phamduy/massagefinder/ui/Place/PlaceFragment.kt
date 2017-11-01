package com.uni.phamduy.massagefinder.ui.Place

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uni.phamduy.massagefinder.MainActivity
import com.uni.phamduy.massagefinder.MoveScreen
import com.uni.phamduy.massagefinder.R
import com.uni.phamduy.massagefinder.adapter.PlaceAdapter
import com.uni.phamduy.massagefinder.ui.PlaceDetailActivity
import com.uni.phamduy.massagefinder.ui.Staff.StaffFragment
import java.util.*



/**
 * Created by PhamDuy on 9/13/2017.
 */
class PlaceFragment : Fragment() {



    private var moveScreen: MoveScreen? = null
    private lateinit var rvPlace: RecyclerView
    private var storeAdapter: PlaceAdapter? = null
    private var listPlace: MutableList<String> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_place, container, false)
        rvPlace = view!!.findViewById(R.id.rvPlace)
        init()

        return view
    }

    fun init() {
        listPlace.clear()
        addList()
        storeAdapter = PlaceAdapter(activity, listPlace)
//        val gridlayout = GridLayoutManager(activity, 2)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPlace.adapter = storeAdapter
        rvPlace.layoutManager = linearLayoutManager
        storeAdapter!!.setOnItemClickListener(object : PlaceAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View) {
                val intent = Intent(activity, PlaceDetailActivity::class.java)
                intent.putExtra("abc", "abc")
               startActivityForResult(intent,1)
            }

        })
    }



    fun addList() {
        for (i in 0..20) {
            listPlace.add(i.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1&& resultCode== Activity.RESULT_OK){
            val result = data!!.getStringExtra("result")
            moveScreen = MoveScreen(activity)
            moveScreen!!.clickedOn(R.id.content, StaffFragment())
            MainActivity.instance.chooseBottomView(3)
        }
    }


}