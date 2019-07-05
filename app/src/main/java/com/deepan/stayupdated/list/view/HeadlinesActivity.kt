package com.deepan.stayupdated.list.view

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepan.stayupdated.R
import com.deepan.stayupdated.helpers.FontsConstants
import com.deepan.stayupdated.helpers.FontsHelper
import com.deepan.stayupdated.helpers.NetworkUtil
import com.deepan.stayupdated.helpers.RunOnUiThread
import com.deepan.stayupdated.list.model.Category
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
        titleColor = resources.getColor(R.color.white)
        title = filter.category.getValue(this) + " " + this.resources.getString(R.string.headlines)

        headlinesRecyclerView.layoutManager = LinearLayoutManager(this)
        headlinesRecyclerView.itemAnimator = null
        headlinesRecyclerView.adapter = HeadlinesAdapter(ArrayList())

        headlinesRecyclerView.addOnScrollListener(object :
                                                      androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((headlinesRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == (headlinesRecyclerView.layoutManager as LinearLayoutManager).itemCount - 1) {
                    fetchData(false)
                }
            }
        })

        refresh.setOnRefreshListener {
            fetchData(true)
        }

        fetchData(false)

        errorLabel.typeface = FontsHelper[this, FontsConstants.BOLD]
    }

    private fun fetchData(isRefresh: Boolean) {
        presenter.getHeadlines(NetworkUtil.isConnected(this), filter, isRefresh)
    }

    override fun getFilterModel(): Filter = filter

    fun updateFilterCategory(category: Category?) {
        if (category != null) {
            filter.category = category.type
            title = filter.category.getValue(this) + " " + this.resources.getString(R.string.headlines)
            refresh.isRefreshing = true
            fetchData(true)
        }
    }

    override fun endRefresh() {
        RunOnUiThread(this).safely {
            if (refresh.isRefreshing) refresh.isRefreshing = false
        }
    }

    override fun startRefresh() {
        RunOnUiThread(this).safely {
            if (!refresh.isRefreshing) refresh.isRefreshing = true
        }
    }

    override fun updateHeadlines(headlines: ArrayList<Headline>) {
        RunOnUiThread(this).safely {
            errorLabel.visibility = View.GONE
            (headlinesRecyclerView.adapter as? HeadlinesAdapter)?.updateItems(headlines)
        }
    }

    override fun showError(error: String) {
        RunOnUiThread(this).safely {
            if (error.isNotEmpty()) {
                updateHeadlines(ArrayList())
                errorLabel.visibility = View.VISIBLE
                errorLabel.text = error
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.filter -> {
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("FILTER", filter)
                }
            }.show(supportFragmentManager, "Filter")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun getMyContext(): Context = this
}
