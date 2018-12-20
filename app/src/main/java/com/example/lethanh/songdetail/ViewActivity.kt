package com.example.lethanh.songdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView

class ViewActivity : AppCompatActivity() {

    var valus = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val adapter = ArrayAdapter<String>(this,
                R.layout.list_item, valus)

        val listView = findViewById<RecyclerView>(R.id.list_view) as ListView
        listView.setAdapter(adapter)
        listView.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val textView = view.findViewById(R.id.text) as TextView

                for (i in 0 until listView.childCount) {
                    if (position === i) {
                        listView.getChildAt(i).setBackgroundColor(Color.BLUE)
                    } else {
                        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
                    }
                }
                var temp = Integer.parseInt(textView.text.toString()) + 1
                textView.text = temp.toString()
            }
        })
    }
}
