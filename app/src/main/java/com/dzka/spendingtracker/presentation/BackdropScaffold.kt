package com.dzka.spendingtracker.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackdropScaffoldBase(
    modifier: Modifier = Modifier,
    topBarTitle: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    backLayerContent: @Composable () -> Unit,
    scaffoldState: BackdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = scaffoldState, block = { scaffoldState.conceal() })
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = topBarTitle,
                modifier = modifier,
                navigationIcon = {
                    if (scaffoldState.isConcealed) {
                        IconButton(
                            onClick = { coroutineScope.launch { scaffoldState.reveal() } }
                        ) { Icon(Icons.Filled.Menu, contentDescription = "Localized description") }
                    } else {
                        IconButton(
                            onClick = { coroutineScope.launch { scaffoldState.conceal() } }
                        ) { Icon(Icons.Filled.Close, contentDescription = "Localized description") }
                    }
                },
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            )
        },
        backLayerContent = backLayerContent,
        frontLayerContent = frontLayerContent,
    ) {

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewBackdropScaffold() {
    BackdropScaffoldBase(
        topBarTitle = { Text(text = "Top Bar Title") },
        frontLayerContent = {
            Column {
                BasicListItem(
                    modifier = Modifier,
                    icon = { },
                    text = { "234244" }
                )
            }
        },
        backLayerContent = {
            LazyColumn {
                items(5) {
                    ListItem(
                        modifier = Modifier,
                        text = { Text(text = "Menu Item") }
                    )
                }
            }
        }
    )
}