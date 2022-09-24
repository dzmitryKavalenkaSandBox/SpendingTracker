package com.dzka.spendingtracker.presentation.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

const val TEXT_FIELD_PADDING_DP = 4

@Composable
fun BaseTextField(
    labelStringResource: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        TextField(
            modifier = Modifier.padding(TEXT_FIELD_PADDING_DP.dp),
            label = { Text(text = stringResource(id = labelStringResource)) },
            value = value,
            onValueChange = onValueChange
        )
    }
}