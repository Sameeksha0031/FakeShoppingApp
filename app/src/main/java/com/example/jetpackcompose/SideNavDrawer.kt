package com.example.jetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.Palette4
import java.util.UUID

@Composable
fun SideNavDrawer() {
    
    val arrayListTwo = arrayListOf<SideDrawerItems>()
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List1"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List2"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List3"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List4"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List5"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List6"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List7"))
    arrayListTwo.add(SideDrawerItems(UUID.randomUUID().toString(),"List8"))
    
    val arrayListOne = arrayListOf<SideDrawerItems>()
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home",arrayListTwo))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home1"))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home2"))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home3",arrayListTwo))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home4"))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home5"))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home6"))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home7",arrayListTwo))
    arrayListOne.add(SideDrawerItems(UUID.randomUUID().toString(),"Home8"))
    
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Palette4)) {
        Column {
           LazyColumn{
               items(arrayListOne){
                   InnerLazyColumn(it)
               }
           }
        }
    }
}

@Composable
fun InnerLazyColumn(it: SideDrawerItems) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .padding(10.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = it.heading, modifier = Modifier.weight(1f))
            if (it.itemList?.isNotEmpty() == true) {
                Image(painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = "ghg",
                    Modifier.clickable {
                        expanded = !expanded
                    })
            }

            if(expanded) {
                it.itemList?.let{ it ->
                    LazyColumn {
                        items(it){
                          InnerLazyColumn(it = it)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun SideNavDrawerfsdf(){
    SideNavDrawer()
}