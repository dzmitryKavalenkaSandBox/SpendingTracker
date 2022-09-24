package com.dzka.spendingtracker.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dzka.spendingtracker.data.local.dao.SpendingAccountDao
import com.dzka.spendingtracker.data.local.dao.SpendingCategoryDao
import com.dzka.spendingtracker.data.local.model.*
import com.dzka.spendingtracker.data.local.model.typeconverters.Converters
import com.dzka.spendingtracker.data.local.model.typeconverters.SpendingAccountConverter
import com.dzka.spendingtracker.data.local.model.typeconverters.SpendingItemConverter
import com.dzka.spendingtracker.data.local.model.typeconverters.SpendingSubCategoryConverter

@Database(
    entities = [OverallBudget::class, SpendingAccount::class,
        SpendingCategory::class, SpendingItem::class, SpendingSubCategory::class], version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class, SpendingAccountConverter::class, SpendingItemConverter::class,
    SpendingSubCategoryConverter::class)
abstract class SpendingTrackerDatabase : RoomDatabase() {

    abstract fun spendingCategoryDao(): SpendingCategoryDao
    abstract fun spendingAccountDao(): SpendingAccountDao

    companion object {
        private var Instance: SpendingTrackerDatabase? = null

        fun getDataBase(context: Context): SpendingTrackerDatabase {
            if (Instance == null) {
                Instance = Room.databaseBuilder(
                    context,
                    SpendingTrackerDatabase::class.java,
                    "spending_tracker_db"
                ).build()
            }
            return Instance as SpendingTrackerDatabase
        }
    }
}