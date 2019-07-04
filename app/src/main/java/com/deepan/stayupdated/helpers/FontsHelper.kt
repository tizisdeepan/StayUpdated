package com.deepan.stayupdated.helpers

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import java.util.*


object FontsHelper {
    private val TAG = "TypefaceHelper"

    private val fontsCache = Hashtable<String, Typeface>()

    operator fun get(c: Context?, assetPath: String): Typeface? {
        if (!fontsCache.containsKey(assetPath) && c != null) {
            try {
                fontsCache[assetPath] = Typeface.createFromAsset(c.assets, "fonts/$assetPath")
            } catch (e: Exception) {
                Log.e(TAG, "Could not get typeface '" + assetPath + "' because " + e.message)
                return null
            }
        }
        return fontsCache[assetPath]
    }
}