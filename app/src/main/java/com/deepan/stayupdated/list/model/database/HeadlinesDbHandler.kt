package com.deepan.stayupdated.list.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

class HeadlinesDbHandler(context: Context) :
    ManagedSQLiteOpenHelper(context, HeadlinesDbConstants.DATABASE_NAME, null, HeadlinesDbConstants.VERSION) {
    companion object {
        private var instance: HeadlinesDbHandler? = null
        @Synchronized
        fun getInstance(ctx: Context): HeadlinesDbHandler {
            Log.e("Database accessed", HeadlinesDbConstants.DATABASE_NAME)
            if (instance == null) instance = HeadlinesDbHandler(ctx.applicationContext)
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + HeadlinesDbConstants.TABLE_NAME)
            createTable(db)
        }
    }

    private fun createTable(db: SQLiteDatabase) {
        db.createTable(HeadlinesDbConstants.TABLE_NAME, true, HeadlinesDbConstants.CATEGORY to TEXT, HeadlinesDbConstants.HEADLINE to TEXT)
    }
}