package id.joearton.androidexperiment.model

import androidx.compose.runtime.Composable

/** Pengelompokan topik pada daftar isi (TOC). */
enum class Category(val label: String) {
    Basics("Dasar Compose"),
    Layout("Layout"),
    Components("Komponen UI"),
    State("State & Arsitektur"),
    System("Sistem Android"),
}

/** Satu entri materi: penjelasan, contoh kode, dan demo interaktif. */
class Topic(
    val id: String,
    val title: String,
    val category: Category,
    val summary: String,
    val points: List<String>,
    val code: String,
    val demo: @Composable () -> Unit,
)
