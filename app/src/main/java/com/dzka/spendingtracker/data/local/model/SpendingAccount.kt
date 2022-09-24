package com.dzka.spendingtracker.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "spending_account")
data class SpendingAccount(
    @PrimaryKey
    val name: String,
    val amount: Double?
) : Parcelable