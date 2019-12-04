package com.example.publieventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
//import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.activity_event.*


class AddEventActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("MainActTitle"))
                edtDate.setText(bundle.getString("MainActTitle"))
                edtTime.setText(bundle.getString("MainActTitle"))
                edtStaff.setText(bundle.getString("MainActTitle"))
                edtDescription.setText(bundle.getString("MainActContent"))
            }
        } catch (ex: Exception) {
        }

        btAdd.setOnClickListener {
            var dbManager = EventDbManager(this)

            var values = ContentValues()
            values.put("Title", edtTitle.text.toString())
            values.put("Date", edtDate.text.toString())
            values.put("Time", edtTime.text.toString())
            values.put("Staff", edtDescription.text.toString())
            values.put("Description", edtDescription.text.toString())

            if (id == 0) {
                val mID = dbManager.insert(values)

                if (mID > 0) {
                    Toast.makeText(this, "Evento Agregado Satisfactoriamente", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al agregar el Evento", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                val mID = dbManager.update(values, "Id=?", selectionArs)

                if (mID > 0) {
                    Toast.makeText(this, "Evento Agregado Satisfactoriamente", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al agregar el Evento", Toast.LENGTH_LONG).show()
                }
            }
        }





    }
}
