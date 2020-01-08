package com.example.engapp.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.engapp.App
import com.example.engapp.ListId
import com.example.engapp.R
import com.example.engapp.database.*


class AddWorkFragment : Fragment(), View.OnClickListener {
    private lateinit var  myFragment: View
    //Кнопка добавления файла и кнопка промежуточного/окончательного добавления
    private lateinit var addAudioPath: Button
    private lateinit var addImagePath: Button
    private lateinit var nextAdd: Button
    //Поля ввода для title/desc и для контента на английском/русском
    private lateinit var title: EditText
    private lateinit var contentDesc: EditText
    private lateinit var contentEn: EditText
    private lateinit var contentRu: EditText
    //Экземлпяры базы данных и для обновления данных о работе пользователя
    private val db: AppDatabase? = App.instance!!.database!!
    private val userDao: UserDataDao = db!!.userDao()!!
    private val accountDao: DataAccountDao = db!!.accountDao()!!
    //С помощью данного интерфейса добавляем работу в базу данных
    private val worksDao: DataWorksDao = db!!.worksDao()!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myFragment =
            inflater.inflate(R.layout.fragment_add_work, container, false)
        init()
        // Inflate the layout for this fragment
        return myFragment
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            //Нажимая данную кнопку, мы заставляем пользователя дать аудио/картинку
            R.id.addAudioPath-> {

            }
            /*Нажимая данную кнопку, мы сохраняем значения в переменные и вставляем в объект
            который позже заполняем до конца и вставляем в базу данных
             */
            R.id.addImagePath-> {

            }
            R.id.nextAdd-> {
                if (notEmpty()){
                    val work  = DataWorks()
                    work.title = title.text.toString()
                    work.contentEn = contentEn.text.toString()
                    work.contentDesc = contentDesc.text.toString()
                    work.contentRu = contentRu.text.toString()
                    work.idAuthor = userDao.getUserData()!!.userId!!
                    worksDao.insertAll(work)
                    /*Добавили работу, теперь получаем id этой работы и добавляем
                    ее к работам аккаунта
                    */
                    val idWork = worksDao.getByTitle(work.title!!)!!.id
                    val account =
                        accountDao.getById(userDao.getUserData()!!.userId!!)
                    val listUser = ListId(account!!.idWorks)
                    listUser.addItem(idWork!!)
                    account.idWorks = listUser.getList().toString()
                    accountDao.update(account)
                    val navController =
                        activity?.let {
                            Navigation.findNavController(
                                it,
                                R.id.nav_host_fragment
                            )
                        }!!
                    navController.navigate(R.id.userFragment)
                }else{
                    val toast = Toast.makeText(context,
                        "Error! Input lines are empty or take up very few characters",
                        Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }
    }


    private fun notEmpty(): Boolean {
        return title.text.toString() != "" && title.text.length > 10 &&
                contentDesc.text.toString() != "" && contentDesc.text.length > 30
                && contentEn.text.toString() != "" && contentEn.text.length > 50
                && contentRu.text.toString() != "" && contentEn.text.length > 50
    }

    private fun init(){
        addAudioPath = myFragment.findViewById(R.id.addAudioPath)
        addImagePath = myFragment.findViewById(R.id.addImagePath)
        nextAdd = myFragment.findViewById(R.id.nextAdd)
        title = myFragment.findViewById(R.id.titleAdd)
        contentDesc = myFragment.findViewById(R.id.contentDesAdd)
        contentEn = myFragment.findViewById(R.id.contentRuAdd)
        contentRu = myFragment.findViewById(R.id.contentEnAdd)
        addImagePath.setOnClickListener(this)
        addImagePath.setOnClickListener(this)
        nextAdd.setOnClickListener(this)
    }

}
