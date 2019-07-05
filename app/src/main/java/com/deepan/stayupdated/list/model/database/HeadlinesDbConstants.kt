package com.deepan.stayupdated.list.model.database

/**
 * Created by deepan-5901 on 05/12/17.
 */
class HeadlinesDbConstants(var category: String) {
    val DATABASE_NAME = "Headlines_$category.db"

    val TABLE_NAME = "headlines"

    val VERSION = 1

    val ID = "id"
    val SOURCE_ID = "sourceId"
    val SOURCE_NAME = "sourceName"
    val AUTHOR = "author"
    val TITLE = "title"
    val DESCRIPTION = "description"
    val URL = "url"
    val IMAGE_URL = "imageUrl"
    val TIME = "time"
}