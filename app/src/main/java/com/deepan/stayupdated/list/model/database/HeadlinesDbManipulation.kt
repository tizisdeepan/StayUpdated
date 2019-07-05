package com.deepan.stayupdated.list.model.database

import android.content.Context
import android.util.Log
import org.jetbrains.anko.db.insert

class HeadlinesDbManipulation(var context: Context) {

    private var dbHelper: HeadlinesDbHandler? = null

    init {
        dbHelper = HeadlinesDbHandler.getInstance(context)
    }

    fun insertSNData(category: String, headlineJson: String): Boolean {
        return try {
            dbHelper?.readableDatabase?.insert(HeadlinesDbConstants.TABLE_NAME, HeadlinesDbConstants.CATEGORY to category, HeadlinesDbConstants.HEADLINE to headlineJson)
            true
        } catch (e: Exception) {
            Log.e("SQL ERROR ", e.toString())
            false
        }
    }

    fun getAllHeadlines(category: String): String? {
        val args = arrayOf(category)
        val cursor = dbHelper?.readableDatabase?.query(HeadlinesDbConstants.TABLE_NAME, null, "${HeadlinesDbConstants.CATEGORY}=?", args, null, null, null)
        return try {
            cursor?.moveToFirst()
            cursor?.getString(HeadlinesColumnInfo.headline)
        } catch (e: Exception) {
            null
        } finally {
            cursor?.close()
        }
    }

    fun clearHeadlinesDb(category: String): Boolean {
        val args = arrayOf(category)
        return try {
            dbHelper?.readableDatabase?.delete(HeadlinesDbConstants.TABLE_NAME, "${HeadlinesDbConstants.CATEGORY}=?", args) ?: 0 > 0
        } catch (e: Exception) {
            Log.e("SQL DELETE ", e.toString())
            false
        }
    }
}