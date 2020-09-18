package com.example.helloar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_items_selected.*

class Items_selected : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_selected)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val countt = intent.getIntExtra("count",0)
        val countt2 = intent.getIntExtra("count2",0)
        val countt3 = intent.getIntExtra("count3",0)
        val countt4 = intent.getIntExtra("count4",0)
        val countt5 = intent.getIntExtra("count5",0)
        TV_chairs.text=  "$countt2 Arm Chair"
        TV_Bed.text= "$countt3 Bed"
        TV_Desk.text=" $countt Desk"
        TV_Bookshelf.text= "$countt4  Bookshelf"
        TV_shortcouch.text= "$countt5 Couch"

    }
    fun BACK (view: View){
        val intent= Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
