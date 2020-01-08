package com.example.engapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.DataWorksDao
import com.example.engapp.database.UserDataDao

/**
 * A simple [Fragment] subclass.
 */
class ElementWorksFragment : Fragment() {
    private val db: AppDatabase? = App.instance!!.database!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private val worksDao: DataWorksDao = db!!.worksDao()!!
    private lateinit var title: TextView
    private lateinit var contentEn: TextView
    private lateinit var contentRu: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myFragment
                = inflater.inflate(R.layout.fragment_element_works, container, false)
        val thisWorks = worksDao.getById(userDao.getUserData()!!.openWorks!!)
        title = myFragment.findViewById(R.id.titleAdd)
        contentEn = myFragment.findViewById(R.id.contentEn)
        contentRu = myFragment.findViewById(R.id.contentEnAdd)
        title.text = thisWorks!!.title
        contentEn.text = thisWorks.contentEn
        contentRu.text = thisWorks.contentRu
        // Inflate the layout for this fragment
        return myFragment
    }


}
