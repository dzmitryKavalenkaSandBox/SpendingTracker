package com.dzka.spendingtracker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dzka.spendingtracker.data.local.model.SpendingCategory

@Dao
interface SpendingCategoryDao {

    @Insert
    suspend fun add(category: SpendingCategory)

    @Delete
    suspend fun delete(category: SpendingCategory)

    @Query("SELECT * FROM spending_category")
    fun getAll(): LiveData<List<SpendingCategory>>

}