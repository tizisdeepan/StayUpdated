package com.deepan.stayupdated.list.presenter

import android.util.Log
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.model.database.HeadlinesDbManager
import com.deepan.stayupdated.list.model.database.HeadlinesDbManipulation
import com.deepan.stayupdated.list.model.interact.HeadlinesInteractImpl
import com.deepan.stayupdated.list.view.HeadlinesContract
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class HeadlinesPresenterImpl(var contract: HeadlinesContract) : HeadlinesPresenter {
    private var page = 1
    private val interact: HeadlinesInteractImpl by lazy { HeadlinesInteractImpl() }
    private val allHeadlines: ArrayList<Headline> = ArrayList()
    private var isLoading = false

    override fun getHeadlines(isConnected: Boolean, filter: Filter, isRefresh: Boolean) {
        if (isConnected) {
            if (isRefresh) {
                page = 1
                allHeadlines.clear()
            }
            if (page != -1 && !isLoading) {
                isLoading = true
                contract.startRefresh()
                interact.getHeadlines(filter, page, this::getHeadlinesOnSuccess, this::getHeadlinesOnFailure)
            }
        } else getHeadlinesOnFailure("No Internet Available!")
    }

    override fun getHeadlinesOnSuccess(headlines: ArrayList<Headline>) {
        isLoading = false
        contract.endRefresh()
        if (headlines.isNotEmpty()) {
            page++
            allHeadlines.addAll(headlines)
            contract.updateHeadlines(allHeadlines)
            updateCachedData()
        } else page = -1
    }

    override fun getHeadlinesOnFailure(error: String) {
        val headlines = HeadlinesDbManager(contract.getMyContext()).getHeadlines(contract.getFilterModel().category.getValue(contract.getMyContext()))
        if (headlines.isNotEmpty()) Log.e("First", headlines[0].toString())
        isLoading = false
        contract.endRefresh()
        contract.updateHeadlines(headlines)
        if (headlines.isEmpty()) contract.showError()
        Log.e("Error Occured", error)
    }

    private fun updateCachedData() {
        val manager = HeadlinesDbManipulation(contract.getMyContext())
        manager.clearHeadlinesDb(contract.getFilterModel().category.getValue(contract.getMyContext()))
        manager.insertSNData(contract.getFilterModel().category.getValue(contract.getMyContext()), Gson().toJson(allHeadlines))
    }
}