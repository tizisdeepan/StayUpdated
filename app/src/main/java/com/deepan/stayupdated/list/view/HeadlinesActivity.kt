package com.deepan.stayupdated.list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepan.stayupdated.R
import com.deepan.stayupdated.helpers.RunOnUiThread
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import com.deepan.stayupdated.list.presenter.HeadlinesPresenterImpl
import kotlinx.android.synthetic.main.activity_news_list.*

class HeadlinesActivity : AppCompatActivity(), HeadlinesContract {

    private val presenter: HeadlinesPresenterImpl by lazy { HeadlinesPresenterImpl(this) }
    private val filter: Filter by lazy { Filter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        headlinesRecyclerView.layoutManager = LinearLayoutManager(this)
        headlinesRecyclerView.itemAnimator = null
        headlinesRecyclerView.adapter = HeadlinesAdapter(ArrayList())
        presenter.getHeadlines(filter)
    }

    override fun updateHeadlines(headlines: ArrayList<Headline>) {
        RunOnUiThread(this).safely {
            (headlinesRecyclerView.adapter as? HeadlinesAdapter)?.updateItems(headlines)
        }
    }

    override fun showError() {
        RunOnUiThread(this).safely {

        }
    }
}
