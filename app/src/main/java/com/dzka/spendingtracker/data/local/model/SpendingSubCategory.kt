package com.dzka.spendingtracker.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "spending_sub_category")
data class SpendingSubCategory(
    @PrimaryKey
    val name: String,
    val spendingItems: List<SpendingItem>
) : Parcelable