package com.dzka.spendingtracker.composition

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.dzka.spendingtracker.R
import com.dzka.spendingtracker.composition.destinations.SpendingAccountsPageDestination
import com.dzka.spendingtracker.composition.destinations.SpendingCategoriesScreenDestination
import com.dzka.spendingtracker.viewmodel.SpendingCategoryViewModel
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
    LaunchedEffect(key1 = backdropState, block = { backdropState.reveal() })
    BackdropScaffold(
        modifier = Modifier,
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
                        navigator.navigate(NavGraphs.root)
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
                        navigator.navigate(SpendingAccountsPageDestination())
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