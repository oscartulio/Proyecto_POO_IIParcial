package com.example.publieventos

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.graphics.scaleMatrix
import kotlinx.android.synthetic.main.agregar_evento_fragment.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {



     private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*Cambio de activity a agreagar evento*/
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intento1 = Intent(this,AgregarEvento :: class.java )
            startActivity(intento1)
        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        /*DatePicker*/
        val Calendarvalue=findViewById<View>(R.id.textView)as TextView
        val submit=findViewById<View>(R.id.button)as Button
        val cal=Calendar.getInstance()
        submit.setOnClickListener(View.OnClickListener {
             val date=object: DatePickerDialog.OnDateSetListener{
                 override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
                  cal.set(Calendar.YEAR,year)
                  cal.set(Calendar.MONTH,month)
                  cal.set(Calendar.DAY_OF_MONTH,day)
                  Calendarvalue.text=SimpleDateFormat("MM/dd/yyyy",Locale.US).format(cal.getTime())
                 }

             }
            DatePickerDialog(this,date,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        })






    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }







}




