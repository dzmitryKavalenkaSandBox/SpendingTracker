package com.dzka.spendingtracker.data.local.model.typeconverters

import androidx.room.TypeConverter
import com.dzka.spendingtracker.data.local.model.SpendingSubCategory
import com.squareup.moshi.Types

class SpendingSubCategoryConverter : TypeConverters() {

    private val type by lazy {
        Types.newParameterizedType(
            List::class.java, SpendingSubCategory::class.java
        )
    }
    private val adapter by lazy { moshi.adapter<List<SpendingSubCategory>>(type) }

    @TypeConverter
    fun fromJson(string: String): SpendingSubCategory? {
        if (string.isEmpty()) {
            return null
        }

        val jsonAdapter = moshi.adapter(SpendingSubCategory::class.java)
        return jsonAdapter.fromJson(string)
    }

    @TypeConverter
    fun fromJsonToList(string: String): List<SpendingSubCategory> {
        return adapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun toJson(spendingSubCategory: SpendingSubCategory): String {
        val jsonAdapter = moshi.adapter(SpendingSubCategory::class.java)
        return jsonAdapter.toJson(spendingSubCategory)
    }

    @TypeConverter
    fun toJsonFromList(spendingSubCategory: List<SpendingSubCategory>): String {
        return adapter.toJson(spendingSubCategory)
    }

}