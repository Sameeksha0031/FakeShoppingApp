package com.example.jetpackcompose

import android.annotation.SuppressLint
import android.util.Log
import android.view.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackcompose.ui.theme.Palette1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun StartScreenB(
    navController: NavHostController,
    innerPadding: PaddingValues) {
//   Box(
//       Modifier
//           .fillMaxSize()
//           .background(Palette4),
//       Alignment.Center) {
//       Column {
//           Text(text = "ScreenB")
//           Button(onClick = {
//               navController.navigate(BottomNavItem.Profile.title){
//                   launchSingleTop = true
//               }
//           }) {
//               Text(text = "on Click")
//           }
//       }
//   }
//================================================
//    val scope = rememberCoroutineScope()
//    var openBottomSheet by remember { mutableStateOf( false) }
//    val bottomSheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = true
//    )
//
//    Button(onClick = { openBottomSheet = true }) {
//        Text(text = "Show Bottom Sheet")
//    }
//
//    if(openBottomSheet) {
//        ModalBottomSheet(
//            sheetState = bottomSheetState,
//            onDismissRequest = {openBottomSheet = false},
//            dragHandle = {
//                Column(
//                    Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    BottomSheetDefaults.DragHandle()
//                    Text(text = "Comment", style = MaterialTheme.typography.titleLarge)
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Divider()
//                }
//            }) {
//           BottomSheetContent(
//               onHideButtonClick = {
//                   scope.launch {
//                       bottomSheetState.hide()
//                   }.invokeOnCompletion {
//                       if (!bottomSheetState.isVisible) openBottomSheet = false
//                   }
//               }
//           )
//        }
//    }

//    StandardBottomSheetM3(innerPadding,)

//      BottomSheetScreen()
    var direction by remember { mutableStateOf(-1)}
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)


    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val (x, y) = dragAmount
                    when {
                        y > 0 -> {
                            direction = 2
                        }

                        y < 0 -> {
                            direction = 3
                        }
                    }
                }

            }
    ) {
        BottomSheetScreenAnother(direction, bottomSheetState,innerPadding)
    }

}

@Composable
fun BottomSheetContent(
    onHideButtonClick:() -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(50) {
            ListItem(headlineContent = { Text(text = "Item $it") },
                leadingContent = {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                }
            )
        }
        item {
            Button(onClick = { onHideButtonClick },
                modifier = Modifier.fillMaxWidth()) {
                Text("Cancel")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun StandardBottomSheetM3(innerPadding: PaddingValues) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    var text by remember { mutableStateOf("Hello") }
    val scroll = rememberScrollState()
    BottomSheetScaffold(
        modifier = Modifier
            .padding(innerPadding)
            .background(Palette1),
        sheetPeekHeight = 220.dp,
        sheetSwipeEnabled = false,
        sheetContent = {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Palette1)
//                .wrapContentHeight(),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = "Swipe up to expand")
//        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Palette1)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = text,
                onValueChange = { text = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Sheet Content")
            TextField(value = text,
                onValueChange = { text = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Sheet Content")
            TextField(value = text,
                onValueChange = { text = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Sheet Content")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = text,
                onValueChange = { text = it })
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Sheet Content")
            TextField(value = text,
                onValueChange = { text = it })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            },
                modifier = Modifier
                    .padding()
                    .padding(innerPadding)) {
                Text(text = "Click to collapse sheet")
            }
            
        }

    }) {innerPadding ->
        Box(modifier =Modifier.padding(innerPadding) ){
            Text(text = "Scoffold content")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val peekHeight = screenHeight / 2

    BottomSheetScaffold(
        sheetContent = {
            //BottomSheetContent()
        },
        sheetPeekHeight = peekHeight
    ) {
        // Main content of the activity
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Hello World!")
        }
    }
}

@Composable
fun BottomSheetContent(peekHeight: Dp,innerPadding: PaddingValues) {
    val scrollState = rememberScrollState()
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            // Peek height applied here
            .navigationBarsPadding()
            .windowInsetsPadding(WindowInsets.ime),
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 300.dp) // Adjust max height as needed
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Multiple Views inside Bottom Sheet
            Text(text = "Item 1")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 2")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 1")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 2")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 1")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 2")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 1")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 2")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 1")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 2")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 1")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item 2")
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                }
            )

            Button(onClick = {
            },
                modifier = Modifier
                    .padding()
                    .padding(innerPadding)) {
                Text(text = "Click to collapse sheet")
            }



            // Add more views as needed
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreenAnother(
    direction: Int,
    bottomSheetState: ModalBottomSheetState,
    innerPadding: PaddingValues
) {
    Log.d("Drag Gesture", "value of direction = $direction")
    Log.d("Drag Gesture", "value of bottomSheetState = ${bottomSheetState}")
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp + configuration.screenWidthDp.dp
    val peekHeight = configuration.screenHeightDp.dp//screenHeight / 2
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val insets = WindowInsets.ime.asPaddingValues()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheetContent(peekHeight,insets)
        },
        sheetShape = MaterialTheme.shapes.large.copy(
            topStart = CornerSize(16.dp),
            topEnd = CornerSize(16.dp)
        ),
        sheetElevation = 8.dp,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface
    ) {
        // Main content of the activity
        scope.launch {
            if(direction == 3){ bottomSheetState.show()}
            else {bottomSheetState.hide()}
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(onClick = {
                scope.launch {
                    if (bottomSheetState.isVisible) {
                        bottomSheetState.hide()
                    } else {
                        bottomSheetState.show() //bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            }) {
                Text(text = "Toggle Bottom Sheet")
            }
        }
    }
}

@Preview
@Composable
fun terterwt() {

}