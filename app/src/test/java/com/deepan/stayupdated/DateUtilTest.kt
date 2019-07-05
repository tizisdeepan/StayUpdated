package com.deepan.stayupdated

import com.deepan.stayupdated.helpers.DateUtil
import org.junit.Test
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse

class DateUtilTest {

    @Test
    fun testDateStringSuccess() {
        val dateString = DateUtil().getDateString("2019-07-04T18:04:35Z")
        assertTrue("testDateStringSuccess Validation Failed", dateString == "Jul 4, 2019 at 11:34 PM")
    }

    @Test
    fun testDateStringFailure() {
        val dateString = DateUtil().getDateString("2019-07-04T18:04:35Z")
        assertFalse("testDateStringFailure Validation Failed", dateString == "Jul 4, 2019 at 11:35 PM")
    }
}