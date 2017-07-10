package com.joshlefler.usconstitution


import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

import java.io.BufferedReader
import java.io.InputStreamReader

class Constitution {
    fun parseConstitution(context : Context): ArrayList<Node> {
        val inStream = context.assets.open("constitution.xml")
        val inBuffered = BufferedReader(InputStreamReader(inStream))

        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(inBuffered)
        var eventType = parser.eventType

        val parts = ArrayList<Node>()
        var currentPart : Node? = null
        var currentParagraph : Paragraph? = null
        var currentArticle : Node? = null
        var currentSection : Node?
        var currentParent : Node? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    when (parser.name) {
                    "part" -> {
                        currentPart = Node(parser.getAttributeValue(null, "description"), parser.getAttributeValue(null, "id"), parser.getAttributeValue(null, "type"))
                        currentParent = currentPart
                        parts.add(currentPart)
                    } "article" -> {
                        currentArticle = Node(parser.getAttributeValue(null, "description"), parser.getAttributeValue(null, "id"), parser.getAttributeValue(null, "type"), parser.getAttributeValue(null, "name"))
                        currentPart!!.articles.add(currentArticle)
                    }  "section" -> {
                        currentSection = Node(parser.getAttributeValue(null, "description"), parser.getAttributeValue(null, "id"), parser.getAttributeValue(null, "type"), parser.getAttributeValue(null, "name"))
                        currentArticle!!.sections.add(currentSection)
                        currentParent = currentSection
                    } "paragraph" -> {
                        currentParagraph = Paragraph(parser.getAttributeValue(null, "description"), parser.getAttributeValue(null, "id"), parser.getAttributeValue(null, "type"), parser.getAttributeValue(null, "name"))
                        currentParent!!.paragraphs.add(currentParagraph)
                    } "content" -> {
                        parser.next() //Advance to actual text.
                        currentParagraph!!.content = parser.text
                    } else -> {
                        }
                    }
                }
                else -> {
                }
            }
            eventType = parser.next()
        }


        return parts
    }

}

data class Node(val description : String, val id: String, val type: String, val name : String = "") {
    val paragraphs = ArrayList<Paragraph>()
    val articles = ArrayList<Node>()
    val sections = ArrayList<Node>()

    //override fun toString() : String = description

}

data class Paragraph(val description : String, val id : String, val name : String, val type : String) {
    var content = ""
}