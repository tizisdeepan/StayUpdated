package com.deepan.stayupdated.list.view

import com.deepan.stayupdated.list.model.Headline

interface NewsListContract {
    fun updateHeadlines(headlines: ArrayList<Headline>)
}