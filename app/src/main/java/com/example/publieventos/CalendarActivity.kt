package com.example.publieventos

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.agregar_evento_activity.*
import java.util.*

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
    /*---------------------------------------------------------------------------------------*/
    val calendar= Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month= calendar.get(Calendar.MONTH)+1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

        val y= year
        val m=month
        val d=day


    editText5.setText(""+ d +"/"+ m +"/"+ y)


    imageView2.setOnClickListener{

        val dpd =
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view: DatePicker, year: Int, month:Int, dayOfMonth:Int ->
                editText5.setText(""+ dayOfMonth +"/"+month+"/"+year+"")
            },year,month,day)
        dpd.show()}


/*---------------------------------------------------------------------------------------*/


    }
}
