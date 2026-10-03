package id.joearton.androidexperiment

/** Nama platform tempat aplikasi berjalan (contoh `expect`/`actual`). */
expect val platformName: String

/** Membuka URL: di Android memakai `Intent.ACTION_VIEW`. */
expect fun openUrl(url: String)
