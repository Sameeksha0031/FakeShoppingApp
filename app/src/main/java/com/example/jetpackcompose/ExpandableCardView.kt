package com.example.jetpackcompose

import android.widget.ExpandableListView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ExpandableCardView() {
    val expandableState = remember {
        mutableStateOf(false)
    }
   Box(modifier = Modifier
       .fillMaxWidth()) {
       Card(shape = RoundedCornerShape(4.dp), border = BorderStroke(1.dp, Color.White)) {
           Column(modifier = Modifier.navigationBarsPadding()) {
               Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)) {
                   Column {
                       Text(text = "Boarding Points")
                       Spacer(modifier = Modifier
                           .height(9.dp)
                           .background(Color.Red))
                   }

                   Column {
                       Text(text = "Dropping Points")
                       Spacer(modifier = Modifier
                           .height(9.dp)
                           .background(Color.Red))
                   }
               }

               LazyColumn(modifier = Modifier.fillMaxWidth()) {
                   items(29){ index ->
                       if(index + 1 == 29){
                           Spacer(modifier = Modifier
                               .height(0.5.dp)
                               .background(Color.Black)
                               .fillMaxWidth())

                           Text(text = "Click on me",
                               modifier = Modifier
                                   .clickable {
                                       expandableState.value = !expandableState.value
                                   }
                           )
                       }else if(index < 4) {
                           Text(text = "vbxjkcvbxklcvb $index", modifier = Modifier.padding(9.dp))
                       } else if(index > 4 && expandableState.value){
                           Text(text = "vbxjkcvbxklcvb $index", modifier = Modifier.padding(9.dp))
                       }
                   }
               }
           }
           ExpandableView(isExpanded = expandableState.value)
       }
   }
}

@Composable
fun ExpandableView(isExpanded: Boolean) {
    // Opening Animation
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    // Closing Animation
    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        Box(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "answerText",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}