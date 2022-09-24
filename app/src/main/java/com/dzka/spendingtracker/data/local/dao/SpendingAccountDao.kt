package com.dzka.spendingtracker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dzka.spendingtracker.data.local.model.SpendingAccount
import com.dzka.spendingtracker.data.local.model.SpendingCategory

@Dao
interface SpendingAccountDao {

    @Insert
    suspend fun add(account: SpendingAccount)

    @Delete
    suspend fun delete(account: SpendingAccount)

    @Query("SELECT * FROM spending_account")
    fun getAll(): LiveData<List<SpendingAccount>>

}