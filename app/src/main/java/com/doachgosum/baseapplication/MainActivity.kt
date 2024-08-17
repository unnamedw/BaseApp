package com.doachgosum.baseapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.MutableSnapshot
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.doachgosum.baseapplication.ui.theme.BaseApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.annotation.RetentionPolicy
import javax.inject.Inject
import javax.net.ssl.SSLException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initialize()

        setContent {
            BaseApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
            }
        }
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    fun initialize() {
        viewModelScope.launch {
            fetchSomeData()
        }
    }

    private fun fetchSomeData() = CoroutineScope(Dispatchers.IO).launch {
        try {
            delay(500)
            throw Exception("Test")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntrinsicTest() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Hello", fontSize = TextUnit(13f, TextUnitType.Sp))
        Divider(
            modifier = Modifier.width(3.dp)
        )
        Text("Hello", fontSize = TextUnit(20f, TextUnitType.Sp))
    }
}

@Preview(showBackground = true)
@Composable
fun IntrinsicTest2() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Hello", fontSize = TextUnit(13f, TextUnitType.Sp))
        Divider(
            modifier = Modifier.width(3.dp).height(IntrinsicSize.Max)
        )
        Text("Hello", fontSize = TextUnit(20f, TextUnitType.Sp))
    }
}

@Preview(showBackground = true)
@Composable
fun IntrinsicTes3() {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.wrapContentWidth(),text = "Hello", fontSize = TextUnit(13f, TextUnitType.Sp))
        Divider(
            modifier = Modifier.fillMaxHeight().width(3.dp)
        )
        Text(modifier = Modifier.wrapContentWidth(), text = "Hello", fontSize = TextUnit(20f, TextUnitType.Sp))
    }
}

@Preview
@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String = "hello", text2: String = "hello") {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxHeight().width(1.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = text2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumnExample() {
    LazyColumn {
        item {
            Box(Modifier.fillMaxHeight()) {
                Text("test")
            }
        }
    }
    var reads = 0
    val snapshot = Snapshot.takeSnapshot { reads++ }
    snapshot.enter { }
    snapshotFlow {  }
    var mutableState by remember { mutableIntStateOf(1) }
    println(mutableState)
    mutableState = 2
    println(mutableState)
}