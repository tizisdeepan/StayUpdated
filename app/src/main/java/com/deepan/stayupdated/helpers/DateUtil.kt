package com.deepan.stayupdated.helpers

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    //2019-07-04T07:00:01Z
    fun getDateString(timeStr: String): String = getTimeString(getDate(timeStr).time)

    @SuppressLint("SimpleDateFormat")
    private fun getDate(timeStr: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return getFormattedDate(sdf, timeStr)
    }

    private fun getFormattedDate(sdf: SimpleDateFormat, timeStr: String): Date {
        try {
            return sdf.parse(timeStr)
        } catch (parseExp: ParseException) {
            parseExp.printStackTrace()
        }
        return Date()
    }

    private fun getTimeString(time: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        val month = when (cal[Calendar.MONTH]) {
            Calendar.JANUARY -> "Jan"
            Calendar.FEBRUARY -> "Feb"
            Calendar.MARCH -> "Mar"
            Calendar.APRIL -> "Apr"
            Calendar.MAY -> "May"
            Calendar.JUNE -> "Jun"
            Calendar.JULY -> "Jul"
            Calendar.AUGUST -> "Aug"
            Calendar.SEPTEMBER -> "Sep"
            Calendar.OCTOBER -> "Oct"
            Calendar.NOVEMBER -> "Nov"
            Calendar.DECEMBER -> "Dec"
            else -> ""
        }
        val year = cal[Calendar.YEAR]
        return "$month ${cal[Calendar.DAY_OF_MONTH]}, $year at ${if (cal[Calendar.HOUR] == 0) "12" else "${cal[Calendar.HOUR]}"}:${if (cal[Calendar.MINUTE] < 10) "0${cal[Calendar.MINUTE]}" else "${cal[Calendar.MINUTE]}"} ${if (cal[Calendar.AM_PM] == 0) "AM" else "PM"}"
    }
}