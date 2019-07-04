package com.deepan.stayupdated.list.presenter

import android.util.Log
import com.deepan.stayupdated.helpers.NetworkUtil
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.model.HeadlinesInteractImpl
import com.deepan.stayupdated.list.view.HeadlinesContract

class HeadlinesPresenterImpl(var contract: HeadlinesContract) : HeadlinesPresenter {
    private var page = 1
    private val interact: HeadlinesInteractImpl by lazy { HeadlinesInteractImpl() }
    private val allHeadlines: ArrayList<Headline> = ArrayList()
    private var isLoading = false

    override fun getHeadlines(filter: Filter, isRefresh: Boolean) {
        if (NetworkUtil.isConnected(contract.getMyContext())) {
            if (isRefresh) {
                page = 1
                allHeadlines.clear()
            }
            if (page != -1 && !isLoading) {
                isLoading = true
                interact.getHeadlines(filter, page, this::getHeadlinesOnSuccess, this::getHeadlinesOnFailure)
            }
        } else getHeadlinesOnFailure("No Internet Available!")
    }

    private fun getHeadlinesOnSuccess(headlines: ArrayList<Headline>) {
        isLoading = false
        contract.endRefresh()
        if (headlines.isNotEmpty()) {
            page++
            allHeadlines.addAll(headlines)
            contract.updateHeadlines(allHeadlines)
        } else page = -1
    }

    private fun getHeadlinesOnFailure(error: String) {
        isLoading = false
        contract.endRefresh()
        Log.e("Error Occured", error)
    }
}