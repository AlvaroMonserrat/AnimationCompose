package com.rrat.animationcompose.ui.componets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics


@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        modifier = modifier.semantics { heading() },
        text = text,
        style = MaterialTheme.typography.h5
    )
}