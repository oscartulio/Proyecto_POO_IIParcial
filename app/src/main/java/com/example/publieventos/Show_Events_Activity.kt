package com.example.publieventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_information.*

class Show_Events_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__events_)

    }

    fun getValues(evento:String,fecha:String,hora:String,descrip: String){
        textView4.text=evento.toString()

    }


}
