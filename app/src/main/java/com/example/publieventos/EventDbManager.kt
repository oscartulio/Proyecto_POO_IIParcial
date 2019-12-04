package com.example.publieventos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class EventDbManager {

    private val dbName = "PubliEventos"
    private val dbTable = "Eventos"
    private val colId = "Id"
    private val colTitle = "Title"
    private val colDate = "Date"
    private val colTime = "Time"
    private val colStaff = "Staff"
    private val colContent = "Content"
    private val dbVersion = 1

    private val CREATE_TABLE_SQL =
        "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + colId + " " +
                "INTEGER PRIMARY KEY," + colTitle + " TEXT, " + colDate + " TEXT, " + colTime + " TEXT, " + colStaff + " TEXT, " + colContent + " TEXT);"
    private var db: SQLiteDatabase? = null

    constructor(context: Context) {
        var dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
    }

    fun insert(values: ContentValues): Long {

        val ID = db!!.insert(dbTable, "", values)
        return ID
    }

    fun queryAll(): Cursor {

        return db!!.rawQuery("select * from " + dbTable, null)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int {

        val count = db!!.delete(dbTable, selection, selectionArgs)
        return count
    }

    fun update(values: ContentValues, selection: String, selectionargs: Array<String>): Int {

        val count = db!!.update(dbTable, values, selection, selectionargs)
        return count
    }

    inner class DatabaseHelper : SQLiteOpenHelper {

        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE_SQL)
            Toast.makeText(this.context, " Base de Datos creada con Exito", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS " + dbTable)
        }
    }





}