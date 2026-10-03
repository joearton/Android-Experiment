package id.joearton.androidexperiment.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.joearton.androidexperiment.model.Category
import id.joearton.androidexperiment.model.Topic

val componentTopics = listOf(
    Topic(
        id = "button",
        title = "Button",
        category = Category.Components,
        summary = "Tombol memicu aksi lewat onClick. Material 3 menyediakan beberapa varian dengan tingkat penekanan berbeda.",
        points = listOf(
            "Button (filled): aksi utama. FilledTonalButton: penekanan menengah.",
            "OutlinedButton dan ElevatedButton: aksi sekunder.",
            "TextButton: aksi paling ringan, cocok di dialog.",
            "enabled = false menonaktifkan tombol; isi tombol adalah slot composable bebas.",
        ),
        code = """
            Button(onClick = { jumlah++ }) {
                Text("Tambah")
            }
            OutlinedButton(onClick = { }, enabled = false) { Text("Nonaktif") }
        """,
        demo = {
            var clicks by remember { mutableStateOf(0) }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Total klik: $clicks")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { clicks++ }) { Text("Filled") }
                    FilledTonalButton(onClick = { clicks++ }) { Text("Tonal") }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { clicks++ }) { Text("Outlined") }
                    ElevatedButton(onClick = { clicks++ }) { Text("Elevated") }
                    TextButton(onClick = { clicks = 0 }) { Text("Reset") }
                }
                OutlinedButton(onClick = {}, enabled = false) { Text("Nonaktif") }
            }
        },
    ),
    Topic(
        id = "textfield",
        title = "TextField",
        category = Category.Components,
        summary = "TextField menerima input teks. Nilainya dikendalikan oleh state yang Anda berikan (controlled component).",
        points = listOf(
            "value dan onValueChange wajib; simpan teks di state dengan remember.",
            "label, placeholder, leadingIcon, trailingIcon, supportingText mempercantik input.",
            "isError = true menandai input tidak valid; singleLine membatasi satu baris.",
            "Gunakan KeyboardOptions untuk tipe keyboard (angka, email, dsb.) di Android.",
        ),
        code = """
            var nama by remember { mutableStateOf("") }
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text("Nama") },
                isError = nama.length > 10,
            )
        """,
        demo = {
            var name by remember { mutableStateOf("") }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama (maks. 10 huruf)") },
                    singleLine = true,
                    isError = name.length > 10,
                    supportingText = { Text("${name.length}/10") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(if (name.isBlank()) "Halo, siapa namamu?" else "Halo, $name!")
            }
        },
    ),
    Topic(
        id = "selection",
        title = "Checkbox, Switch, Slider",
        category = Category.Components,
        summary = "Kontrol pilihan untuk nilai boolean dan rentang. Semuanya stateless: Anda yang menyimpan nilainya.",
        points = listOf(
            "Checkbox: pilihan ya/tidak, sering dipakai dalam daftar.",
            "Switch: pengaturan hidup/mati yang berlaku segera.",
            "Slider: nilai kontinu dalam valueRange; steps membuatnya diskrit.",
            "Pola umum: parameter checked/value + callback onCheckedChange/onValueChange.",
        ),
        code = """
            var aktif by remember { mutableStateOf(true) }
            Switch(checked = aktif, onCheckedChange = { aktif = it })

            var volume by remember { mutableStateOf(0.5f) }
            Slider(value = volume, onValueChange = { volume = it })
        """,
        demo = {
            var checked by remember { mutableStateOf(true) }
            var enabled by remember { mutableStateOf(false) }
            var volume by remember { mutableStateOf(0.5f) }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checked, onCheckedChange = { checked = it })
                    Text("Saya setuju: " + if (checked) "ya" else "tidak")
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Switch(checked = enabled, onCheckedChange = { enabled = it })
                    Text("Mode gelap: " + if (enabled) "hidup" else "mati")
                }
                Text("Volume: ${(volume * 100).toInt()}%")
                Slider(value = volume, onValueChange = { volume = it })
            }
        },
    ),
    Topic(
        id = "card-dialog",
        title = "Card & AlertDialog",
        category = Category.Components,
        summary = "Card mengelompokkan konten terkait dalam permukaan bersudut. AlertDialog meminta keputusan pengguna.",
        points = listOf(
            "Card, ElevatedCard, OutlinedCard punya gaya berbeda; isinya bebas.",
            "AlertDialog tampil selama dipanggil di komposisi: kontrol dengan state boolean.",
            "onDismissRequest dipanggil saat klik di luar dialog atau tombol back.",
            "confirmButton wajib, dismissButton opsional.",
        ),
        code = """
            var tampil by remember { mutableStateOf(false) }
            if (tampil) {
                AlertDialog(
                    onDismissRequest = { tampil = false },
                    title = { Text("Hapus?") },
                    confirmButton = { TextButton(onClick = { tampil = false }) { Text("Ya") } },
                )
            }
        """,
        demo = {
            var show by remember { mutableStateOf(false) }
            var result by remember { mutableStateOf("Belum ada pilihan") }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Hasil: $result")
                    Button(onClick = { show = true }) { Text("Tampilkan dialog") }
                }
            }
            if (show) {
                AlertDialog(
                    onDismissRequest = { show = false; result = "Dibatalkan" },
                    title = { Text("Hapus item?") },
                    text = { Text("Tindakan ini tidak dapat dibatalkan.") },
                    confirmButton = { TextButton(onClick = { show = false; result = "Dihapus" }) { Text("Hapus") } },
                    dismissButton = { TextButton(onClick = { show = false; result = "Dibatalkan" }) { Text("Batal") } },
                )
            }
        },
    ),
)
