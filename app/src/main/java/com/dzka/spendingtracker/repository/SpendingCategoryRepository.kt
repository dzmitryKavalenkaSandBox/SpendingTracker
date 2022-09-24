package com.dzka.spendingtracker.repository

import androidx.lifecycle.LiveData
import com.dzka.spendingtracker.dao.SpendingCategoryDao
import com.dzka.spendingtracker.model.SpendingCategory

class SpendingCategoryRepository(private val categoryDao: SpendingCategoryDao) {

    val allCategories: LiveData<List<SpendingCategory>> = categoryDao.getAll()

    suspend fun addCategory(category: SpendingCategory) {
        categoryDao.add(category)
    }

    suspend fun deleteCategory(category: SpendingCategory) {
        categoryDao.delete(category)
    }
}