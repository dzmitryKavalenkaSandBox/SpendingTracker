package com.dzka.spendingtracker.presentation.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun GeneralDialog(
    title: String,
    message: String? = null,
    positiveBtnText: String,
    negativeBtnText: String? = null,
    onDismissRequest: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties(),
    onPositiveBtnClicked: () -> Unit = {},
    onNegativeBtnClicked: (() -> Unit)? = null,
    dialogState: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = {
            dialogState.value = false

            onDismissRequest?.invoke()
        },
        properties = properties,
    ) {
        Box(Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 8.dp
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
                        text = title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    if (message != null) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                            text = message,
                            fontSize = 15.sp,
                            color = Color(0xFF677987),
                            textAlign = TextAlign.Center,
                            lineHeight = 23.sp
                        )
                    }
                    Column {
                        content()
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Row {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (negativeBtnText != null) {
                                OutlinedButton(
                                    modifier = Modifier
                                        .weight(.5f),
                                    onClick = {
                                        dialogState.value = false
                                        onNegativeBtnClicked?.invoke()
                                    }
                                ) {
                                    Text(negativeBtnText)
                                }

                                Spacer(modifier = Modifier.width(16.dp))
                            }

                            Button(
                                modifier = Modifier
                                    .weight(.5f),
                                onClick = {
                                    onPositiveBtnClicked()
                                }
                            ) {
                                Text(positiveBtnText)
                            }
                        }
                    }
                }
            }
        }
    }
}