package com.example.app_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)


        val title = findViewById<TextView>(R.id.tx_title) as EditText
        val description = findViewById<TextView>(R.id.tx_description) as EditText


        //val btn_enviar = findViewById(R.id.btn_enviar) as Button




        GlobalScope.launch {
            val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
            val repository = BookRepository(bookDao)
            repository.insert(Book(title.getText().toString(),description.getText().toString()))
            //repository.insert(Book("the best seller: Android","ddccc"))
            val lista = repository.getListBooks()
            lista.forEach {
                Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Description: ${it.description}")
            }
        }

    }

}