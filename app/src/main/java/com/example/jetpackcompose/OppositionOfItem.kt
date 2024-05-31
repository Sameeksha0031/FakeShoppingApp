package com.example.jetpackcompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.example.jetpackcompose.ui.theme.FlexboxColor
import kotlin.math.roundToInt


@Composable
fun LazyColumnVisibilityExample() {
    val listState = rememberLazyListState()
    val visibleItemsInfo = remember { derivedStateOf { listState.layoutInfo.visibleItemsInfo} }

    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        items(4) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        val visibleItem = visibleItemsInfo.value.find { it.index == index }
                        if (visibleItem != null) {
                            // The item is visible
                            Log.d("Visibility", "Item $index is visible")
                        }
                    }
            ) {
                when(index) {
                    0 -> LayoutForAmenities()
                    1 -> Text("Item $index B")
                    2 -> Text("Item $index C")
                    3 -> Text("Item $index D")
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun FlowRow(
    horizontalGap: Dp = 0.dp,
    verticalGap: Dp = 0.dp,
    alignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit,
) = Layout(content = content) { measurables, constraints ->
    val horizontalGapPx = horizontalGap.toPx().roundToInt()
    val verticalGapPx = verticalGap.toPx().roundToInt()

    val rows = mutableListOf<Row>()
    var rowConstraints = constraints
    var rowPlaceables = mutableListOf<Placeable>()

    measurables.forEach { measurable ->
        val placeable = measurable.measure(Constraints())
        if (placeable.measuredWidth !in rowConstraints.minWidth..rowConstraints.maxWidth) {
            rows += Row(rowPlaceables, horizontalGapPx)
            rowConstraints = constraints
            rowPlaceables = mutableListOf()
        }
        val consumedWidth = placeable.measuredWidth + horizontalGapPx
        rowConstraints = rowConstraints.offset(horizontal = -consumedWidth)
        rowPlaceables.add(placeable)
    }
    rows += Row(rowPlaceables, horizontalGapPx)

    val width = constraints.maxWidth
    val height = (rows.sumOf { row -> row.height } + (rows.size - 1) * verticalGapPx)
        .coerceAtMost(constraints.maxHeight)

    layout(width, height) {
        var y = 0
        rows.forEach { row ->
            val offset = alignment.align(row.width, width, layoutDirection)
            var x = offset
            row.placeables.forEach { placeable ->
                placeable.placeRelative(x, y)
                x += placeable.width + horizontalGapPx
            }
            y += row.height + verticalGapPx
        }
    }
}

private class Row(
    val placeables: List<Placeable>,
    val horizontalGapPx: Int,
) {
    val width by lazy(mode = LazyThreadSafetyMode.NONE) {
        placeables.sumBy { it.width } + (placeables.size - 1) * horizontalGapPx
    }

    val height by lazy(mode = LazyThreadSafetyMode.NONE) {
        placeables.maxOfOrNull { it.height } ?: 0
    }
}

@Composable
private fun Preview(alignment: Alignment.Horizontal) {
    Box(Modifier.width(100.dp)) {
        FlowRow(
            horizontalGap = 8.dp,
            verticalGap = 8.dp,
            alignment = alignment,
        ) {
            repeat(17) { index ->
                Text(text = index.toString())
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAlignStart() = Preview(alignment = Alignment.Start)

@Preview
@Composable
private fun PreviewAlignCenter() = Preview(alignment = Alignment.CenterHorizontally)

@Preview
@Composable
private fun PreviewAlignEnd() = Preview(alignment = Alignment.End)

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun LayoutForAmenities() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Amenities", fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(5.dp))
        Spacer(modifier = Modifier.height(18.dp))
        Box() {
            FlowRow(
                horizontalGap = 8.dp,
                verticalGap = 8.dp,
                alignment = Alignment.Start,
            ) {
                repeat(17) { index ->
                    Card(onClick = {},
                        backgroundColor = FlexboxColor,
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        if(index%2 == 0) {
                            Row(modifier = Modifier
                                .padding()
                                .padding(10.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_home_24),
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.width(1.dp))
                                Text(text = "afafafsdfsdf",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(4.dp))
                            }
                        } else {
                            Row(modifier = Modifier
                                .padding()
                                .padding(10.dp)
                                .align(Alignment.Center)) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_home_24),
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.width(1.dp))
                                Text(text = "dskf",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().padding(7.dp,15.dp,7.dp,15.dp).height(0.5.dp).background(Color.White))
    }
}


