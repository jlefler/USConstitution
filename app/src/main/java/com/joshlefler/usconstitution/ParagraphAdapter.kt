package com.joshlefler.usconstitution

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by leflers on 7/15/17.
 */
class ParagraphAdapter(newNode : Node) : RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>() {
    var node : Node
    init {
        node = newNode
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParagraphViewHolder {
        var cv = LayoutInflater.from(parent!!.context).inflate(R.layout.card, parent, false) as ViewGroup
        var viewHolder = ParagraphViewHolder(cv)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return node.paragraphs.size
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder?, position: Int) {
        if (holder != null) {
            holder.contentView.setText(node.paragraphs[position].content)
            if (position == 0) {
                holder.name.setText(node.toString())
                holder.name.visibility = View.VISIBLE
            } else {
                holder.name.visibility = View.GONE
            }

        }
    }

    class ParagraphViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup) {
        var name : TextView
        var contentView : TextView
        init {
            name = viewGroup.findViewById(R.id.paragraph_name) as TextView
            contentView = viewGroup.findViewById(R.id.paragraph_content) as TextView
        }
    }
}