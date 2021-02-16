package com.osdar.androidcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.osdar.androidcompose.ui.theme.AndroidComposeTheme

class ComplexCustomLayoutActivity : AppCompatActivity() {
    @ExperimentalLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting2()
                }
            }
        }
    }
}

//@Composable
//fun StaggeredGrid(
//    modifier: Modifier = Modifier(),
//    rows: Int = 3,
//    children: @Composable () -> Unit
//) {
//
//}


@Composable
fun Greeting(name: String) {
    ConstraintLayout {

        val (button, text, button2) = createRefs()

        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button) {
            top.linkTo(parent.top, margin = 16.dp)
        }) {
            Text(text = "Button")
        }


        Text(text = "Text", modifier = Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            centerHorizontallyTo(parent)
        })


        val barrier = createEndBarrier(button, text)
        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(button2) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(barrier)
        }) {
            Text(text = "Osama")
        }

    }
}

@ExperimentalLayout
@Composable
fun Greeting2(modifier: Modifier = Modifier) {
    Row(modifier = modifier.preferredHeight(IntrinsicSize.Min)) {

        Text(
            text = "Os",
            modifier = Modifier.weight(1f).padding(start = 4.dp).wrapContentWidth(
                Alignment.Start
            )
        )
        Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().preferredWidth(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)

                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = "text2"
        )
    }
}

@ExperimentalLayout
@Preview(showBackground = true)
@Composable
fun DefaultPreview( ) {
    AndroidComposeTheme {
        Greeting2()
    }
}