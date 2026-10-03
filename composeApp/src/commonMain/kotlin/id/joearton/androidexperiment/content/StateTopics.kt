package id.joearton.androidexperiment.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import id.joearton.androidexperiment.model.Category
import id.joearton.androidexperiment.model.Topic
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()
    fun increment() = _count.update { it + 1 }
}

@Composable
private fun EffectDemo() {
    var visible by remember { mutableStateOf(true) }
    val log = remember { mutableStateListOf<String>() }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = { visible = !visible }) { Text(if (visible) "Sembunyikan child" else "Tampilkan child") }
        if (visible) {
            var seconds by remember { mutableStateOf(0) }
            LaunchedEffect(Unit) {
                log += "LaunchedEffect: mulai timer"
                while (true) { delay(1000); seconds++ }
            }
            DisposableEffect(Unit) {
                log += "DisposableEffect: masuk komposisi"
                onDispose { log += "DisposableEffect: keluar komposisi" }
            }
            Text("Timer child: $seconds detik")
        }
        log.takeLast(5).forEach { Text(it) }
    }
}

val stateTopics = listOf(
    Topic(
        id = "state",
        title = "State & remember",
        category = Category.State,
        summary = "UI Compose adalah fungsi dari state. Ketika state berubah, Compose menjalankan ulang (recompose) composable yang membacanya.",
        points = listOf(
            "mutableStateOf membuat state yang dapat diamati Compose.",
            "remember menyimpan nilai selama composable ada di komposisi; hilang jika composable dihapus.",
            "rememberSaveable juga bertahan saat rotasi layar atau proses dimatikan sistem (Android).",
            "State hoisting: angkat state ke pemanggil, jadikan composable stateless (value + callback).",
        ),
        code = """
            var biasa by remember { mutableStateOf(0) }
            var awet by rememberSaveable { mutableStateOf(0) }
            Button(onClick = { biasa++; awet++ }) { Text("Tambah") }
        """,
        demo = {
            var plain by remember { mutableStateOf(0) }
            var saved by rememberSaveable { mutableStateOf(0) }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("remember: $plain")
                Text("rememberSaveable: $saved")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { plain++; saved++ }) { Text("Tambah keduanya") }
                    OutlinedButton(onClick = { plain = 0; saved = 0 }) { Text("Reset") }
                }
                Text("Di Android, putar layar: hanya rememberSaveable yang tetap.", style = androidx.compose.material3.MaterialTheme.typography.bodySmall)
            }
        },
    ),
    Topic(
        id = "effects",
        title = "Side effects",
        category = Category.State,
        summary = "Pekerjaan di luar penggambaran UI (timer, listener, I/O) harus dibungkus effect handler agar terikat siklus hidup komposisi.",
        points = listOf(
            "LaunchedEffect(key): menjalankan coroutine saat masuk komposisi, dibatalkan saat keluar atau key berubah.",
            "DisposableEffect(key): mendaftarkan sesuatu lalu membersihkannya di onDispose.",
            "rememberCoroutineScope: scope untuk meluncurkan coroutine dari event seperti klik.",
            "SideEffect dan rememberUpdatedState melengkapi untuk kasus lanjutan.",
        ),
        code = """
            LaunchedEffect(Unit) {
                while (true) { delay(1000); detik++ }
            }
            DisposableEffect(Unit) {
                // daftarkan listener
                onDispose { /* lepas listener */ }
            }
        """,
        demo = { EffectDemo() },
    ),
    Topic(
        id = "viewmodel",
        title = "ViewModel",
        category = Category.State,
        summary = "ViewModel menyimpan state UI dan logika presentasi, bertahan saat konfigurasi berubah (misalnya rotasi) dan terpisah dari UI.",
        points = listOf(
            "Turunkan dari ViewModel; ekspos state sebagai StateFlow (read-only).",
            "Ambil di composable dengan viewModel() lalu collectAsState().",
            "viewModelScope untuk coroutine yang otomatis dibatalkan saat ViewModel dihapus.",
            "Sejak library lifecycle 2.8, ViewModel tersedia multiplatform (KMP).",
        ),
        code = """
            class CounterViewModel : ViewModel() {
                private val _count = MutableStateFlow(0)
                val count: StateFlow<Int> = _count.asStateFlow()
                fun increment() = _count.update { it + 1 }
            }

            val vm: CounterViewModel = viewModel { CounterViewModel() }
            val count by vm.count.collectAsState()
        """,
        demo = {
            val vm: CounterViewModel = viewModel { CounterViewModel() }
            val count by vm.count.collectAsState()
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Nilai di ViewModel: $count")
                Button(onClick = vm::increment) { Text("Increment") }
            }
        },
    ),
)
