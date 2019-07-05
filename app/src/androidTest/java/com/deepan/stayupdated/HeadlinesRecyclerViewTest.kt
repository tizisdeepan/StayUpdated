package com.deepan.stayupdated

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.deepan.stayupdated.list.view.HeadlinesActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeadlinesRecyclerViewTest {

    @get:Rule
    var activityRule: ActivityTestRule<HeadlinesActivity> = ActivityTestRule(HeadlinesActivity::class.java)

    @Before
    fun setUp() {
        Thread.sleep(5000)
    }

    @Test
    fun testHeadlinesLoadMore() {
        onView(withRecyclerView(R.id.headlinesRecyclerView).atPositionOnView(0, R.id.headlineTitle)).check(matches(not(withText(""))))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}