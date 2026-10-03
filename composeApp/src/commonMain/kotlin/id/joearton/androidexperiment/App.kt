package id.joearton.androidexperiment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.joearton.androidexperiment.content.allTopics
import id.joearton.androidexperiment.ui.TocList
import id.joearton.androidexperiment.ui.TopicScreen
import kotlinx.coroutines.launch

/** Lebar minimum agar TOC tampil permanen di samping (tablet/desktop). */
private val WideLayoutThreshold = 840.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme {
        var selectedId by rememberSaveable { mutableStateOf(allTopics.first().id) }
        val topic = allTopics.first { it.id == selectedId }

        BoxWithConstraints {
            val wide = maxWidth >= WideLayoutThreshold

            if (wide) {
                PermanentNavigationDrawer(
                    drawerContent = {
                        PermanentDrawerSheet(Modifier.padding(end = 1.dp)) {
                            TocList(allTopics, selectedId) { selectedId = it }
                        }
                    },
                ) {
                    Scaffold(topBar = { TopAppBar(title = { Text(topic.title) }) }) { padding ->
                        Box(Modifier.padding(padding)) { TopicScreen(topic) }
                    }
                }
            } else {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        androidx.compose.material3.ModalDrawerSheet {
                            TocList(allTopics, selectedId) {
                                selectedId = it
                                scope.launch { drawerState.close() }
                            }
                        }
                    },
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(topic.title) },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Buka daftar isi")
                                    }
                                },
                            )
                        },
                    ) { padding ->
                        Box(Modifier.padding(padding)) { TopicScreen(topic) }
                    }
                }
            }
        }
    }
}
