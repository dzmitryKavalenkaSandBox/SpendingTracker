package com.dzka.spendingtracker.data.local.model.typeconverters

import androidx.room.TypeConverter
import com.dzka.spendingtracker.data.local.model.SpendingItem
import com.squareup.moshi.Types

class SpendingItemConverter : TypeConverters() {

    private val type by lazy {
        Types.newParameterizedType(
            List::class.java, SpendingItem::class.java
        )
    }
    private val adapter by lazy { moshi.adapter<List<SpendingItem>>(type) }

    @TypeConverter
    fun fromJson(string: String): SpendingItem? {
        if (string.isEmpty()) {
            return null
        }

        val jsonAdapter = moshi.adapter(SpendingItem::class.java)
        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun fromJsonToList(string: String): List<SpendingItem> {
        return adapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun toJsonFromList(spendingItems: List<SpendingItem>): String {
        return adapter.toJson(spendingItems)
    }
}
