package com.deepan.stayupdated.list.presenter

import android.util.Log
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.model.NewsListInteractImpl
import com.deepan.stayupdated.list.view.NewsListContract

class NewsListPresenterImpl(var contract: NewsListContract) : NewsListPresenter {
    var page = 0
    private val interact: NewsListInteractImpl by lazy { NewsListInteractImpl(this) }
    private val allHeadlines: ArrayList<Headline> = ArrayList()

    override fun getHeadlines(filter: Filter) {
        interact.getHeadlines(filter, page, this::getHeadlinesOnSuccess, this::getHeadlinesOnFailure)
    }

    private fun getHeadlinesOnSuccess(headlines: ArrayList<Headline>) {
        page++
        allHeadlines.addAll(headlines)
        contract.updateHeadlines(allHeadlines)
    }

    private fun getHeadlinesOnFailure(error: String) {
        Log.e("Error Occured", error)
    }
}