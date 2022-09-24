package com.dzka.spendingtracker.model.typeconverters

import androidx.room.TypeConverter
import com.dzka.spendingtracker.model.SpendingAccount
import com.squareup.moshi.Types

class SpendingAccountConverter : TypeConverters() {

    private val type by lazy {
        Types.newParameterizedType(SpendingAccount::class.java)
    }
    private val adapter by lazy { moshi.adapter<SpendingAccount>(type) }

    @TypeConverter
    fun fromJson(string: String): SpendingAccount? {
        if (string.isEmpty()) {
            return null
        }

        return adapter.fromJson(string)
    }

    @TypeConverter
    fun toJson(spendingAccount: SpendingAccount): String {
        return adapter.toJson(spendingAccount)
    }

}