package com.example.lethanh.songdetail


import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.lethanh.songdetail.content.SongUtils
import kotlinx.android.synthetic.main.song_list_content.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SongDetailFragment : Fragment() {

    var mSong: SongUtils.Song? = null
    var data: SongUtils?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       if (getArguments()!!.containsKey(SongUtils.SONG_ID_KEY )) {
          mSong = SongUtils.SONG_ITEMS.get(getArguments()!!.getInt(SongUtils.SONG_ID_KEY))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rooter =  inflater.inflate(R.layout.song_detail, container, false)

        if (mSong != null){
            rooter.findViewById<TextView>(R.id.song_detail).setText(mSong!!.details)
        }
        return rooter
    }

    fun newInstance(selectedSong: Int): SongDetailFragment {
        val fragment = SongDetailFragment()
        val arguments = Bundle()
        arguments.putInt(SongUtils.SONG_ID_KEY, selectedSong)
        fragment.arguments = arguments
        return fragment
    }
}
