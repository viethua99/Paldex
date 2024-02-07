import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.vproject.paldex.presentation.component.PaldexAppBackground
import com.vproject.paldex.presentation.component.PaldexNavigationBar
import com.vproject.paldex.presentation.component.PaldexNavigationBarItem
import com.vproject.paldex.presentation.component.theme.PaldexAppTheme
import com.vproject.paldex.presentation.screen.favorite.FavoriteTab
import com.vproject.paldex.presentation.screen.home.HomeTab

@Composable
fun App() {
    PaldexAppTheme {
        PaldexAppBackground {
            Navigator(MainTabContainer())
        }
    }
}

private class MainTabContainer : Screen {
    val tabs = listOf(HomeTab, FavoriteTab)

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        TabNavigator(
            tabs[0], tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = tabs
                )
            }
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.background,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    PaldexBottomBar(tabs = tabs)
                }
            ) { paddingValues ->
                Column(Modifier.fillMaxSize().padding(paddingValues)) {
                    CurrentTab()
                }
            }
        }
    }
}

/**
 * Bottom bar with list of tabs to select
 */
@Composable
private fun PaldexBottomBar(
    modifier: Modifier = Modifier,
    tabs: List<Tab>
) {
    PaldexNavigationBar(
        modifier = modifier,
    ) {
        tabs.forEach { tab ->
            val tabNavigator = LocalTabNavigator.current
            val selected = tabNavigator.current.key == tab.key
            PaldexNavigationBarItem(
                selected = selected,
                onClick = { tabNavigator.current = tab  },
                icon = {
                    Icon(painter = tab.options.icon!!, contentDescription = tab.options.title)

                },
                label = { Text(tab.options.title) })
        }
    }
}