package com.example.project01.reinoso1165606.finalexampractical

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context):SQLiteOpenHelper(context, "SavedPlaces", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE Locations (id Int, title Text, lat Text, lon Text)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS Locations"
        db?.execSQL(query)
    }

    fun insert(location:PositionDataModel):Int{
        val db = writableDatabase

        val contentValues = ContentValues()
        contentValues.put("title",location.title)
        contentValues.put("lat",location.latitude)
        contentValues.put("lon",location.longitude)

        val id = db.insert("Locations",null, contentValues)
        return id.toInt()
    }

    fun read():ArrayList<PositionDataModel>{
        var positions = ArrayList<PositionDataModel>()

        val db = readableDatabase
        val query = "SELECT * FROM Locations"
        val cursor = db.rawQuery(query,null)

        while(cursor.moveToNext()){
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val lat = cursor.getString(cursor.getColumnIndexOrThrow("lat"))
            val lon = cursor.getString(cursor.getColumnIndexOrThrow("lon"))
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))

            var position = PositionDataModel(title, lat, lon)
            position.id = id

            positions.add(position)
        }
        return positions
    }

}