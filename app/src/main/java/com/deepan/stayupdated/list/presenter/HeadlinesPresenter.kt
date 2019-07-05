package com.deepan.stayupdated.list.presenter

import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline

interface HeadlinesPresenter {
    fun getHeadlines(isConnected: Boolean, filter: Filter, isRefresh: Boolean = false)
    fun getHeadlinesOnSuccess(headlines: ArrayList<Headline>)
    fun getHeadlinesOnFailure(error: String)
}