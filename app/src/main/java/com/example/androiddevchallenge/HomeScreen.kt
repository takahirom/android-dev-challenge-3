/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.MyTheme

class Theme(val name: String, val resource: Int)
class DesignImage(val name: String, val description: String, val resource: Int)

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

val designImages = listOf(
    DesignImage(
        name = "Monestera",
        description = "This is a description",
        resource = R.drawable.plant6
    ),
    DesignImage(
        name = "Aglaonema",
        description = "This is a description",
        resource = R.drawable.plant7
    ),
    DesignImage(
        name = "Peace lily",
        description = "This is a description",
        resource = R.drawable.plant8
    ),
    DesignImage(
        name = "Fiddle leaf tree",
        description = "This is a description",
        resource = R.drawable.plant9
    ),
    DesignImage(
        name = "Snake plant",
        description = "This is a description",
        resource = R.drawable.plant10
    ),
    DesignImage(
        name = "Pothos",
        description = "This is a description",
        resource = R.drawable.plant11
    ),

)

@Composable
fun HomeScreen() {
    Surface(color = MaterialTheme.colors.background) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (content, bottomNavigation) = createRefs()
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                val image = R.drawable.ic_baseline_search_24
                val text = "Search"
                Field(image, text)
                Text(
                    text = "Browse themes",
                    modifier = Modifier
                        .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.h1
                )
                LazyRow(
                    content = {
                        itemsIndexed(themes) { index, content ->
                            if (index == 0) {
                                Spacer(modifier = Modifier.width(16.dp))
                            }
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
                                            modifier = Modifier
                                                .padding(start = 16.dp)
                                                .fillMaxWidth(),
                                            textAlign = TextAlign.Start,
                                            text = content.name,
                                            style = MaterialTheme.typography.h2
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                )
                ConstraintLayout(Modifier.fillMaxWidth()) {
                    val (text, image) = createRefs()
                    Text(
                        text = "Design your home garden",
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier
                            .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                            .padding(start = 16.dp)
                            .constrainAs(text) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                        contentDescription = "filter",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                        modifier = Modifier
                            .size(24.dp)
                            .constrainAs(image) {
                                end.linkTo(parent.end, 8.dp)
                                bottom.linkTo(text.bottom, 8.dp)
                            }
                    )
                }
                Column {
                    designImages.forEachIndexed { index, content ->
                        ConstraintLayout(Modifier.fillMaxWidth()) {
                            val (texts, checkbox, designImageImage, divider) = createRefs()
                            Image(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .constrainAs(designImageImage) {
                                        start.linkTo(parent.start, 16.dp)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    },
                                painter = painterResource(id = content.resource),
                                contentDescription = content.name,
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                Modifier.constrainAs(texts) {
                                    start.linkTo(designImageImage.end, 16.dp)
                                    top.linkTo(designImageImage.top)
                                }
                            ) {
                                Text(
                                    text = content.name,
                                    style = MaterialTheme.typography.h2,
                                    modifier = Modifier.paddingFromBaseline(top = 24.dp)
                                )
                                Text(
                                    text = content.description,
                                    style = MaterialTheme.typography.body2,
                                )
                            }
                            var state by remember { mutableStateOf(false) }
                            Checkbox(
                                modifier = Modifier
                                    .constrainAs(checkbox) {
                                        end.linkTo(parent.end, 16.dp)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    },
                                checked = state, onCheckedChange = { state = it }
                            )
                            Divider(
                                modifier = Modifier.constrainAs(divider) {
                                    start.linkTo(designImageImage.end, 8.dp)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                }
                            )
                        }
                        Spacer(Modifier.size(8.dp))
                    }
                }
            }
            BottomNavigation(
                Modifier.constrainAs(bottomNavigation) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                class Nav(val title: String, val resource: Int)

                val navs = listOf(
                    Nav("Home", R.drawable.ic_baseline_home_24),
                    Nav("Favorite", R.drawable.ic_baseline_favorite_border_24),
                    Nav("Profile", R.drawable.ic_baseline_account_circle_24),
                    Nav("Cart", R.drawable.ic_baseline_shopping_cart_24),
                )
                var selected by remember { mutableStateOf(0) }
                navs.forEachIndexed { index, nav ->
                    BottomNavigationItem(
                        selected = selected == index,
                        label = {
                            Text(
                                text = nav.title,
                                style = MaterialTheme.typography.caption,
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = nav.resource),
                                contentDescription = nav.title,
                            )
                        },
                        onClick = {
                            selected = index
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun Field(image: Int, text: String) {
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        onValueChange = { s: String -> },
        leadingIcon = {
            Icon(
                painter = painterResource(id = image),
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = text,
                modifier = Modifier.size(16.dp)
            )
        },
        placeholder = {
            Text(
                text = text,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    )
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewHome() {
    MyTheme {
        HomeScreen()
    }
}
