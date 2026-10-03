package id.joearton.androidexperiment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build

actual val platformName: String = "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"

/** Diisi oleh [MainActivity]; dipakai untuk memulai Intent. */
internal var appContext: Context? = null

actual fun openUrl(url: String) {
    val context = appContext ?: return
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}
