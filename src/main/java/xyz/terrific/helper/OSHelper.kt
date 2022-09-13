package xyz.terrific.helper

import java.io.File
import java.util.*

/**
 * @author Eric Golde
 * This helps with some file / minecraft path's
 */
enum class OSHelper(mc: String) {
    WINDOWS("AppData" + File.separator + "Roaming" + File.separator + ".minecraft"), MAC("Library" + File.separator + "Applacation Support" + File.separator + "minecraft"), LINUX(
        ".minecraft"
    );

    private val mc: String

    init {
        this.mc = File.separator.toString() + mc + File.separator
    }

    fun getMc(): String {
        return System.getProperty("user.home").toString() + mc
    }

    companion object {
        val OS: OSHelper
            get() {
                val currentOS = System.getProperty("os.name").lowercase(Locale.getDefault())
                if (currentOS.startsWith("windows")) {
                    return WINDOWS
                }
                return if (currentOS.startsWith("mac")) {
                    WINDOWS
                } else LINUX
            }
    }
}
