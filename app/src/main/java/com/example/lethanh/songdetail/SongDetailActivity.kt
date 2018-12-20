package com.example.lethanh.songdetail

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.lethanh.songdetail.content.SongUtils

class SongDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)


        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar);

       val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        if(savedInstanceState == null){
            val selectedSong = getIntent().getIntExtra(SongUtils.SONG_ID_KEY, 0 )
            val fragment = SongDetailFragment().newInstance(selectedSong)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.song_detail_container, fragment)
                    .commit();
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id= item!!.getItemId()
        if (id == android.R.id.home){
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
