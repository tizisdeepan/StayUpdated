package com.deepan.stayupdated.list.model

interface NewsListInteract {
    fun getHeadlines(filter: Filter, page: Int, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit)
}