package com.deepan.stayupdated.list.presenter

import com.deepan.stayupdated.list.model.Filter

interface HeadlinesPresenter {
    fun getHeadlines(filter: Filter)
}