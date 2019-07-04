package com.deepan.stayupdated.list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Filter(var category: Categories = Categories.ALL): Parcelable