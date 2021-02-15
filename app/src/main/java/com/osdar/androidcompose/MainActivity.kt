package com.osdar.androidcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.osdar.androidcompose.ui.theme.AndroidComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                LayoutCodeLab()
            }
        }
    }


    @Preview
    @Composable
    fun PreviewFun() {
        MyApp {
            LayoutCodeLab()
        }
    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        LayoutCodeLab()
    }


//
//    @Composable
//    fun MyOwnColumn(
//        modifier: Modifier = Modifier,
//        children: @Composable () -> Unit
//    ) {
//
//        Layout(
//            modifier = modifier,
//            children = children
//        ) { measurables, constraints ->
//            // Don't constrain child views further, measure them with given constraints
//            // List of measured children
//            val placeables = measurables.map { measurable ->
//                // Measure each children
//                measurable.measure(constraints)
//            }
//
//            // Track the y co-ord we have placed children up to
//            var yPosition = 0
//
//            // Set the size of the layout as big as it can
//            layout(constraints.maxWidth, constraints.maxHeight) {
//                // Place children in the parent layout
//                placeables.forEach { placeable ->
//                    // Position item on the screen
//                    placeable.placeRelative(x = 0, y = yPosition)
//
//                    // Record the y co-ord placed up to
//                    yPosition += placeable.height
//                }
//            }
//        }
//    }

    private fun Modifier.customLayoutModifier(firstBaselineToTop: Dp) =
        Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            // Check the composable has a first baseline
            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
            val firstbaseline = placeable[FirstBaseline]

            // Height of the composable with padding - first baseline
            val placeableY = firstBaselineToTop.toIntPx() - firstbaseline
            val height = placeable.height + placeableY
            layout(placeable.width, height) {
                // Where the composable gets placed
                placeable.placeRelative(0, placeableY)
            }

        }

    @Composable
    fun LayoutCodeLab() {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Layout code lab")
            },
                actions = {
                    IconButton(onClick = { }) {
//                        Icon(Icons.Filled.Favorite)
                    }
                })
        }) { innerPadding ->
            ScaffoldContent(innerPadding)

        }
    }

    @Composable
    private fun ScaffoldContent(innerPadding: PaddingValues) {
        Row(Modifier.padding(innerPadding)) {
            Text(text = "Hi there", modifier = Modifier.customLayoutModifier(32.dp))
            Text(text = "Hi Osama, How are you?", modifier = Modifier.customLayoutModifier(32.dp))
        }
    }

    @Composable
    fun MyLayoutContent(modifier: Modifier = Modifier) {
        Row(
            modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
//                .background(MaterialTheme.colors.surface)
                .clickable(onClick = { })
                .padding(16.dp)
        ) {
            Surface(
                modifier = Modifier.preferredSize(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.surface.copy(alpha = 0.2f)
            ) {

            }

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "Osama Darabeh", fontWeight = FontWeight.Bold)
                Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                    Text(text = "3 min ago", style = MaterialTheme.typography.body2)
                }
            }
        }
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
            itemsIndexed(names) { _, name ->
                run {
                    Greeting(name)
                    Divider(color = Color.Black)
                }
            }
        }
    }

    @Composable
    private fun MyScreenContent(
        names: List<String> = listOf(
            "Osama",
            "Darabeh",
            "Darabeh",
            "Darabeh"
        )
    ) {
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
        var isSelected = remember { mutableStateOf(false) }
        val backgroundColor =
//            animateAsState(if (isSelected.value) Color.Red else Color.Transparent)
        Column(modifier = Modifier.padding(16.dp)) {
            val imageModifier = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp))
//            Image(
//                image,
//                modifier = imageModifier,
//                contentScale = ContentScale.Crop
//            )

            Spacer(modifier = Modifier.preferredHeight(16.dp))

            Text(
                text = "A day wandering through the sandhills \n" +
                        "in Shark Fin Cove, and a few of the \n" +
                        "sights I saw $text",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(2.dp)
//                    .background(color = backgroundColor.value)
                    .clickable(onClick = { isSelected.value = (!isSelected.value) })
            )
            Text(text = "Hello $text", style = typography.body2)
            Text(text = "Hello $text", style = typography.body2)

        }
    }
}


