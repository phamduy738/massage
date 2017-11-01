package com.uni.phamduy.massagefinder.ui.Place

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uni.phamduy.massagefinder.MainActivity
import com.uni.phamduy.massagefinder.MoveScreen
import com.uni.phamduy.massagefinder.R
import com.uni.phamduy.massagefinder.adapter.ListWardAdapter
import com.uni.phamduy.massagefinder.adapter.SearchAdapter
import java.util.*






/**
 * Created by PhamDuy on 9/13/2017.
 */
class WardFragment : Fragment(), View.OnClickListener {


    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.imgFilter -> {
                    if (MainActivity.instance.rvSearch.visibility == View.GONE) {
                        MainActivity.instance.rvSearch.visibility = View.VISIBLE
                        isShow = false
                    } else {

                        MainActivity.instance.rvSearch.visibility = View.GONE

                        isShow = true
                    }
                }

            }
        }
    }

    private lateinit var rvPlace: RecyclerView
    private var storeAdapter: ListWardAdapter? = null
    private var listPlace: MutableList<String> = ArrayList()
    private var searchAdapter: SearchAdapter? = null
    private var listFilter: MutableList<String> = ArrayList()
    private var moveScreen: MoveScreen? = null
    val offset = 10
    var isShow: Boolean = true


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_ward, container, false)
        rvPlace = view!!.findViewById(R.id.rvPlace)
        setHeader()
        init()
        showFilter()
        MainActivity.instance.imgFilter.setOnClickListener(this)

        return view
    }

    fun init() {
        listPlace.clear()
        addList()
        storeAdapter = ListWardAdapter(activity, listPlace)
        val gridlayout = GridLayoutManager(activity, 2)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPlace.adapter = storeAdapter
        rvPlace.layoutManager = gridlayout
    }

    fun setHeader() {
        MainActivity.instance.imgFilter.visibility = View.VISIBLE
        MainActivity.instance.search_layout.visibility = View.VISIBLE
        MainActivity.instance.tv_title.visibility = View.GONE
        MainActivity.instance.chooseBottomView(2)
    }

    fun addList() {
        val ward_array = activity.resources.getStringArray(R.array.ward)
        var list2: List<String> = ArrayList<String>()
        list2 = ward_array.toList()
        listPlace.addAll(list2)
//        for (i in 0..ward_array.size) {
//            listPlace.add(ward_array[i])
//        }
    }

    private fun showFilter() {

        listFilter.clear()
        listFilter.add("Thành Phố")
//        listFilter.add("Rating")
//        listFilter.add("Khoảng cách")
        listFilter.add("Sort")
        //set RecyclerView
        searchAdapter = SearchAdapter(activity, listFilter)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        MainActivity.instance.rvSearch.adapter = searchAdapter
        MainActivity.instance.rvSearch.layoutManager = gridLayoutManager
        searchAdapter!!.setOnItemClickListener(object : SearchAdapter.ClickListener {
            override fun onItemClick(position: Int, v: View?) {
                when(position){
                    0->{
                        val fm = fragmentManager
                        val wardDialog = WardDialogFragment()
                        wardDialog.retainInstance = true
                        wardDialog.show(fm, "fragment_ward")
                    }
                    1->{
                        val fm = fragmentManager
                        val sortDialog = SortDialogFragment()
                        sortDialog.retainInstance = true
                        sortDialog.show(fm, "fragment_sort")
                    }
                }


            }
        })


    }

    override fun onPause() {
        super.onPause()
        MainActivity.instance.rvSearch.visibility = View.GONE
    }

}