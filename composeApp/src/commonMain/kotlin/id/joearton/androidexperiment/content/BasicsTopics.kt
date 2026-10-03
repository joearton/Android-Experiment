package id.joearton.androidexperiment.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.joearton.androidexperiment.model.Category
import id.joearton.androidexperiment.model.Topic

val basicsTopics = listOf(
    Topic(
        id = "composable",
        title = "@Composable & Text",
        category = Category.Basics,
        summary = "Fungsi @Composable adalah blok pembangun UI di Jetpack Compose. Anda mendeskripsikan UI, Compose yang menggambarnya.",
        points = listOf(
            "Anotasi @Composable menandai fungsi yang boleh memancarkan (emit) UI.",
            "Nama fungsi composable diawali huruf kapital dan tidak mengembalikan nilai UI.",
            "Text menampilkan teks; atur gaya lewat fontSize, fontWeight, color, maxLines, dan overflow.",
            "Pemanggilan composable bisa berulang kali (recomposition), jadi hindari efek samping di dalam body.",
        ),
        code = """
            @Composable
            fun Salam(nama: String) {
                Text(
                    text = "Halo, " + nama,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        """,
        demo = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Halo, Android!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text("Teks miring dan tebal", fontWeight = FontWeight.SemiBold, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                Text(
                    "Teks panjang dipotong dengan maxLines = 1 dan overflow = Ellipsis agar tidak memenuhi layar yang sempit.",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
    ),
    Topic(
        id = "modifier",
        title = "Modifier",
        category = Category.Basics,
        summary = "Modifier mengubah ukuran, tata letak, tampilan, dan perilaku sebuah composable lewat rantai pemanggilan.",
        points = listOf(
            "Urutan modifier penting: padding sebelum background berbeda hasilnya dengan sesudahnya.",
            "Umum dipakai: fillMaxWidth, size, padding, background, border, clip, clickable.",
            "Setiap composable menerima parameter modifier sebagai parameter pertama opsional.",
        ),
        code = """
            Text(
                "Klik saya",
                modifier = Modifier
                    .background(Color(0xFF6650A4))
                    .clickable { /* aksi */ }
                    .padding(16.dp),
                color = Color.White,
            )
        """,
        demo = {
            var count by remember { mutableStateOf(0) }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "padding SEBELUM background",
                    modifier = Modifier.padding(16.dp).background(Color(0xFF6650A4)),
                    color = Color.White,
                )
                Text(
                    "padding SESUDAH background",
                    modifier = Modifier.background(Color(0xFF6650A4)).padding(16.dp),
                    color = Color.White,
                )
                Text(
                    "Diklik $count kali (clickable)",
                    modifier = Modifier.background(Color(0xFF006A6A)).clickable { count++ }.padding(16.dp),
                    color = Color.White,
                )
            }
        },
    ),
)
