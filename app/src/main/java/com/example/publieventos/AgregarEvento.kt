package com.example.publieventos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.publieventos.ui.agregarevento.AgregarEventoFragment

class AgregarEvento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agregar_evento_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AgregarEventoFragment.newInstance())
                .commitNow()
        }









    }






}
