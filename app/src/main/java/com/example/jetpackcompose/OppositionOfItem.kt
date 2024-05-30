package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat

@Composable
fun ColumnWithPositions() {
    val textPositions = remember { mutableStateListOf<Pair<Int, IntSize>>() }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState,enabled = true)) {
        for (i in 1..15) {
            TextWithPosition(text = "Item $i", index = i, onPositioned = { index, size ->
                // Update the position in the list
                if (textPositions.size < index) {
                    textPositions.add(Pair(index - 1, size))
                } else {
                    textPositions[index - 1] = Pair(index, size)
                }
            })
        }
    }

    // Logging the positions for verification
    LaunchedEffect(textPositions) {
        textPositions.forEachIndexed { index, position ->
            val (index, size) = position
            Log.d("PositionOfText","Text $index is at position $size")
        }
    }
}

@Composable
fun TextWithPosition(text: String, index: Int, onPositioned: (Int, IntSize) -> Unit) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Text(
        text = text,
        modifier = Modifier
            .height(600.dp)
            .padding(vertical = 8.dp)
            .onGloballyPositioned { coordinates ->
                size = coordinates.size
                onPositioned(index, size)
            }
    )
}


@Composable
fun LazyColumnVisibilityExample() {
    val listState = rememberLazyListState()
    val visibleItemsInfo = remember { derivedStateOf { listState.layoutInfo.visibleItemsInfo} }

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        items(4) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .onGloballyPositioned { coordinates ->
                        val visibleItem = visibleItemsInfo.value.find { it.index == index }
                        if (visibleItem != null) {
                            // The item is visible
                            Log.d("Visibility", "Item $index is visible")
                        }
                    }
            ) {
                when(index) {
                    0 -> Text("Item $index A")
                    1 -> Text("Item $index B")
                    2 -> Text("Item $index C")
                    3 -> Text("Item $index D")
                    else -> {}
                }
            }
        }
    }
}

