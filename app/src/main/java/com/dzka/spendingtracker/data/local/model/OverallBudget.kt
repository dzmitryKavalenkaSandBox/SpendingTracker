package com.dzka.spendingtracker.data.local.model

import androidx.room.Entity
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
@Entity(tableName = "overall_budget", primaryKeys = ["periodFrom", "periodTo"])
data class OverallBudget(
    val periodFrom: Date,
    val periodTo: Date,
    val initialAmount: Double,
    val leftAmount: Double,
)