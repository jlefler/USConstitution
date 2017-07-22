package com.joshlefler.usconstitution


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by leflers on 7/15/17.
 */


class ParagraphFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Not smart to assume it's not null, but we can't really do anything useful if it isn't there anyway...
        return inflater!!.inflate(R.layout.fragment_paragraph, container, false)
    }

    override fun onResume() {
        val node = arguments.get("node") as Node
        super.onResume()
        val paragraphList = view as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        paragraphList.layoutManager = layoutManager
        val paragraphAdapter = ParagraphAdapter(node)
        paragraphList.adapter = paragraphAdapter
        activity.title = node.toString()


    }
}