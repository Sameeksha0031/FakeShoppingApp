package com.example.jetpackcompose

import android.widget.ExpandableListView
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ExpandableCardView() {
    val expandableState = remember {
        mutableStateOf(false)
    }
    val extrasPadding = if(expandableState.value) 48.dp else 0.dp
   Box(modifier = Modifier.fillMaxWidth()) {
       Card(shape = RoundedCornerShape(4.dp), border = BorderStroke(1.dp, Color.White)) {
           Column(modifier = Modifier.padding(bottom = extrasPadding)) {
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
                   items(15){ index ->
                       if(index < 4) {
                           Text(text = "vbxjkcvbxklcvb", modifier = Modifier.padding(9.dp))
                       } else if(index > 4 && expandableState.value){
                           Text(text = "vbxjkcvbxklcvb", modifier = Modifier.padding(9.dp))
                       }
                   }
               }

               Spacer(modifier = Modifier
                   .height(3.dp)
                   .background(Color.Black)
                   .fillMaxWidth())

               Text(text = "Click on me", modifier = Modifier.clickable {

               })

           }
       }
   }
}