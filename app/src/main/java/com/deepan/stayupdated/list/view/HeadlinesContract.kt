package com.deepan.stayupdated.list.view

import com.deepan.stayupdated.list.model.Headline

interface HeadlinesContract {
    fun updateHeadlines(headlines: ArrayList<Headline>)
    fun showError()
    fun endRefresh()
}