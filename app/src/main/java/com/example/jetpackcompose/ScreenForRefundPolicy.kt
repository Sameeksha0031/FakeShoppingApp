package com.example.jetpackcompose

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RefundPolicy() {
    Box(modifier = Modifier.fillMaxWidth()){
        Column {
            Text(text = "Refund Policy", fontWeight = FontWeight.Bold, color = Color.White)

            Card(border = BorderStroke(7.dp,Color.Gray)) {

            }
        }
    }
}

@Preview
@Composable
fun RefundTextLayout(){
    Column {
        Spacer(modifier = Modifier
            .height(0.2.dp)
            .background(Color.White)
            .fillMaxWidth())
        Row {
            Text(text = "djskfjkds",Modifier.padding(8.dp))
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(0.3.dp)
                .background(Color.White)
                )
            Text(text = "djskfjkds",Modifier.padding(8.dp))
        }
        Spacer(modifier = Modifier
            .height(0.2.dp)
            .background(Color.White)
            .fillMaxWidth())
    }
}