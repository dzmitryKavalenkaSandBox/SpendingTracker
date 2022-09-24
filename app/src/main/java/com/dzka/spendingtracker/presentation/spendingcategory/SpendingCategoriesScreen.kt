package com.dzka.spendingtracker.presentation.spendingcategory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.dzka.spendingtracker.R
import com.dzka.spendingtracker.presentation.BackdropScaffoldBase
import com.dzka.spendingtracker.presentation.BasicListItem
import com.dzka.spendingtracker.presentation.ScaffoldWithCenteredFab
import com.dzka.spendingtracker.presentation.destinations.SpendingAccountsPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination(start = true)
@Composable
fun SpendingCategoriesScreen(
    navigator: DestinationsNavigator,
    viewModel: SpendingCategoryViewModel,
) {
    val allCategories by viewModel.allCategories.observeAsState(
        emptyList()
    )
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val scope = rememberCoroutineScope()
    BackdropScaffoldBase(
        modifier = Modifier,
        scaffoldState = backdropState,
        topBarTitle = {
            Text(text = stringResource(id = R.string.spending_categories_page_title))
        },
        frontLayerContent = {
            ScaffoldWithCenteredFab(
                fabText = {
                    Text(text = stringResource(id = R.string.add_category_button_name))
                },
                onFabClicked = {

                },
                content = { innerPadding ->
                    if (allCategories.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(300.dp)
                                    .fillMaxWidth(),
                                painter = painterResource(R.drawable.ic_coin),
                                alpha = 0.2F,
                                contentDescription = ""
                            )
                        }
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(150.dp),
                    ) {
                        items(allCategories) { category ->
                            Card(
                                modifier = Modifier.padding(8.dp),
                                shape = MaterialTheme.shapes.medium,
                                elevation = 10.dp
                            ) {
                                Column {
                                    Row {
                                        Text(
                                            modifier = Modifier.padding(4.dp),
                                            text = category.name
                                        )
                                    }
                                    Text(
                                        modifier = Modifier.padding(4.dp),
                                        text = "$ spent"
                                    )
                                }
                            }
                        }
                    }
                },
            )
        },
        backLayerContent = {
            Column {
                BasicListItem(
                    modifier = Modifier.clickable(
                        role = Role.Button
                    ) {
                        scope.launch { backdropState.conceal() }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_coin),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    text = {
                        Text(text = stringResource(id = R.string.spending_categories_page_title))
                    }
                )
                BasicListItem(
                    modifier = Modifier.clickable(
                        role = Role.Button
                    ) {
                        scope.launch { backdropState.conceal() }
                        navigator.navigate(SpendingAccountsPageDestination()) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_wallet),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    text = { Text(text = stringResource(id = R.string.spending_accounts)) }
                )
            }
        }
    )
}