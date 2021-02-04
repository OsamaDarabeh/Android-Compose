package com.osdar.androidcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osdar.androidcompose.ui.theme.AndroidComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }


    @Preview
    @Composable
    fun PreviewFun() {
        MyApp {
            MyScreenContent()
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        MyScreenContent()
    }

    @Composable
    fun Counter(count: Int, updateCount: (Int) -> Unit) {
        Button(
            onClick = { updateCount(count + 1) },
        ) {
            Text(text = "I clicked to button $count time")
        }

    }

    @Composable
    fun NameList(names: List<String>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(names) { name ->
                run {
                    Greeting(name)
                    Divider(color = Color.Black)
                }
            }
        }
    }

    @Composable
    private fun MyScreenContent(names: List<String> = listOf("Osama", "Darabeh","Darabeh","Darabeh")) {
        val time = remember { mutableStateOf(0) }
        AndroidComposeTheme() {
            Surface(color = Color.Yellow) {
                Column(modifier = Modifier.fillMaxHeight()) {
                    NameList(names = names, Modifier.weight(1f))
                    Counter(count = time.value, updateCount = { newCount ->
                        time.value = newCount
                    })
                }
            }
        }
    }

    @Composable
    fun Greeting(text: String) {
        val image = imageResource(id = R.drawable.header)
        val typography = MaterialTheme.typography

        Column(modifier = Modifier.padding(16.dp)) {
            val imageModifier = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp))
            Image(
                image, modifier = imageModifier, contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            Text(
                text = "A day wandering through the sandhills \n" +
                        "in Shark Fin Cove, and a few of the \n" +
                        "sights I saw $text",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = "Hello $text", style = typography.body2)
            Text(text = "Hello $text", style = typography.body2)

        }
    }
}


