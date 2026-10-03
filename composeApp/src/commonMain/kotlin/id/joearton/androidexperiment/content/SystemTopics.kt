package id.joearton.androidexperiment.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import id.joearton.androidexperiment.model.Category
import id.joearton.androidexperiment.model.Topic
import id.joearton.androidexperiment.openUrl
import id.joearton.androidexperiment.platformName

val systemTopics = listOf(
    Topic(
        id = "activity-lifecycle",
        title = "Activity & Lifecycle",
        category = Category.System,
        summary = "Activity adalah titik masuk UI aplikasi Android. Sistem memanggil callback siklus hidup saat Activity dibuat, tampil, dijeda, atau dihancurkan.",
        points = listOf(
            "onCreate: inisialisasi dan setContent { ... } untuk memasang UI Compose.",
            "onStart/onResume: Activity terlihat dan interaktif. onPause/onStop: kehilangan fokus atau tak terlihat.",
            "onDestroy: dihancurkan, termasuk saat rotasi (kecuali konfigurasi ditangani sendiri).",
            "Di Compose, gunakan LifecycleEventEffect atau DisposableEffect dengan LifecycleObserver untuk bereaksi pada siklus hidup.",
            "Setiap Activity wajib dideklarasikan di AndroidManifest.xml.",
        ),
        code = """
            class MainActivity : ComponentActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    enableEdgeToEdge()
                    setContent { App() }
                }
            }
        """,
        demo = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Platform saat ini: $platformName")
                Text("Urutan umum: onCreate → onStart → onResume → (berjalan) → onPause → onStop → onDestroy")
            }
        },
    ),
    Topic(
        id = "intent",
        title = "Intent",
        category = Category.System,
        summary = "Intent adalah pesan untuk meminta aksi dari komponen lain: membuka Activity, URL, berbagi data, atau memulai Service.",
        points = listOf(
            "Intent eksplisit menunjuk komponen tertentu; Intent implisit menyatakan aksi (ACTION_VIEW, ACTION_SEND).",
            "Data dibawa lewat Uri dan extras (putExtra).",
            "Dari luar Activity, tambahkan FLAG_ACTIVITY_NEW_TASK.",
            "Demo ini memakai expect/actual: Android memanggil Intent.ACTION_VIEW, desktop membuka browser.",
        ),
        code = """
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            context.startActivity(intent)
        """,
        demo = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Platform: $platformName")
                Button(onClick = { openUrl("https://developer.android.com") }) { Text("Buka developer.android.com") }
            }
        },
    ),
)
