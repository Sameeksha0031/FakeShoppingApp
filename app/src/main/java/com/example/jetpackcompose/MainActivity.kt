package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.theme.JetPackComposeTheme
import com.example.jetpackcompose.ui.theme.Palette1
import com.example.jetpackcompose.ui.theme.Palette3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackComposeTheme{
                val navController = rememberNavController()
                var checkBottomBarVisibility by remember { mutableStateOf(true) }
               Scaffold(modifier = Modifier.wrapContentHeight(),
                   topBar = {
                       TopAppBar(title = { Text(text = "Top App BAr",
                           color = Color.Black)},
//                navigationIcon = {
//                                 IconButton(onClick = {}){
//                                     Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "backIcon")
//                                 }
//                },
                           backgroundColor = Palette3,
                           contentColor = Color.Black,
                           elevation = 10.dp,
                           modifier = Modifier
                               .statusBarsPadding()
                       )
                   },
                   drawerContent = {
                                   SideNavDrawer()
                   },
                   bottomBar = {
                       if(checkBottomBarVisibility) {
                           BottomAppBar(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                                   .navigationBarsPadding(),
                               cutoutShape = CircleShape,
                               backgroundColor = Palette3,
                           ) {
                               val items = listOf(
                                   BottomNavItem.Home,
                                   BottomNavItem.List,
                                   BottomNavItem.Analytics,
                                   BottomNavItem.Profile
                               )
                               val navStackBackStackEntry by navController.currentBackStackEntryAsState()
                               val currentDestination = navStackBackStackEntry?.destination
                               items.forEach { item ->
                                   AddItem(
                                       screen = item,
                                       navController,
                                       currentDestination
                                   )
                               }
                           }
                       }
                }) { innerPadding ->
                    NavGraphComposable(navController,innerPadding) { checkBottomBarVisibility = it }
               }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.title } == true
    BottomNavigationItem(
        // Text that shows bellow the icon
        label = {
            Text(text = screen.title,
                color = if (selected){
                    Color.White
                }else {
                    Color.Black
                })
        },

        // The icon resource
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title,
                modifier = Modifier.size(30.dp),
                tint = if (selected){
                    Color.White
                }else {
                    Color.Black
                }
            )
        },
        modifier = Modifier
            .padding()
            .padding(0.dp, 0.dp, 0.dp, 0.dp),

        // Display if the icon it is select or not
        selected = true,

        // Always show the label bellow the icon or not
        alwaysShowLabel = true,

        // Click listener for the icon
        onClick = {
            navController.navigate(screen.title){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }

        // Control all the colors of the icon
       // colors = NavigationBarItemDefaults.colors()
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeTheme {
        CardViewForGrid(GridItem("10", Palette1,85.dp))
    }
}