package id.joearton.androidexperiment.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.joearton.androidexperiment.model.Category
import id.joearton.androidexperiment.model.Topic

@Composable
private fun Chip(label: String, color: Color, modifier: Modifier = Modifier.size(56.dp)) {
    Box(modifier.background(color), contentAlignment = Alignment.Center) {
        Text(label, color = Color.White)
    }
}

val layoutTopics = listOf(
    Topic(
        id = "column-row-box",
        title = "Column, Row, Box",
        category = Category.Layout,
        summary = "Tiga layout dasar: Column menyusun vertikal, Row horizontal, dan Box menumpuk elemen.",
        points = listOf(
            "Column: verticalArrangement mengatur jarak, horizontalAlignment mengatur perataan silang.",
            "Row: horizontalArrangement dan verticalAlignment bekerja sebaliknya.",
            "Box: anak digambar bertumpuk; contentAlignment atau Modifier.align menentukan posisinya.",
            "Modifier.weight(1f) pada anak Row/Column membagi sisa ruang secara proporsional.",
        ),
        code = """
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(Modifier.size(56.dp).background(Color.Red))
                Box(Modifier.weight(1f).height(56.dp).background(Color.Blue))
            }
        """,
        demo = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Column", style = MaterialTheme.typography.labelLarge)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Chip("1", Color(0xFFB3261E), Modifier.fillMaxWidth().height(32.dp))
                    Chip("2", Color(0xFF6650A4), Modifier.fillMaxWidth().height(32.dp))
                }
                Text("Row dengan weight", style = MaterialTheme.typography.labelLarge)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Chip("A", Color(0xFF006A6A))
                    Chip("weight(1f)", Color(0xFF6650A4), Modifier.weight(1f).height(56.dp))
                    Chip("B", Color(0xFF006A6A))
                }
                Text("Box (menumpuk)", style = MaterialTheme.typography.labelLarge)
                Box(Modifier.size(96.dp)) {
                    Chip("bawah", Color(0xFFB3261E), Modifier.size(96.dp))
                    Chip("atas", Color(0xFF6650A4), Modifier.size(48.dp).align(Alignment.BottomEnd).clip(CircleShape))
                }
            }
        },
    ),
    Topic(
        id = "lazy-column",
        title = "LazyColumn",
        category = Category.Layout,
        summary = "LazyColumn menampilkan daftar panjang secara efisien: hanya item yang terlihat yang dibuat. Setara RecyclerView di Compose.",
        points = listOf(
            "items(list, key = ...) membuat item per elemen; key menjaga state saat urutan berubah.",
            "Gunakan LazyRow untuk daftar horizontal dan LazyVerticalGrid untuk grid.",
            "Jangan menaruh LazyColumn di dalam Column yang bisa di-scroll (tinggi tak terbatas).",
            "LazyListState memungkinkan scroll terprogram, misalnya animateScrollToItem.",
        ),
        code = """
            LazyColumn {
                items(nama, key = { it }) { item ->
                    Text(item, Modifier.padding(12.dp))
                    HorizontalDivider()
                }
            }
        """,
        demo = {
            val names = remember { List(50) { "Item ke-" + (it + 1) } }
            LazyColumn(Modifier.height(220.dp)) {
                items(names, key = { it }) { item ->
                    Text(item, Modifier.fillMaxWidth().padding(12.dp))
                    HorizontalDivider()
                }
            }
        },
    ),
)
