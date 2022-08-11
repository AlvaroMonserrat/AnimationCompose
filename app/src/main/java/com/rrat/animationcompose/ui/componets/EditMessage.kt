package com.rrat.animationcompose.ui.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrat.animationcompose.R


@Composable
fun EditMessage(
    shown: Boolean
){
    AnimatedVisibility(
        visible = shown,
        enter = slideInVertically(
            initialOffsetY = {fullHeight ->  -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        )
                ,
        exit = slideOutVertically(
            targetOffsetY = {fullHeight ->  -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Text(
                text = stringResource(id = R.string.edit_message),
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}

@Preview
@Composable
fun EditMessagePreview()
{
    EditMessage(shown = true)
}