package com.joshlefler.usconstitution

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by leflers on 6/3/17.
 */

class MainListFragment : ListFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val parts = Constitution().parseConstitution(context)


        listView.adapter =  ArrayAdapter<Node>(activity.baseContext, android.R.layout.simple_list_item_1, parts)
        setListShown(true)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
    }
}