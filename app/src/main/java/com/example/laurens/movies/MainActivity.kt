package com.example.laurens.movies

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSearchButtonClick(v: View) {
        val editText = findViewById(R.id.show_search_input) as EditText
        val showName = editText.text.toString()
        searchShow(showName)
    }

    private fun searchShow(showName: String) {
        val intent = Intent(this, ShowListActivity::class.java)
        intent.putExtra(SHOW_NAME_KEY, showName)
        startActivity(intent)
    }

    companion object {

        val SHOW_NAME_KEY = "com.example.laurens.movies.SHOW_NAME"
    }

}
