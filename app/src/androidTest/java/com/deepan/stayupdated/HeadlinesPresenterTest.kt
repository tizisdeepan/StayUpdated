package com.deepan.stayupdated

import androidx.test.rule.ActivityTestRule
import com.deepan.stayupdated.helpers.NetworkUtil
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.presenter.HeadlinesPresenterImpl
import com.deepan.stayupdated.list.view.HeadlinesActivity
import com.deepan.stayupdated.list.view.HeadlinesContract
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HeadlinesPresenterTest {

    @get:Rule
    var activityRule: ActivityTestRule<HeadlinesActivity> = ActivityTestRule(HeadlinesActivity::class.java)
    @Mock
    lateinit var contract: HeadlinesContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testNetworkConnection() {
        val isConnected = NetworkUtil.isConnected(activityRule.activity)
        val presenter = HeadlinesPresenterImpl(contract)
        presenter.getHeadlines(isConnected, Filter(), false)
        Thread.sleep(3000)
        if (!isConnected) {
            verify(contract, times(1)).showError()
            verify(contract, times(1)).endRefresh()
        } else {
            verify(contract, times(1)).updateHeadlines(ArgumentMatchers.anyList<Headline>() as ArrayList<Headline>)
            verify(contract, times(1)).endRefresh()
        }
    }
}