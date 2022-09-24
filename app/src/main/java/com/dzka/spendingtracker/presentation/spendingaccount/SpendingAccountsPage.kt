package com.dzka.spendingtracker.presentation.spendingaccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dzka.spendingtracker.R
import com.dzka.spendingtracker.presentation.*
import com.dzka.spendingtracker.presentation.destinations.SpendingCategoriesScreenDestination
import com.dzka.spendingtracker.presentation.dialog.GeneralDialog
import com.dzka.spendingtracker.presentation.textfield.BaseTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun SpendingAccountsPage(
    navigator: DestinationsNavigator,
    viewModel: SpendingAccountViewModel,
) {
    val allAccounts by viewModel.allAccounts.observeAsState(
        emptyList()
    )
    val createAccountDialogState = rememberSaveable {
        mutableStateOf(false)
    }
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
    val scope = rememberCoroutineScope()
    val accountNameTextState = rememberSaveable { mutableStateOf("") }
    val accountBudgetTextState = rememberSaveable { mutableStateOf("") }
    BackdropScaffoldBase(
        modifier = Modifier,
        scaffoldState = backdropState,
        topBarTitle = { Text(text = stringResource(id = R.string.spending_accounts)) },
        frontLayerContent = {
            ScaffoldWithCenteredFab(
                fabText = {
                    Text(text = stringResource(id = R.string.add_spending_account))
                },
                onFabClicked = {
                    createAccountDialogState.value = !createAccountDialogState.value
                },
                content = { innerPadding ->
                    if (createAccountDialogState.value) {
                        GeneralDialog(
                            dialogState = createAccountDialogState,
                            title = "Add Account name and budget",
                            positiveBtnText = "Confirm",
                            negativeBtnText = "Abort",
                            onPositiveBtnClicked = {
                                viewModel.addAccount()
                                createAccountDialogState.value = false
                            }
                        ) {
                            BaseTextField(
                                labelStringResource = R.string.account_name,
                                value = accountNameTextState.value,
                                onValueChange = {
                                    accountNameTextState.value = it
                                    viewModel.initNewAccountIfNotExist()
                                    viewModel.currentNewAccount =
                                        viewModel.currentNewAccount?.copy(
                                            name = accountNameTextState.value
                                        )
                                }
                            )
                            BaseTextField(
                                labelStringResource = R.string.account_budget,
                                value = accountBudgetTextState.value,
                                onValueChange = {
                                    accountBudgetTextState.value = it
                                    viewModel.initNewAccountIfNotExist()
                                    viewModel.currentNewAccount =
                                        viewModel.currentNewAccount?.copy(
                                            name = accountBudgetTextState.value
                                        )
                                }
                            )
                        }
                    }
                    if (allAccounts.isEmpty()) {
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
                                painter = painterResource(R.drawable.ic_wallet),
                                alpha = 0.2F,
                                contentDescription = ""
                            )
                        }
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(150.dp),
                    ) {
                        items(allAccounts) { account ->
                            Card(
                                modifier = Modifier.padding(8.dp),
                                shape = MaterialTheme.shapes.medium,
                                elevation = 10.dp
                            ) {
                                Column {
                                    Row {
                                        Text(
                                            modifier = Modifier.padding(4.dp),
                                            text = account.name
                                        )
                                    }
                                    Text(
                                        modifier = Modifier.padding(4.dp),
                                        text = "${account.amount}$ budget"
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
                        navigator.navigate(SpendingCategoriesScreenDestination()) {
                            launchSingleTop = true
                            popUpTo(SpendingCategoriesScreenDestination.route)
                        }
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