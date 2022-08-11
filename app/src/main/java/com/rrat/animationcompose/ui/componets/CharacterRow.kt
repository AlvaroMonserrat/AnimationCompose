package com.rrat.animationcompose.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rrat.animationcompose.ui.theme.Amber600
import com.rrat.animationcompose.R

@Composable
fun CharacterRow(
    onRefresh: ()->Unit
)
{
    Row(
        modifier = Modifier
            .heightIn(min = 64.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Amber600)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = stringResource(id = R.string.power), fontSize = 24.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onRefresh) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(id = R.string.refresh)
            )
        }
    }

}

@Preview
@Composable
fun CharacterRowPreview()
{
    CharacterRow(){}
}