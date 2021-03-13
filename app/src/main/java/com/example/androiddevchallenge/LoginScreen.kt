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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LoginScreen(onHome: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 184.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                text = "Log in with email",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1
            )
            Form(
                text = "Email address"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Form(
                text = "Password (8+ charactors"
            )
            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                text = buildAnnotatedString {
                    append("By clicking below, you agree to our ")
                    append(
                        AnnotatedString(
                            "Term of Use",
                            SpanStyle(textDecoration = TextDecoration.Underline)
                        )
                    )
                    append(" and consent ")
                    append("to our")
                    append(
                        AnnotatedString(
                            "Privacy Policy",
                            SpanStyle(textDecoration = TextDecoration.Underline)
                        )
                    )
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )

            MyButton(
                onClick = {
                    onHome()
                },
                "Log in"
            )
        }
    }
}

@Composable
private fun Form(text: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        value = text,
        onValueChange = { s: String -> },
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
fun PreviewLogin() {
    MyTheme {
        LoginScreen(onHome = { /*TODO*/ })
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewLoginDark() {
    MyTheme(true) {
        LoginScreen(onHome = { /*TODO*/ })
    }
}
