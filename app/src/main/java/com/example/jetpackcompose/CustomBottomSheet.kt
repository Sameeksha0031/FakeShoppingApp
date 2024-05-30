package com.example.jetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SamBottomSheet(){
    val bottomSheetState = rememberBottomSheetScaffoldState(bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )) //rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isBottomSheetVisible by remember {
        derivedStateOf { bottomSheetState.bottomSheetState.isExpanded}
    }


    BottomSheetScaffold(
        sheetContent = {
            samBottomContent()
        }, scaffoldState = bottomSheetState,
        sheetGesturesEnabled = true,
        backgroundColor = Color.Gray
    ) {
        if (isBottomSheetVisible) {
            //Anima
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SameeBottomSheet(){
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(sheetContent = { samBottomContent() },
        sheetState = bottomSheetState,
        sheetGesturesEnabled = true) {
        Button(onClick = { scope.launch{ bottomSheetState.show() } },
            modifier = Modifier.fillMaxWidth()) {
            Text("Cancel")
        }
    }
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
            .fillMaxWidth()
            .heightIn((-10).dp, peekHeight)// Peek height applied here
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Adjust max height as needed
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

            // Add more views as needed
        }
    }
}

