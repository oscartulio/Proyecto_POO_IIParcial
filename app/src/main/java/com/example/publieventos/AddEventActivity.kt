package com.example.publieventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_event.*



class AddEventActivity : AppCompatActivity() {

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("Title"))
                edtDate.setText(bundle.getString("Date"))
                edtTime.setText(bundle.getString("Time"))
                edtStaff.setText(bundle.getString("Staff"))
                edtDescription.setText(bundle.getString("Content"))
            }
        } catch (ex: Exception) {
        }

        btAdd.setOnClickListener {
            var dbManager = EventDbManager(this)

            var values = ContentValues()
            values.put("Title", edtTitle.text.toString())
            values.put("Date", edtDate.text.toString())
            values.put("Time", edtTime.text.toString())
            values.put("Staff", edtStaff.text.toString())
            values.put("Content", edtDescription.text.toString())

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
