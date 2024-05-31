package com.example.jetpackcompose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.BottomSheetColor
import com.example.jetpackcompose.ui.theme.FlexboxColor
import kotlinx.coroutines.launch

@Preview
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

    val radius = (20 * bottomSheetState.currentFraction).dp

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
            ) {}
        },
        modifier = Modifier.navigationBarsPadding()
    )

}

@Preview
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
            .background(BottomSheetColor)
    ) {
        BottomSheetHeader()
        Box(modifier = Modifier.padding(10.dp)){
            LazyColumnVisibilityExample()
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

@Preview
@Composable
fun BottomSheetHeader(){
    Box{
        Column(
            Modifier
                .background(FlexboxColor)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White))
            Spacer(Modifier.height(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                Text("helmbmvbnmlo",color = Color.White,textAlign = TextAlign.Start,modifier = Modifier.weight(2F))
                RatingTicket()
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                Text("helmbmvbnmlo",color = Color.White,textAlign = TextAlign.Start,modifier = Modifier.weight(2F))
                Text("helmbmvbnmlo",color = Color.White,textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1F)
                        .padding()
                        .padding(0.dp, 0.dp, 3.dp, 0.dp),
                    fontSize = 8.sp)
            }
            Spacer(Modifier.height(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier) {
                    ServicesAndPolicy()
                }
                Column(modifier = Modifier) {
                    ServicesAndPolicy()
                }
                Column(modifier = Modifier) {
                    ServicesAndPolicy()
                }
                Column(modifier = Modifier) {
                    ServicesAndPolicy()
                }
            }
            Spacer(Modifier.height(8.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.White))
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun RatingTicket(){
    Card(backgroundColor = Color.Green,
        modifier = Modifier
            .size(35.dp, 15.dp)
            .padding()
            .padding(0.dp, 0.dp, 3.dp, 0.dp),
        shape = RoundedCornerShape(2.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 3.dp, 0.dp)) {
            Image(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White))
            Text("4.3", fontSize = 9.sp,color = Color.White,textAlign = TextAlign.Center,modifier = Modifier
                .weight(1F)
                .padding(0.dp, 3.dp))

        }
    }
}


@Composable
fun ServicesAndPolicy() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.size(65.dp,45.dp)) {
        Image(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24), contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White))
        Text("dsfkghjtfu", fontSize = 14.sp,color = Color.White,textAlign = TextAlign.Center,modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 3.dp, 0.dp, 0.dp))
    }
}






