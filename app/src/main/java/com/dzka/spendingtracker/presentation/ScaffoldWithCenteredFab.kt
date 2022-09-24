package com.dzka.spendingtracker.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dzka.spendingtracker.R

private val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)

@Composable
fun ScaffoldWithCenteredFab(
    fabText: @Composable () -> Unit = {
        Text(text = stringResource(id = R.string.add_string))
    },
    onFabClicked: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val fabShape = RoundedCornerShape(70f)
    Scaffold(
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {}
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = fabText,
                icon = { Icon(Icons.Default.Add, "") },
                onClick = onFabClicked,
                shape = fabShape
            )
        },
        content = content
    )
}

@Preview
@Composable
fun PreviewScaffoldWithCenteredFab() {
    ScaffoldWithCenteredFab(content = { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(count = 50) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .background(colors[it % colors.size])
                )
                Text(text = "Item")
            }
        }
    })
}