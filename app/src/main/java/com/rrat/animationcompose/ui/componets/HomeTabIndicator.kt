package com.rrat.animationcompose.ui.componets

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrat.animationcompose.ui.screens.TabPage
import com.rrat.animationcompose.ui.theme.Green800
import com.rrat.animationcompose.ui.theme.Purple700


@Composable
fun HomeTabIndicator(
    tabPage: TabPage,
    tabPositions: List<TabPosition>
){


    val transition = updateTransition(
        targetState = tabPage,
        label = "Tab indicator")


    val indicatorLeft by transition.animateDp(
        label ="Indicator left",
        transitionSpec = {
            if(TabPage.Home isTransitioningTo TabPage.Work){
                spring(stiffness = Spring.StiffnessLow)
            }else{
                spring(stiffness = Spring.StiffnessMedium)
            }
        }
    ) {
        page->
        tabPositions[page.ordinal].left
    }

    val indicatorRight by transition.animateDp(
        label="Indicator right",
        transitionSpec = {
            if(TabPage.Home isTransitioningTo TabPage.Work){
                spring(stiffness = Spring.StiffnessMedium)
            }else{
                spring(stiffness = Spring.StiffnessLow)
            }
        }
    ) {
        page->
        tabPositions[page.ordinal].right
    }

    val color by transition.animateColor(label = "Border color") {
        page -> if (page == TabPage.Home) Purple700 else Green800
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color),
                RoundedCornerShape(4.dp)
            )
    )
}

@Preview
@Composable
fun HomeTabIndicatorPreview(){

}