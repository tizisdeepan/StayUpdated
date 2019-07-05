package com.deepan.stayupdated.list.model.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.deepan.stayupdated.list.model.Headline
import org.jetbrains.anko.db.insert

class HeadlinesDbManipulation(var context: Context, var constants: HeadlinesDbConstants) {

    private var dbHelper: HeadlinesDbHandler? = null

    init {
        dbHelper = HeadlinesDbHandler.getInstance(context, constants)
    }

    fun insertSNData(headline: Headline): Boolean {
        return try {
            val cv = ContentValues()
            cv.put(constants.ID, headline.id)
            cv.put(constants.SOURCE_ID, headline.sourceId)
            cv.put(constants.SOURCE_NAME, headline.sourceName)
            cv.put(constants.AUTHOR, headline.author)
            cv.put(constants.TITLE, headline.title)
            cv.put(constants.DESCRIPTION, headline.description)
            cv.put(constants.URL, headline.url)
            cv.put(constants.IMAGE_URL, headline.imageUrl)
            cv.put(constants.TIME, headline.time)
            dbHelper?.readableDatabase?.insert(constants.TABLE_NAME, constants.ID to headline.id, constants.SOURCE_ID to headline.sourceId, constants.SOURCE_NAME to headline.sourceName, constants.AUTHOR to headline.author, constants.TITLE to headline.title, constants.DESCRIPTION to headline.description, constants.URL to headline.url, constants.IMAGE_URL to headline.imageUrl, constants.TIME to headline.time)
            true
        } catch (e: Exception) {
            Log.e("SQL ERROR ", e.toString())
            false
        }
    }

    fun getAllHeadlines(): Cursor? = dbHelper?.readableDatabase?.rawQuery("SELECT * FROM " + constants.TABLE_NAME, null)

    fun clearHeadlinesDb(): Boolean {
        return try {
            dbHelper?.readableDatabase?.delete(constants.TABLE_NAME, null, null) ?: 0 > 0
        } catch (e: Exception) {
            Log.e("SQL DELETE ", e.toString())
            false
        }
    }
}