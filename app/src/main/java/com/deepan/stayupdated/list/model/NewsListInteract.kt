package com.deepan.stayupdated.list.model

interface NewsListInteract {
    fun getHeadlines(filter: Filter, offset: String, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit)
}