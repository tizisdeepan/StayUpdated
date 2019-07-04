package com.deepan.stayupdated.list.model

interface HeadlinesInteract {
    fun getHeadlines(filter: Filter, page: Int, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit)
}