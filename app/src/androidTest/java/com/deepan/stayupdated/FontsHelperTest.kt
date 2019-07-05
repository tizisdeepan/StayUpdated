package com.deepan.stayupdated

import androidx.test.rule.ActivityTestRule
import com.deepan.stayupdated.helpers.FontsConstants
import com.deepan.stayupdated.helpers.FontsHelper
import com.deepan.stayupdated.list.view.HeadlinesActivity
import junit.framework.TestCase.assertFalse
import org.junit.Rule
import org.junit.Test

class FontsHelperTest {

    @get:Rule
    var activityRule: ActivityTestRule<HeadlinesActivity> = ActivityTestRule(HeadlinesActivity::class.java)

    @Test
    fun testFontsAvailability() {
        assertFalse(FontsHelper[activityRule.activity, FontsConstants.BOLD] == null)
        assertFalse(FontsHelper[activityRule.activity, FontsConstants.SEMIBOLD] == null)
        assertFalse(FontsHelper[activityRule.activity, FontsConstants.ITALIC_SEMIBOLD] == null)
        assertFalse(FontsHelper[activityRule.activity, FontsConstants.REGULAR] == null)
    }
}