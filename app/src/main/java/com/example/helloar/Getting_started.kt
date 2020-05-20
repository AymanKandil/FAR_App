package com.example.helloar

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Getting_started : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getting_started)
    }
    fun BACK (view: View){
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
