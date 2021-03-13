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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable(
            route = "welcome",
        ) { backStackEntry ->
            WelcomeScreen {
                navController.navigate("login")
            }
        }
        composable(
            route = "login",
        ) { backStackEntry ->
            LoginScreen {
                navController.navigate("home")
            }
        }
        composable(
            route = "home",
        ) { backStackEntry ->
            HomeScreen()
        }
    }
}

@Composable
fun WelcomeScreen(onLogin: () -> Unit) {
    Surface(color = MaterialTheme.colors.primary) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_light_welcome_bg),
                "background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (image, box, login) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.ic_light_welcome_illos),
                    "illos",
                    modifier = Modifier
                        .padding(
                            start = 88.dp,
                            top = 72.dp
                        )
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 16.dp, end= 16.dp)
                        .constrainAs(box) {
                        top.linkTo(image.bottom, 48.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_light_logo),
                        "logo",
                        modifier = Modifier


                    )
                    Text(
                        text = "Beautiful home garden solutions",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .firstBaselineToTop(32.dp)

                    )
                    Button(
                        { onLogin() },
                        modifier = Modifier
                            .height(48.dp)
                            .firstBaselineToTop(40.dp)
                            .fillMaxWidth()
                        ,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                    ) {
                        Text(text = "Create account")
                    }
                }
                Text(text = "Log in", modifier = Modifier
                    .clickable { onLogin() }
                    .constrainAs(login) {
                        top.linkTo(box.bottom, 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewWelcome() {
    MyTheme {
        WelcomeScreen(onLogin = { /*TODO*/ })
    }
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
): Modifier = Modifier.layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.toPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height.toInt()) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY.toInt())
    }
}

@Composable
fun LoginScreen(onHome: () -> Unit) {
    Button({ onHome() }) {
        Text(text = "Login")
    }
}

@Composable
fun HomeScreen() {
    Text(text = "")
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
