package com.deepan.stayupdated.list.presenter

import com.deepan.stayupdated.list.model.Filter

interface NewsListPresenter {
    fun getHeadlines(filter: Filter)
}