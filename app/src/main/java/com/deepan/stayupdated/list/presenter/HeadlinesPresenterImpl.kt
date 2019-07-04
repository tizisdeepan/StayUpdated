package com.deepan.stayupdated.list.presenter

import android.util.Log
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.model.HeadlinesInteractImpl
import com.deepan.stayupdated.list.view.HeadlinesContract

class HeadlinesPresenterImpl(var contract: HeadlinesContract) : HeadlinesPresenter {
    var page = 0
    private val interact: HeadlinesInteractImpl by lazy { HeadlinesInteractImpl(this) }
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