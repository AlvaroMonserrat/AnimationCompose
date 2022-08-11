package com.rrat.animationcompose.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrat.animationcompose.ui.theme.Green300
import com.rrat.animationcompose.ui.theme.Purple100
import com.rrat.animationcompose.R
import com.rrat.animationcompose.ui.componets.*
import com.rrat.animationcompose.ui.theme.AnimationComposeTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TabPage{
    Home, Work
}

@Composable
fun HomeScreen(){

    val allTasks = stringArrayResource(id = R.array.tasks)
    val allTopics = stringArrayResource(id = R.array.topics).toList()

    var tabPage by remember { mutableStateOf(TabPage.Home) }

    val tasks = remember { mutableStateListOf(*allTasks) }

    val backgroundColor by animateColorAsState(
        targetValue = if (tabPage == TabPage.Home) Purple100 else Green300)

    val lazyListState = rememberLazyListState()

    var editMessageShown by remember { mutableStateOf(false)}

    var extendedPlanet by remember { mutableStateOf<String?>(null) }

    suspend fun showEditMessage(){
        if (!editMessageShown){
            editMessageShown = true
            delay(2500L)
            editMessageShown = false
        }
    }


    var powerLoading by remember { mutableStateOf(false)}

    suspend fun loadWeather()
    {
        if(!powerLoading){
            powerLoading = true
            delay(3000L)
            powerLoading = false
        }
    }

    LaunchedEffect(Unit){
        loadWeather()
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HomeTabBar(
                tabPag = tabPage,
                onTabSelected = {
                    tabPage = it
                },
                backgroundColor = backgroundColor
            )
        },
        backgroundColor = backgroundColor,
        floatingActionButton = {
            HomeFloatingActionButton(extended = lazyListState.isScrollingUp()) {
                coroutineScope.launch{
                        showEditMessage()
                }
            }
        }
    ) {
        paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
            modifier = Modifier.padding(paddingValues),
            state = lazyListState
        ){
            item { Header(stringResource(id = R.string.character)) }
            item { Spacer(modifier=Modifier.height(16.dp))}

            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 2.dp
                ){
                    if(powerLoading)
                    {
                        LoadingRow()
                    }else{
                        CharacterRow {
                            coroutineScope.launch {
                                loadWeather()
                            }
                        }
                    }

                }

            }

            item { Spacer(modifier=Modifier.height(32.dp))}
            item { Header(stringResource(id = R.string.planets)) }
            item { Spacer(modifier=Modifier.height(16.dp))}

            items(allTopics){
                topic->
                PlanetRow(planet = topic, expanded = extendedPlanet==topic) {
                    extendedPlanet = if(extendedPlanet == topic) null else topic
                }
            }
            item { Spacer(modifier=Modifier.height(32.dp))}

            item { Header(text = stringResource(R.string.galaxies)) }
            item { Spacer(modifier=Modifier.height(16.dp))}

            if(tasks.isEmpty())
            {
                item {
                    TextButton(onClick = {
                        tasks.clear()
                        tasks.addAll(allTasks)
                    }) {
                        Text(stringResource(id = R.string.add_tasks))
                    }
                }
            }
            items(count=tasks.size){
                i->
                val task = tasks.getOrNull(i)
                if (task != null) {
                    key(task){
                        TaskRow(task = task) {
                            tasks.remove(task)
                        }
                    }

                }
            }
        }
        EditMessage(shown = editMessageShown)
    }
}



@Composable
fun LazyListState.isScrollingUp(): Boolean{
    var previousIndex by remember(this){ mutableStateOf(firstVisibleItemIndex)}
    var previousScrollOffset by remember(this){ mutableStateOf(firstVisibleItemScrollOffset)}
    return remember(this){
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex){
                previousIndex > firstVisibleItemIndex
            }else{
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Preview(widthDp = 320)
@Composable
fun HomeScreenPreview() {
    AnimationComposeTheme {
        HomeScreen()
    }
}