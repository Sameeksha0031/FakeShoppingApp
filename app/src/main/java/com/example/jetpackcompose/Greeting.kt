package com.example.jetpackcompose

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.example.jetpackcompose.ui.theme.Palette1
import com.example.jetpackcompose.ui.theme.Palette3
import com.example.jetpackcompose.ui.theme.Palette4
import java.util.UUID

@Composable
fun Greeting(navController: NavHostController) {
    val activity = LocalView.current.context as Activity
    val backgroundArgb = Palette3.toArgb()
    val wic = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
    wic.isAppearanceLightStatusBars = true
    activity.window.statusBarColor = backgroundArgb
    activity.window.navigationBarColor = backgroundArgb
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    Scaffold(
        modifier = Modifier.wrapContentHeight(),
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                       navController.navigate(BottomNavItem.List.title){
                           launchSingleTop = true
                       }
            },
                modifier = Modifier.padding().padding(0.dp,0.dp,0.dp,95.dp),
                backgroundColor = Palette1
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        },
        drawerContent = { Text(text = "Drawer Menu 1") },
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Palette4)
                .padding(innerpadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContainerView()
        }

    }
}

@Composable
fun ContainerView() {

    var product = arrayListOf<GridItem>()
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,123.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,50.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,110.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,90.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,100.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,120.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,70.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,90.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,60.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,85.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,120.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,70.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,90.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,60.dp))
    product.add(GridItem(UUID.randomUUID().toString(), Palette1,85.dp))

    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(product){
            Log.d("Index","product index of = ${product.indexOf(it)}")
            CardViewForGrid(it)
        }
    }
}

@Composable
fun CardViewForGrid(product: GridItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(product.size)
            .height(product.size),
        elevation = 4.dp,
        backgroundColor = Palette1
    ) {
        Text(text = "  gdfgsgfgsdfgsdfgdfgsdfgsdfgdf   ")
    }
}