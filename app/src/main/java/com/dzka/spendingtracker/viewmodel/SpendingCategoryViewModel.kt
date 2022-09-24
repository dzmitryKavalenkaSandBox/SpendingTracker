package com.dzka.spendingtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dzka.spendingtracker.database.SpendingTrackerDatabase
import com.dzka.spendingtracker.model.SpendingCategory
import com.dzka.spendingtracker.repository.SpendingCategoryRepository
import kotlinx.coroutines.launch

class SpendingCategoryViewModel(application: Application) : AndroidViewModel(application) {

    val allCategories: LiveData<List<SpendingCategory>>
    private val repository: SpendingCategoryRepository
    var newCategory: SpendingCategory? = null

    init {
        val categoryDao = SpendingTrackerDatabase.getDataBase(application).spendingCategoryDao()
        repository = SpendingCategoryRepository(categoryDao)
        allCategories = repository.allCategories
    }

    fun addCategory(category: SpendingCategory) {
        viewModelScope.launch {
            repository.addCategory(category)
        }
    }

    fun deleteCategory(category: SpendingCategory) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }
}