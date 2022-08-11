package com.rrat.animationcompose.ui.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrat.animationcompose.R


@Composable
fun HomeFloatingActionButton(
    extended: Boolean,
    onClick: ()->Unit
){

    FloatingActionButton(onClick = onClick) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )

            AnimatedVisibility(extended)
            {
                Text(
                    text = stringResource(id = R.string.edit),
                    modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeFloatingActionButtonPreview(){
    HomeFloatingActionButton(true) {}
}