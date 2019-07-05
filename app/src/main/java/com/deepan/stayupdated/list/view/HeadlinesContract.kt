package com.deepan.stayupdated.list.view

import android.content.Context
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline

interface HeadlinesContract {
    fun getMyContext(): Context
    fun updateHeadlines(headlines: ArrayList<Headline>)
    fun showError(error: String)
    fun startRefresh()
    fun endRefresh()
    fun getFilterModel(): Filter
}