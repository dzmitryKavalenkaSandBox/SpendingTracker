package com.dzka.spendingtracker.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "spending_category")
data class SpendingCategory(
    @PrimaryKey
    val id: Int,
    val name: String,
    val plannedAmount: Double = 0.0,
    val spendingSubCategories: List<SpendingSubCategory>
) : Parcelable


@Parcelize
data class SpendingCategoriesParcel(
    val categories: List<SpendingSubCategory>
): Parcelable