package com.example.laurens.movies

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import java.io.Serializable
import java.util.Objects

/**
 * Created by Laurens on 13-9-2017.
 */
class ShowAdapter(private val shows: List<ShowWithScore>) : BaseAdapter() {

    override fun getCount() = shows.size

    override fun getItem(i: Int) = getShow(i)

    override fun getItemId(i: Int) = shows[i].show.id.toLong()

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val textView = view as? TextView
                ?: TextView(viewGroup.context).apply { textSize = 25f }
        return textView.apply {
            text = shows[i].show.name
        }
    }

    fun getShow(index: Int) = shows[index]


    class ShowWithScore : Serializable {
        var score: Double = 0.toDouble()
        lateinit var show: Show
    }

    class Show : Serializable {
        var id: Int = 0
        lateinit var name: String
        lateinit var language: String
        lateinit var genres: Collection<String>
        lateinit var status: String
    }

}
