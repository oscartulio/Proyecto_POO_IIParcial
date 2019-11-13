package com.example.publieventos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import kotlinx.android.synthetic.main.agregar_evento_activity.*
import java.util.*


class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)


    val calendar= Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month= calendar.get(Calendar.MONTH)+1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hora = calendar.get(Calendar.HOUR)
    val minute=calendar.get(Calendar.MINUTE)



        val y= year
        val m=month
        val d=day
        val hors=hora
        val mins=minute


    editText5.setText(""+ d +"/"+ m +"/"+ y)

        editTextTime.setText(""+hors +":"+mins)

    imageView2.setOnClickListener{

        val dpd =
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view: DatePicker, year: Int, month:Int, dayOfMonth:Int ->
                editText5.setText(""+ dayOfMonth +"/"+month+"/"+year+"")
            },year,month,day)
        dpd.show()}


        imageView3.setOnClickListener{
            val dpd2 =
                TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view: TimePicker, hourOfDay, minute ->
                    editTextTime.setText(""+hourOfDay +":"+minute)
                },hora,minute)
            dpd2.show() }


    }
}
