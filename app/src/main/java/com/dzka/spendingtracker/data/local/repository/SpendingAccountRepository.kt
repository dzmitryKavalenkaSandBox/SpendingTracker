package com.dzka.spendingtracker.data.local.repository

import androidx.lifecycle.LiveData
import com.dzka.spendingtracker.data.local.dao.SpendingAccountDao
import com.dzka.spendingtracker.data.local.model.SpendingAccount

class SpendingAccountRepository(private val accountDao: SpendingAccountDao) {

    val allAccounts: LiveData<List<SpendingAccount>> = accountDao.getAll()

    suspend fun addAccount(category: SpendingAccount) {
        accountDao.add(category)
    }

    suspend fun deleteAccount(category: SpendingAccount) {
        accountDao.delete(category)
    }
}