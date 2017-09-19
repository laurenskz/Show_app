package com.example.laurens.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.example.laurens.movies.ShowAdapter.ShowWithScore
import com.example.laurens.movies.ShowListActivity.Companion.SHOW_DETAILS_KEY
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Laurens on 13-9-2017.
 */
class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val showWithScore = intent.getSerializableExtra(SHOW_DETAILS_KEY) as ShowWithScore
        detail_text.text = toDetails(showWithScore)
        detail_text.textSize = 25f
    }

    private fun toDetails(show: ShowWithScore): String {
        val actualShow = show.show
        return getString(R.string.details_string,
                actualShow!!.name,
                actualShow.language,
                actualShow.genres!!.toString(),
                actualShow.status
        )
    }


}
