package com.example.jetpackcompose

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.ListItem
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SamBottomSheet(){
    val bottomSheetState = rememberBottomSheetScaffoldState(bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    ))
    val scope = rememberCoroutineScope()
    var isSheetOpen by remember { mutableStateOf(false) }

    val sheetToggle: () -> Unit = {
        scope.launch {
            if(bottomSheetState.bottomSheetState.isCollapsed){
                bottomSheetState.bottomSheetState.expand()
            } else {
                bottomSheetState.bottomSheetState.collapse()
            }
        }
    }

    val radius = (30 * bottomSheetState.currentFraction).dp

    BottomSheetScaffold(
        sheetContent = {
            SheetContent {
                SheetExpanded {
                    samBottomContent()
                }
            }
        },
        scaffoldState = bottomSheetState,
        sheetGesturesEnabled = true,
        sheetShape =  RoundedCornerShape(topStart = radius, topEnd = radius),
        sheetPeekHeight = 2.dp,
        content = {
            val fraction = bottomSheetState.bottomSheetState.progress
            val targetValue = bottomSheetState.bottomSheetState.targetValue
            val currentValue = bottomSheetState.bottomSheetState.currentValue
            val currentFraction = bottomSheetState.currentFraction

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (((currentFraction > 0.0) && (targetValue == BottomSheetValue.Expanded
                                    && (currentValue == BottomSheetValue.Collapsed)) || (targetValue == BottomSheetValue.Expanded
                                    && (currentValue == BottomSheetValue.Expanded)))
                        )
                            Color.LightGray.copy(alpha = 0.5f)
                        else
                            Color.Transparent
                    )
                    .clickable {
                        if (bottomSheetState.bottomSheetState.isExpanded) {
                            scope.launch {
                                bottomSheetState.bottomSheetState.collapse()
                            }
                        }
                    }
            ) {
                Text(
                    "Main Content",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp
                )
            }
        },
        modifier = Modifier.navigationBarsPadding()
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun samBottomContent() {
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val peekHeight = (screenHeight) * 3 / 4
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth() // Peek height applied here
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
            contentDescription = "",
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Adjust max height as needed
                .verticalScroll(scrollState)
                .padding(10.dp)
        ) {
            // Multiple Views inside Bottom Sheet
            Amenities()
            Spacer(modifier = Modifier.height(16.dp))
            Amenities2()
            Spacer(modifier = Modifier.height(16.dp))
            Amenities3()
            Spacer(modifier = Modifier.height(16.dp))
            Amenities4()
            // Add more views as needed
        }
    }
}

@Composable
fun SheetContent(
    heightFraction: Float = 0.8f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = heightFraction)
    ) {
        content()
    }
}

@Composable
fun SheetExpanded(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        content()
    }
}

@Composable
fun Amenities() {
    Box(
        modifier = Modifier
            .height(60.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Text("hello")
    }
}

@Composable
fun Amenities2(){
    Box(
        modifier = Modifier
            .height(60.dp)
            .background(Color.Cyan),
        contentAlignment = Alignment.Center,
    ) {
        Text("hello")
    }
}

@Composable
fun Amenities3(){
    Box(
        modifier = Modifier
            .height(60.dp)
            .background(Color.Green),
        contentAlignment = Alignment.Center,
    ) {
        Text("hello")
    }
}

@Composable
fun Amenities4(){
    Box(
        modifier = Modifier
            .height(60.dp)
            .background(Color.Magenta),
        contentAlignment = Alignment.Center,
    ) {
        Text("hello")
    }
}

