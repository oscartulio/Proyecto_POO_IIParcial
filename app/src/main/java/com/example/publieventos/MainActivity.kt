package com.example.publieventos
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {


    private var listEvents = ArrayList<EventActivity>()

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*Cambio de activity a AddEventActivity*/
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intento1 = Intent(
                this,
                AddEventActivity::class.java
            )
            startActivity(intento1)
        }

/*
                //Muestra el panel de botones de la parte izquierda
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
*/
        loadQueryAll()

        /*al dar click en el listview dice el nombre del evento*/

      /* lvEventos.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + listEvents[position].title, Toast.LENGTH_SHORT).show() }*/
    }

    override fun onResume() {
        super.onResume()
        loadQueryAll()
    }

    fun loadQueryAll() {

        var dbManager = EventDbManager(this)
        val cursor = dbManager.queryAll()

        listEvents.clear()
        if (cursor.moveToFirst()) {

            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val title = cursor.getString(cursor.getColumnIndex("Title"))
                val date = cursor.getString(cursor.getColumnIndex("Date"))
                val time = cursor.getString(cursor.getColumnIndex("Time"))
                val staff = cursor.getString(cursor.getColumnIndex("Staff"))
                val content = cursor.getString(cursor.getColumnIndex("Content"))

                listEvents.add(EventActivity(id, title,time,date,staff, content))

            } while (cursor.moveToNext())
        }

        var notesAdapter = NotesAdapter(this, listEvents)
        lvEventos.adapter = notesAdapter
    }

    /***************************************/

    inner class NotesAdapter : BaseAdapter {

        private var notesList = ArrayList<EventActivity>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<EventActivity>) : super() {
            this.notesList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.activity_event, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mNote = notesList[position]

            vh.tvTitle.text = mNote.title

            vh.tvContent.text = mNote.content

            vh.ivEdit.setOnClickListener {
                updateNote(mNote)
            }

            vh.ivDelete.setOnClickListener {
                var dbManager = EventDbManager(this.context!!)
                val selectionArgs = arrayOf(mNote.id.toString())
                dbManager.delete("Id=?", selectionArgs)
                loadQueryAll()
            }

            return view
        }

        /****/
        override fun getItem(position: Int): Any {
            return notesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return notesList.size
        }
    }

    private fun updateNote(note: EventActivity) {
        var intent = Intent(this, AddEventActivity::class.java)
        intent.putExtra("MainActId", note.id)
        intent.putExtra("MainActTitle", note.title)
        intent.putExtra("MainActContent", note.content)
        startActivity(intent)
    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView
        val ivEdit: ImageView
        val ivDelete: ImageView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById(R.id.tvContent) as TextView
            this.ivEdit = view?.findViewById(R.id.ivEdit) as ImageView
            this.ivDelete = view?.findViewById(R.id.ivDelete) as ImageView
        }
    }








/*
        /*no se pero tiene que ver con el panel izquierdo*/
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.main, menu)
            return true
        }

        /*Desplega el menu de la parte izquierda*/
        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }


        /*permite presionar el boton atras y que ek menu se cierre*/
        override fun onBackPressed() {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }
*/

/*última linea*/

}

