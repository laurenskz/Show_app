package com.example.laurens.movies

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

import com.google.gson.Gson

import java.io.IOException
import java.net.URL

import com.example.laurens.movies.MainActivity.Companion.SHOW_NAME_KEY
import kotlinx.android.synthetic.main.activity_showlist.*

/**
 * Created by Laurens on 13-9-2017.
 */
class ShowListActivity : AppCompatActivity() {
    private var showName: String? = null
    private var listView: ListView? = null
    private lateinit var showAdapter: ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showlist)
        showName = intent.getStringExtra(SHOW_NAME_KEY)
        ShowSearchTask().execute(requestPath)
        show_list_view.setOnItemClickListener(this::onItemClick)
    }

    private val requestPath: String
        get() = BASE_QUERY.buildUpon()
                .appendQueryParameter("q", showName)
                .build()
                .toString()

    fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long): Unit {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SHOW_DETAILS_KEY, showAdapter.getShow(i))
        startActivity(intent)
    }

    private inner class ShowSearchTask : AsyncTask<String, Void, ShowAdapter>() {

        override fun doInBackground(vararg urls: String): ShowAdapter? {
            try {
                val content = URL(urls[0]).readText()
                val shows = Gson().fromJson<Array<ShowAdapter.ShowWithScore>>(content, Array<ShowAdapter.ShowWithScore>::class.java)
                return ShowAdapter(shows.toList())
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(showAdapter: ShowAdapter?) {
            if (showAdapter == null) {
                Toast.makeText(this@ShowListActivity, "Loading shows failed", Toast.LENGTH_LONG)
                        .show()
                return
            }
            this@ShowListActivity.showAdapter = showAdapter
            listView!!.adapter = showAdapter
        }
    }

    companion object {


        val SHOW_DETAILS_KEY = "com.example.laurens.movies.showDetails"
        val BASE_QUERY = Uri.Builder()
                .scheme("http")
                .authority("api.tvmaze.com")
                .appendPath("search")
                .appendPath("shows")
                .build()
    }
}
