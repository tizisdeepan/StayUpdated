package com.deepan.stayupdated.list.model.interact

import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline

interface HeadlinesInteract {
    fun getHeadlines(filter: Filter, page: Int, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit)
}