package com.rrat.animationcompose.ui.componets


import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rrat.animationcompose.R
import com.rrat.animationcompose.ui.screens.TabPage


@Composable
fun HomeTabBar(
    backgroundColor: Color,
    tabPag: TabPage,
    onTabSelected: (tabPage: TabPage) -> Unit
){
    TabRow(
        selectedTabIndex = tabPag.ordinal,
        backgroundColor = backgroundColor,
        indicator = {
            tabPosition ->
            HomeTabIndicator(tabPage = tabPag, tabPositions = tabPosition)
        }
    ) {
        HomeTab(
            icon = Icons.Default.Home,
            title = stringResource(id = R.string.home),
            onClick = { onTabSelected(TabPage.Home) }
        )
        HomeTab(
            icon = Icons.Default.AccountBox,
            title = stringResource(id = R.string.work),
            onClick = {onTabSelected(TabPage.Work)}
        )
    }
}

@Preview
@Composable
fun HomeTabBarPreview()
{
    HomeTabBar(
        tabPag = TabPage.Home,
        onTabSelected = {},
        backgroundColor = MaterialTheme.colors.background
    )
}