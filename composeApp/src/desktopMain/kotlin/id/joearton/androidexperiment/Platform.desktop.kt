package id.joearton.androidexperiment

import java.awt.Desktop
import java.net.URI

actual val platformName: String = "Desktop (${System.getProperty("os.name")})"

actual fun openUrl(url: String) {
    if (Desktop.isDesktopSupported()) Desktop.getDesktop().browse(URI(url))
}
