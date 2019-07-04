package com.deepan.stayupdated.list.presenter

import com.deepan.stayupdated.list.model.NewsListInteractImpl
import com.deepan.stayupdated.list.view.NewsListContract

class NewsListPresenterImpl(var contract: NewsListContract): NewsListPresenter {
    val interact: NewsListInteractImpl by lazy { NewsListInteractImpl(this) }
}