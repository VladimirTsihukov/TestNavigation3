package com.tishukoff.testingnavigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.tishukoff.testingnavigation3.feature.detail.DetailScreen
import com.tishukoff.testingnavigation3.ui.theme.TestingNavigation3Theme
import kotlinx.serialization.Serializable


@Serializable
data object ProductList : NavKey

@Serializable
data class ProductDetails(val productId: String) : NavKey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingNavigation3Theme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    val backStack: NavBackStack = rememberNavBackStack(ProductList)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<ProductList> { ProductListScreen { backStack.add(ProductDetails(it)) } }
            entry<ProductDetails> { key -> DetailScreen(productId = key.productId) }
        }
        /*        entryProvider = { key ->
                    when (key) {
                        is ProductList -> NavEntry(key) {
                            ProductListScreen {
                                backStack.add(ProductDetails(it))
                            }
                        }

                        is ProductDetails -> NavEntry(key) {
                            DetailScreen(productId = key.productId)
                        }
                    }
                }*/
    )
}

@Composable
fun ProductListScreen(onClick: (String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            List(10) { it }.forEach {
                DetailItem(item = "Android $it", modifier = Modifier.padding(8.dp), onClick)
            }
        }
    }
}


@Composable
fun DetailItem(
    item: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    Text(
        text = "Detail $item!",
        modifier = modifier.clickable { onClick(item) }
    )
}

@Composable
fun EmptyScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            text = "Details screen",
            modifier = Modifier.padding(innerPadding),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestingNavigation3Theme {
        DetailItem("Android") { }
    }
}
