package com.deepan.stayupdated.list.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

class HeadlinesDbHandler(context: Context, var constants: HeadlinesDbConstants) :
    ManagedSQLiteOpenHelper(context, constants.DATABASE_NAME, null, constants.VERSION) {
    companion object {
        private var instance: HeadlinesDbHandler? = null
        @Synchronized
        fun getInstance(ctx: Context, constants: HeadlinesDbConstants): HeadlinesDbHandler {
            Log.e("Database accessed", constants.DATABASE_NAME)
            if (instance == null) instance = HeadlinesDbHandler(ctx.applicationContext, constants)
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS " + constants.TABLE_NAME)
            createTable(db)
        }
    }

    private fun createTable(db: SQLiteDatabase) {
        db.createTable(constants.TABLE_NAME, true, constants.ID to TEXT, constants.SOURCE_ID to TEXT, constants.SOURCE_NAME to TEXT, constants.AUTHOR to TEXT, constants.TITLE to TEXT, constants.DESCRIPTION to TEXT, constants.URL to TEXT, constants.IMAGE_URL to TEXT, constants.TIME to TEXT)
    }
}