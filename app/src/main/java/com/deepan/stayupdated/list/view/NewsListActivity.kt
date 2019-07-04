package com.deepan.stayupdated.list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deepan.stayupdated.R
import com.deepan.stayupdated.list.presenter.NewsListPresenterImpl

class NewsListActivity : AppCompatActivity(), NewsListContract {

    val presenter: NewsListPresenterImpl by lazy { NewsListPresenterImpl(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

    }
}
