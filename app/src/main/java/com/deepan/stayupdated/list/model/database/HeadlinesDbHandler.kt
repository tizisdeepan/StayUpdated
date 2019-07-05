package com.deepan.stayupdated.list.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

class HeadlinesDbHandler(context: Context, var category: String) :
    ManagedSQLiteOpenHelper(context, HeadlinesDbConstants(category).DATABASE_NAME, null, HeadlinesDbConstants(category).VERSION) {
    companion object {
        private var instance: HeadlinesDbHandler? = null
        @Synchronized
        fun getInstance(ctx: Context, category: String): HeadlinesDbHandler {
            Log.e("Database accessed", HeadlinesDbConstants(category).DATABASE_NAME)
            if (instance == null) instance = HeadlinesDbHandler(ctx.applicationContext, category)
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + HeadlinesDbConstants(category).TABLE_NAME)
            createTable(db)
        }
    }

    private fun createTable(db: SQLiteDatabase) {
        db.createTable(HeadlinesDbConstants(category).TABLE_NAME, true, HeadlinesDbConstants(category).ID to TEXT, HeadlinesDbConstants(category).SOURCE_ID to TEXT, HeadlinesDbConstants(category).SOURCE_NAME to TEXT, HeadlinesDbConstants(category).AUTHOR to TEXT, HeadlinesDbConstants(category).TITLE to TEXT, HeadlinesDbConstants(category).DESCRIPTION to TEXT, HeadlinesDbConstants(category).URL to TEXT, HeadlinesDbConstants(category).IMAGE_URL to TEXT, HeadlinesDbConstants(category).TIME to TEXT)
    }
}