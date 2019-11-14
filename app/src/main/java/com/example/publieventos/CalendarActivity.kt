package com.example.publieventos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.agregar_evento_activity.*

import java.text.SimpleDateFormat
import java.util.*


class CalendarActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val y = year
        val m = month
        val d = day


        editText5.setText("" + d + "/" + m + "/" + y)




        imageView2.setOnClickListener {

            val dpd =
                DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        editText5.setText("" + dayOfMonth + "/" + month + "/" + year + "") },
                    year, month, day)
            dpd.show()
        }
/*----------------------------------------------------------------------------------------------------------------------*/

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        BtnSave.setOnClickListener{

           val event = txt1.text.toString().trim()
            val fecha = editText5.text
            val hora = editText.text.toString().trim()
            val  descripcion = editText4.text.toString().trim()

            val editor = sharedPreferences.edit()

            editor.putString("Evento", event)
            editor.putString("Fecha",fecha.toString() )
            editor.putString("Hora", hora)
            editor.putString("Descripcion", descripcion)
            editor.apply()
             Toast.makeText(this,"Guardado satisfactoriamente",Toast.LENGTH_LONG).show()

        }


        ShowBtn.setOnClickListener {
            val show_event = Show_Events_Activity()

            /*------------------------------------------------------------------------*/
            val intento2 = Intent(this, Show_Events_Activity :: class.java)
            startActivity(intento2)





            /*----------------------------------------------------------------------------*/

            val event = sharedPreferences.getString("Evento", "")
                val fecha = sharedPreferences.getString("Fecha", "")
                val hora = sharedPreferences.getString("Hora", "")
                val descripcion = sharedPreferences.getString("Descripcion", "")

            show_event.getValues(event,fecha,hora,descripcion)


                textView3.text = "Evento: $event \nFecha: $fecha \nHora: $hora \nDescripcion: $descripcion "



        }

    /*--------------------------------------------------------------------------------------------------------------*/


       }



    }


