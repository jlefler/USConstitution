package com.joshlefler.usconstitution

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * Created by leflers on 6/3/17.
 */

class MainListFragment : ListFragment() {
    private var nodes = ArrayList<Node>()
    private var nodeType = "US Constitution"

    init {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            nodes = arguments.get("nodes") as ArrayList<Node>
            nodeType = arguments.getString("nodeType")
        }
    }

    override fun onResume() {
        super.onResume()
        if (nodes.size == 0) {
            nodes = Constitution().parseConstitution(context)
        }
        if (nodeType.length > 0) {
            activity.title = nodeType
        }


        listView.adapter =  ArrayAdapter<Node>(activity.baseContext, android.R.layout.simple_list_item_1, nodes)
        setListShown(true)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val node = nodes[position]
        if (node.paragraphs.size > 0) {
            val paragraphView = ParagraphFragment()
            val args = Bundle()
            args.putSerializable("node", node)
            paragraphView.arguments = args
            fragmentManager.beginTransaction().replace(R.id.mainFrame, paragraphView).addToBackStack(nodeType).commit()
        } else {
            val newList = MainListFragment()
            val args = Bundle()
            args.putString("nodeType", node.toString())
            if (node.amendments.size > 0) {
                args.putSerializable("nodes", node.amendments)
            } else if (node.articles.size > 0) {
                args.putSerializable("nodes", node.articles)
            } else if (node.sections.size > 0) {
                args.putSerializable("nodes", node.sections)
            }
            newList.arguments = args

            fragmentManager.beginTransaction().replace(R.id.mainFrame, newList).addToBackStack(nodeType).commit()
    }
    }
}