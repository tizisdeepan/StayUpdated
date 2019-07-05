package com.deepan.stayupdated.list.model.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.deepan.stayupdated.list.model.Headline
import org.jetbrains.anko.db.insert

class HeadlinesDbManipulation(var context: Context, var category: String) {

    private var dbHelper: HeadlinesDbHandler? = null

    init {
        dbHelper = HeadlinesDbHandler.getInstance(context, category)
    }

    fun insertSNData(headline: Headline): Boolean {
        return try {
            dbHelper?.readableDatabase?.insert(HeadlinesDbConstants(category).TABLE_NAME, HeadlinesDbConstants(category).ID to headline.id, HeadlinesDbConstants(category).SOURCE_ID to headline.sourceId, HeadlinesDbConstants(category).SOURCE_NAME to headline.sourceName, HeadlinesDbConstants(category).AUTHOR to headline.author, HeadlinesDbConstants(category).TITLE to headline.title, HeadlinesDbConstants(category).DESCRIPTION to headline.description, HeadlinesDbConstants(category).URL to headline.url, HeadlinesDbConstants(category).IMAGE_URL to headline.imageUrl, HeadlinesDbConstants(category).TIME to headline.time)
            true
        } catch (e: Exception) {
            Log.e("SQL ERROR ", e.toString())
            false
        }
    }

    fun getAllHeadlines(): Cursor? = dbHelper?.readableDatabase?.rawQuery("SELECT * FROM " + HeadlinesDbConstants(category).TABLE_NAME, null)

    fun clearHeadlinesDb(): Boolean {
        return try {
            dbHelper?.readableDatabase?.delete(HeadlinesDbConstants(category).TABLE_NAME, "1", null) ?: 0 > 0
        } catch (e: Exception) {
            Log.e("SQL DELETE ", e.toString())
            false
        }
    }
}