package com.example.engapp


import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.engapp.database.DataWorksDao


/**
 * A simple [Fragment] subclass.
 */
class ElementWorksFragment : Fragment() {
    private val worksDao: DataWorksDao = App.instance!!.database!!.worksDao()!!
    private lateinit var  myFragment: View
    private lateinit var title: TextView
    private lateinit var contentEn: TextView
    private lateinit var contentRu: TextView
    private lateinit var audioEng: ImageButton
    private lateinit var pauseBut: ImageButton
    private lateinit var resetBut: ImageButton
    private var mPlayer: MediaPlayer? = null
    private lateinit var progressContol: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(R.layout.fragment_element_works, container, false)
        init()


        // Inflate the layout for this fragment
        return myFragment
    }

    private fun init(){
        val thisWorks = worksDao.getById(arguments!!.getInt("workId"))
        title = myFragment.findViewById(R.id.titleAdd)
        contentEn = myFragment.findViewById(R.id.contentEn)
        contentRu = myFragment.findViewById(R.id.contentRu)
        audioEng = myFragment.findViewById(R.id.audioEng)
        pauseBut = myFragment.findViewById(R.id.pauseBut)
        resetBut = myFragment.findViewById(R.id.resetBut)
        if (thisWorks?.pathAudio != null) {
            val uriAudio = Uri.parse("file:///storage/emulated/0" +
                    thisWorks.pathAudio)
            mPlayer = MediaPlayer.create(context, uriAudio)
        }
        title.text = thisWorks!!.title
        contentEn.text = thisWorks.contentRu
        contentRu.text = thisWorks.contentEn
        activity!!.title = thisWorks.title
    }


    private fun doPause(view: View?) {
        mPlayer!!.pause()
        pauseBut.isEnabled = false
        audioEng.isEnabled = true
    }


}
