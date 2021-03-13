package com.example.androiddevchallenge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
    Surface {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 184.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                text = "Log in with email",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1
            )
            TextField(
                "",
                placeholder = {
                    Text(
                        text = "Email address",
                        style = MaterialTheme.typography.body1
                    )
                },
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                "",
                placeholder = {
                    Text(
                        text = "Password (8+ charactors",
                        style = MaterialTheme.typography.body1
                    )
                },
                onValueChange = { },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
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

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewLogin() {
    MyTheme {
        LoginScreen(onHome = { /*TODO*/ })
    }
}