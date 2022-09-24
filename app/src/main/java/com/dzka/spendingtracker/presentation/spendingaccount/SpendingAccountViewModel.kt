package com.dzka.spendingtracker.presentation.spendingaccount

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dzka.spendingtracker.data.local.database.SpendingTrackerDatabase
import com.dzka.spendingtracker.data.local.model.SpendingAccount
import com.dzka.spendingtracker.data.local.repository.SpendingAccountRepository
import kotlinx.coroutines.launch

class SpendingAccountViewModel(application: Application) : AndroidViewModel(application) {

    val allAccounts: LiveData<List<SpendingAccount>>
    private val repository: SpendingAccountRepository
    var currentNewAccount: SpendingAccount? = null

    init {
        val spendingAccountDao =
            SpendingTrackerDatabase.getDataBase(application).spendingAccountDao()
        repository = SpendingAccountRepository(spendingAccountDao)
        allAccounts = repository.allAccounts
    }

    fun addAccount() {
        viewModelScope.launch {
            if (!currentNewAccount?.name.isNullOrBlank()) {
                repository.addAccount(currentNewAccount!!)
            }
            currentNewAccount = null
        }
    }

    fun deleteAccount(account: SpendingAccount) {
        viewModelScope.launch {
            repository.deleteAccount(account)
        }
    }

    fun initNewAccountIfNotExist() {
        if (currentNewAccount == null) {
            currentNewAccount = SpendingAccount("", 0.0)
        }
    }
}