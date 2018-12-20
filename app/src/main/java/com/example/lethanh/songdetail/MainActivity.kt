package com.example.lethanh.songdetail

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.lethanh.songdetail.content.SongUtils
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup


class MainActivity : AppCompatActivity() {

    internal lateinit var sharedpreferences: SharedPreferences
    val mypreference = "mypref"
    val Song = "Song"
    var mTwoPane = false
    var selectedSong: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<TextView>(R.id.song_detail_container) != null) {
            mTwoPane = true;
        }

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE)

        if (mTwoPane) {

            if (sharedpreferences.contains(Song))
                selectedSong = (sharedpreferences.getInt(Song, 0))
            /*selectedSong = holder.adapterPosition*/
            val fragment = SongDetailFragment().newInstance(selectedSong!!)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.song_detail_container, fragment)
                    .addToBackStack(null)
                    .commit()

        }
        // Set the toolbar as the app bar.
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle())

        // Get the song list as a RecyclerView.
        val recyclerView = findViewById<View>(R.id.song_list) as RecyclerView
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS)

    }


    internal inner class SimpleItemRecyclerViewAdapter(private val mValues: List<SongUtils.Song>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.song_list_content, parent, false)
            return ViewHolder(view)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.mItem = mValues[position]
            holder.mIdView.text = (position + 1).toString()
            holder.mContentView.text = mValues[position].song_title
            if (mValues[position].selected) {
                holder.mView.setBackgroundColor(Color.BLUE)

            } else {
                holder.mView.setBackgroundColor(Color.WHITE)
            }

            holder.mView.setOnClickListener { v ->
                mValues.forEachIndexed { index, song -> if(index == holder.layoutPosition) song.selected = true else song.selected = false }

                if (mTwoPane) {
                    var selectedSong: Int? = null
                    selectedSong = holder.adapterPosition
                    val fragment = SongDetailFragment().newInstance(selectedSong!!)
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.song_detail_container, fragment)
                            .commit()
                } else {
                    val context = v.context
                    val intent = Intent(context, SongDetailActivity::class.java)
                    intent.putExtra(SongUtils.SONG_ID_KEY, holder.adapterPosition)
                    context.startActivity(intent)
                }
                val editor = sharedpreferences.edit()
                editor.putInt(Song, position)
                editor.commit()
                notifyDataSetChanged()
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        internal inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView
            val mContentView: TextView
            var mItem: SongUtils.Song? = null

            init {
                mIdView = mView.findViewById<View>(R.id.id) as TextView
                mContentView = mView.findViewById<View>(R.id.content) as TextView
            }

        }
    }

}