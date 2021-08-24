package com.example.applearnenglish

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val NAME_DATABASE: String = "NAME_DATABASE"
    private lateinit var tvVoca : TextView
    private lateinit var edtVocaNew : EditText
    private lateinit var btNext : Button
    private lateinit var btAdd : Button
    private lateinit var btOK : Button
    private lateinit var btResetDatabase : Button
    private lateinit var db : AppDatabase
    private lateinit var list : MutableList<VocaModel>
    private lateinit var listIndex : MutableList<Int>
    private var checkResetDatabase : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initEvents()
    }

    private fun initEvents() {
        // Event btNext
        btNext.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                edtVocaNew.visibility = View.GONE
                btOK.visibility = View.GONE

                if(checkResetDatabase==false){
                    checkResetDatabase = true
                    listIndex.clear()
                    listIndex.add(-1)
                }

                var checkWhile : Boolean = true
                var i : Int
                while (checkWhile){
                    var checkFor : Boolean = true

                    if(list.size==0||list.size==listIndex.size-1){
                        return
                    }

                    i = (0..list.size-1).random()

                    for (j in 0..listIndex.size-1){
                        var index : Int = listIndex.get(j)
                        if(index==i){
                            checkFor = false
                        }
                    }

                    if(checkFor==true){
                        checkWhile = false
                        listIndex.add(i)
                        tvVoca.text = list.get(i).voca
                    }
                }
            }
        })

        // Event btAdd
        btAdd.setOnClickListener{
            edtVocaNew.visibility = View.VISIBLE
        }

        btResetDatabase.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                list = db.vocaDao().getAll()
                checkResetDatabase = false
            }

        })

        // Event btOk
        btOK.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if(edtVocaNew.text.toString().equals("")==false){
                    var str : String = edtVocaNew.text.toString()
                    var vocaModel = VocaModel(str)
                    db.vocaDao().insertVoca(vocaModel)
                    Toast.makeText(this@MainActivity,"Success",Toast.LENGTH_SHORT).show()
                    edtVocaNew.setText("")
                }else{
                    Toast.makeText(this@MainActivity,"Fail",Toast.LENGTH_SHORT).show()
                }
            }

        })

        // Event edtVocaNew
        edtVocaNew.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p3>0){
                    btOK.visibility = View.VISIBLE
                }else{
                    btOK.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }

    private fun initViews() {
        tvVoca = findViewById(R.id.tv_voca)
        edtVocaNew = findViewById(R.id.edt_voca_new)
        btNext = findViewById(R.id.bt_next)
        btAdd = findViewById(R.id.bt_add)
        btOK = findViewById(R.id.bt_ok)
        btResetDatabase = findViewById(R.id.bt_reset_database)
        db = AppDatabase.getInstance(this)
        list = db.vocaDao().getAll()
        listIndex = mutableListOf()
        listIndex.add(-1)
    }
}