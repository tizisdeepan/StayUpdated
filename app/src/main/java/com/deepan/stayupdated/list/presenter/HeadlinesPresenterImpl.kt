package com.deepan.stayupdated.list.presenter

import android.util.Log
import com.deepan.stayupdated.R
import com.deepan.stayupdated.list.model.Categories
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.model.database.HeadlinesDbConstants
import com.deepan.stayupdated.list.model.database.HeadlinesDbManager
import com.deepan.stayupdated.list.model.database.HeadlinesDbManipulation
import com.deepan.stayupdated.list.model.interact.HeadlinesInteractImpl
import com.deepan.stayupdated.list.view.HeadlinesContract
import org.jetbrains.anko.doAsync

class HeadlinesPresenterImpl(var contract: HeadlinesContract) : HeadlinesPresenter {
    private var page = 1
    val interact: HeadlinesInteractImpl by lazy { HeadlinesInteractImpl() }
    val allHeadlines: ArrayList<Headline> = ArrayList()
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
        doAsync {
            val headlines = HeadlinesDbManager(contract.getMyContext()).getHeadlines(when (contract.getFilterModel().category) {
                                                                                         Categories.ALL -> contract.getMyContext().resources.getString(R.string.category_all)
                                                                                         Categories.BUSINESS -> contract.getMyContext().resources.getString(R.string.category_business)
                                                                                         Categories.ENTERTAINMENT -> contract.getMyContext().resources.getString(R.string.category_entertainment)
                                                                                         Categories.GENERAL -> contract.getMyContext().resources.getString(R.string.category_general)
                                                                                         Categories.HEALTH -> contract.getMyContext().resources.getString(R.string.category_health)
                                                                                         Categories.SCIENCE -> contract.getMyContext().resources.getString(R.string.category_science)
                                                                                         Categories.SPORTS -> contract.getMyContext().resources.getString(R.string.category_sports)
                                                                                         Categories.TECHNOLOGY -> contract.getMyContext().resources.getString(R.string.category_technology)
                                                                                     })
            if (headlines.isNotEmpty()) {
                isLoading = false
                contract.endRefresh()
                contract.updateHeadlines(headlines)
            } else {
                isLoading = false
                contract.endRefresh()
                contract.showError()
            }
        }
        Log.e("Error Occured", error)
    }

    fun updateCachedData() {
        doAsync {
            val manager = HeadlinesDbManipulation(contract.getMyContext(), HeadlinesDbConstants(when (contract.getFilterModel().category) {
                Categories.ALL -> contract.getMyContext().resources.getString(R.string.category_all)
                Categories.BUSINESS -> contract.getMyContext().resources.getString(R.string.category_business)
                Categories.ENTERTAINMENT -> contract.getMyContext().resources.getString(R.string.category_entertainment)
                Categories.GENERAL -> contract.getMyContext().resources.getString(R.string.category_general)
                Categories.HEALTH -> contract.getMyContext().resources.getString(R.string.category_health)
                Categories.SCIENCE -> contract.getMyContext().resources.getString(R.string.category_science)
                Categories.SPORTS -> contract.getMyContext().resources.getString(R.string.category_sports)
                Categories.TECHNOLOGY -> contract.getMyContext().resources.getString(R.string.category_technology)
            }))
            manager.clearHeadlinesDb()
            allHeadlines.forEach { manager.insertSNData(it) }
        }
    }
}