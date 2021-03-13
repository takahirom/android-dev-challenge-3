package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.MyTheme

class Theme(val name: String, val resource: Int)
class DesignImage(val name: String, val resource: Int)

val themes = listOf(
    Theme(
        name = "Desert chic",
        resource = R.drawable.plant1
    ),
    Theme(
        name = "Tiny terrariums",
        resource = R.drawable.plant2
    ),
    Theme(
        name = "Jungle vibes",
        resource = R.drawable.plant3
    ),
    Theme(
        name = "Easy care",
        resource = R.drawable.plant4
    ),
    Theme(
        name = "Statements",
        resource = R.drawable.plant5
    ),
)

@Composable
fun HomeScreen() {
    Surface {
        ConstraintLayout {
            val (content, bottomNavigation) = createRefs()
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                Modifier
                    .scrollable(rememberScrollState(), Orientation.Vertical)
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                TextField(
                    "",
                    placeholder = {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.body1
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_search_24),
                            contentDescription = "search"
                        )
                    },
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Browse themes",
                    modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 16.dp),
                )
                LazyRow(content = {
                    itemsIndexed(themes) { index, content ->
                        Card(
                            Modifier.size(136.dp),
                            shape = MaterialTheme.shapes.small,
                            elevation = 1.dp
                        ) {
                            Column {
                                Image(
                                    modifier = Modifier
                                        .height(96.dp)
                                        .fillMaxWidth(),
                                    painter = painterResource(id = content.resource),
                                    contentDescription = content.name,
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Start,
                                        text = content.name
                                    )
                                }
                            }
                        }
                    }
                })
                ConstraintLayout(Modifier.fillMaxWidth()) {
                    val (text, image) = createRefs()
                    Text(
                        text = "Design your home garden",
                        modifier = Modifier
                            .paddingFromBaseline(top = 40.dp)
                            .constrainAs(text) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                    )
                    Image(
                        painterResource(id = R.drawable.ic_baseline_filter_list_24),
                        "filter",
                        Modifier
                            .size(24.dp)
                            .constrainAs(image) {
                                end.linkTo(parent.end, 8.dp)
                                bottom.linkTo(parent.bottom, 8.dp)
                            }
                    )
                }

            }
            BottomNavigation(
                Modifier.constrainAs(bottomNavigation) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                BottomNavigationItem(
                    selected = true,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_home_24),
                            contentDescription = "home"
                        )
                    },
                    onClick = {}
                )
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewHome() {
    MyTheme {
        HomeScreen()
    }
}