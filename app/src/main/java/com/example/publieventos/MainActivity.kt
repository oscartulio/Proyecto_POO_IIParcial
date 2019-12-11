package com.example.publieventos
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Html
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_event.*
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

       lvEventos.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
           val Builder = AlertDialog.Builder(this)
           Builder.setTitle("Descripcion Del Evento")
            val title = (listEvents[position].title)
            val date = (listEvents[position].date)
            val time = (listEvents[position].time)
            val staff = (listEvents[position].staff)
            val content =(listEvents[position].content)
           Builder.setMessage(Html.fromHtml("<big><br><br>Evento:<br><br></big> $title \n<big><br><br>Fecha:<br><br></big> $time \n<big><br><br>Hora:<br><br></big> $date \n<big><br><br>Staff:<br><br></big>\n$staff \n" +
                   "<big><br><br>Staff:<br><br></big>\n" +
                   "$content"))
           Builder.setNeutralButton("Hecho",{ dialogInterface: DialogInterface, i: Int -> })
            Builder.show()
       }

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
                Log.i("1", "Posocion de ViewHolder" + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mNote = notesList[position]

            vh.tvTitle.text = mNote.title

            vh.tvDate.text = mNote.date

            vh.tvTime.text = mNote.time

            /*Editar evento*/
            vh.ivEdit.setOnClickListener {
                updateNote(mNote)
            }

            /*Eliminar*/
            vh.ivDelete.setOnClickListener {

                var dbManager = EventDbManager(this.context!!)
                val selectionArgs = arrayOf(mNote.id.toString())
                dbManager.delete("Id=?", selectionArgs)

                showDialog()
                loadQueryAll()

            }


            return view
        }



        /****************/
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
        intent.putExtra("MainDate", note.date  )
        startActivity(intent)
    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvDate: TextView
        val tvTime: TextView
        val ivEdit: ImageView
        val ivDelete: ImageView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvDate = view?.findViewById(R.id.tvDate) as TextView
            this.tvTime = view?.findViewById(R.id.tvTime) as TextView
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

private fun showDialog() {

    lateinit var dialog: AlertDialog



    val builder = AlertDialog.Builder(this)


    builder.setTitle("Información")


    builder.setMessage("Se ha Removido el Evento")


    val dialogClickListener = DialogInterface.OnClickListener { _, which -> }

    builder.setNeutralButton("Hecho",dialogClickListener)

    dialog = builder.create()

    dialog.show()
}
/*
    private fun showDialogContent() {

        lateinit var dialog: AlertDialog



        val builder = AlertDialog.Builder(this)


        builder.setTitle("Información")
        //Toast.makeText(this, "Click en " + listEvents[position].title, Toast.LENGTH_SHORT).show()
        builder.setMessage("Se ha Removido el Evento" + listEvents[])


        val dialogClickListener = DialogInterface.OnClickListener { _, which -> }

        builder.setNeutralButton("Hecho",dialogClickListener)

        dialog = builder.create()

        dialog.show()
    }

*/


/*última linea*/
}




