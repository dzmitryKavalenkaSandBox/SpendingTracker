package com.dzka.spendingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.dzka.spendingtracker.composition.NavGraphs
import com.dzka.spendingtracker.composition.destinations.SpendingCategoriesScreenDestination
import com.dzka.spendingtracker.model.SpendingCategory
import com.dzka.spendingtracker.model.SpendingItem
import com.dzka.spendingtracker.model.SpendingSubCategory
import com.dzka.spendingtracker.ui.theme.SpendingTrackerTheme
import com.dzka.spendingtracker.viewmodel.SpendingCategoryViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

class MainActivity : ComponentActivity() {

    private lateinit var spendingCategoryViewModel: SpendingCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spendingCategoryViewModel =
            ViewModelProvider(this)[SpendingCategoryViewModel::class.java]

//        insertSomeData()

        setContent {
            SpendingTrackerTheme {
                DestinationsNavHost(navGraph = NavGraphs.root, dependenciesContainerBuilder = {
                    dependency(SpendingCategoriesScreenDestination) {
                        spendingCategoryViewModel
                    }
                })
            }
        }
    }

    private fun insertSomeData() {
        val categoryToAdd = SpendingCategory(
            id = (0..10000).random(),
            name = "MyOwesomeCategory",
            spendingSubCategories = listOf(
                SpendingSubCategory(
                    name = "SubCategory",
                    spendingItems = listOf(
                        SpendingItem(
                            id = 123,
                            name = "Item",
                            amount = 100.1,
                            spendingAccount = null
                        )
                    )
                )
            )
        )
        spendingCategoryViewModel.addCategory(categoryToAdd)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpendingTrackerTheme {
        Greeting("Android")
    }
}