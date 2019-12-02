package com.example.publieventos
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html.fromHtml
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calendar.*
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

            /*------------------------------------------------------------------------*/
            val Builder = AlertDialog.Builder(this)
                Builder.setTitle("Descripcion Del Evento")
            val event = sharedPreferences.getString("Evento", "")
            val fecha = sharedPreferences.getString("Fecha", "")
            val hora = sharedPreferences.getString("Hora", "")
            val descripcion = sharedPreferences.getString("Descripcion", "")
                Builder.setMessage(fromHtml("<big><br><br>Evento:<br><br></big> $event \n<big><br><br>Fecha:<br><br></big> $fecha \n<big><br><br>Hora:<br><br></big> $hora \n<big><br><br>Equipo:<br><br></big>\n$descripcion"))

                Builder.setNeutralButton("Hecho",{ dialogInterface: DialogInterface, i: Int ->
                    finish() })

            Builder.show()


            /*----------------------------------------------------------------------------*/
/*


*/

        }



       }



    }


