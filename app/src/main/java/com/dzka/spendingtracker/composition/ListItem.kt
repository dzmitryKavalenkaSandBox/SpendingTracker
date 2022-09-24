package com.dzka.spendingtracker.composition

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasicListItem(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    icon: @Composable (() -> Unit)? = null,
) {
    ListItem(
        modifier = modifier,
        icon = icon,
        text = text
    )
}


@Preview
@Composable
fun PreviewListItem() {
    BasicListItem(
        modifier = Modifier,
        text = { Text("Some Text") },
        icon = { Icon(Icons.Outlined.AddCircle, contentDescription = "") }
    )
}