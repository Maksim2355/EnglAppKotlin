package com.example.engapp


import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.engapp.database.DataWorks
import com.example.engapp.database.DataWorksDao
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class ElementWorksFragment : Fragment(), View.OnClickListener, MediaPlayer.OnCompletionListener {
    private val worksDao: DataWorksDao = App.instance!!.database!!.worksDao()!!
    private lateinit var  myFragment: View
    private lateinit var title: TextView
    private lateinit var contentEn: TextView
    private lateinit var contentRu: TextView
    private var audioEng: ImageButton? = null
    private var pauseBut: ImageButton? = null
    private var resetBut: ImageButton? = null
    private var rewBut: ImageButton? = null
    private var forwBut: ImageButton? = null
    private var mPlayer: MediaPlayer? = null
    private val threadHandler: Handler = Handler()
    private  var progressControl: SeekBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(R.layout.fragment_element_works, container, false)
        val thisWorks = worksDao.getById(arguments!!.getInt("workId"))
        init(thisWorks!!)
        //Если имеется
        if (thisWorks.pathAudio != null){
            activePlayer(thisWorks)
        }else{
            val menuPlayer = myFragment.findViewById<LinearLayout>(R.id.menuPlayer)
            menuPlayer.visibility = View.GONE
        }


        // Inflate the layout for this fragment
        return myFragment
    }

    private fun init(thisWorks: DataWorks){
        title = myFragment.findViewById(R.id.titleAdd)
        contentEn = myFragment.findViewById(R.id.contentEn)
        contentRu = myFragment.findViewById(R.id.contentRu)
        title.text = thisWorks.title
        contentEn.text = thisWorks.contentRu
        contentRu.text = thisWorks.contentEn
        activity!!.title = thisWorks.title
    }
    private fun activePlayer(works: DataWorks){
        val uriAudio = Uri.parse("file:///storage/emulated/0" +
                works.pathAudio)
        mPlayer = MediaPlayer.create(context, uriAudio)
        audioEng = myFragment.findViewById(R.id.audioEng)
        pauseBut = myFragment.findViewById(R.id.pauseBut)
        pauseBut!!.isEnabled = true
        resetBut = myFragment.findViewById(R.id.resetBut)
        rewBut = myFragment.findViewById(R.id.rewindFastBut)
        forwBut = myFragment.findViewById(R.id.forwardFastBut)
        progressControl = myFragment.findViewById(R.id.progressControl)
        //Присваиваем обработчики для каждой кнопки
        audioEng!!.setOnClickListener(this) //Кнопка старта
        resetBut!!.setOnClickListener(this) //Кнопка перезапуска трека
        pauseBut!!.setOnClickListener(this) //Кнопка паузы
        rewBut!!.setOnClickListener(this) //Перемтока назад
        forwBut!!.setOnClickListener(this) //Перемотка вперед
        mPlayer!!.setOnCompletionListener(this)
    }


    private fun doPause() {
        mPlayer!!.pause()
        audioEng!!.setImageResource(R.drawable.play_48dp)
        pauseBut!!.setImageResource(R.drawable.pause_active_48dp)
        pauseBut!!.isEnabled = false
        audioEng!!.isEnabled = true
    }

    private fun doStart() {
        val duration: Int = mPlayer!!.duration
        val currentPosition: Int = mPlayer!!.currentPosition
        if (currentPosition == 0) {
            progressControl!!.max = duration
        }
        audioEng!!.setImageResource(R.drawable.music_active)
        pauseBut!!.setImageResource(R.drawable.pause_24dp)
        mPlayer!!.start()
        // Create a thread to update position of SeekBar.
        val updateSeekBarThread = UpdateSeekBarThread(mPlayer, progressControl, threadHandler)
        threadHandler.postDelayed(updateSeekBarThread, 50)
        pauseBut!!.isEnabled = true
        audioEng!!.isEnabled = false
    }

    internal class UpdateSeekBarThread(private val mediaPlayer: MediaPlayer?,
                                       private val seekBar: SeekBar?,
                                       private val threadHandler: Handler) : Runnable {
        override fun run() {
            val currentPosition: Int = mediaPlayer!!.currentPosition
            seekBar!!.progress = currentPosition
            threadHandler.postDelayed(this, 50)
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.audioEng -> {
                doStart()
            }
            R.id.pauseBut -> {
                doPause()
            }
            R.id.resetBut-> {
                doReset()
            }
            R.id.rewindFastBut -> {
                doRewind()
            }
            R.id.forwardFastBut -> {
                doFastForward()
            }

        }
    }

    override fun onPause() {
        super.onPause()
        if(mPlayer != null){
            doPause()
        }

    }


    private fun doRewind() {
        val currentPosition: Int = mPlayer!!.currentPosition
        val duration: Int = mPlayer!!.duration
        // 5 seconds.
        val SUBTRACT_TIME = 5000
        if (currentPosition - SUBTRACT_TIME > 0) {
            mPlayer!!.seekTo(currentPosition - SUBTRACT_TIME)
        }
    }

    private fun doFastForward() {
        val currentPosition: Int = mPlayer!!.currentPosition
        val duration: Int = mPlayer!!.duration
        // 5 seconds.
        val ADD_TIME = 5000
        if (currentPosition + ADD_TIME < duration) {
            mPlayer!!.seekTo(currentPosition + ADD_TIME)
        }
    }

    private fun doReset(){
        mPlayer!!.seekTo(0)
        pauseBut!!.isEnabled = false
        audioEng!!.isEnabled = true
        doStart()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        audioEng!!.setImageResource(R.drawable.play_48dp)
        pauseBut!!.setImageResource(R.drawable.pause_active_48dp)
        pauseBut!!.isEnabled = false
        audioEng!!.isEnabled = true
    }


}
