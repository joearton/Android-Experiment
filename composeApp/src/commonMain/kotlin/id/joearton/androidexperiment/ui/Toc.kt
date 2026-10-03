package id.joearton.androidexperiment.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.joearton.androidexperiment.model.Topic

/** Daftar isi, dikelompokkan per kategori. Dipakai oleh drawer. */
@Composable
fun TocList(topics: List<Topic>, selectedId: String, onSelect: (String) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(12.dp)) {
        item {
            Text(
                "Android Explorer",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp),
            )
        }
        topics.groupBy { it.category }.forEach { (category, items) ->
            item(key = category.name) {
                Text(
                    category.label,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
                )
            }
            items(items, key = { it.id }) { topic ->
                NavigationDrawerItem(
                    label = { Text(topic.title) },
                    selected = topic.id == selectedId,
                    onClick = { onSelect(topic.id) },
                )
            }
        }
    }
}
