package xyz.terrific.utils

import com.formdev.flatlaf.IntelliJTheme
import com.formdev.flatlaf.util.LoggingFacade
import java.io.IOException

/**
 * @author FlatLaf
 */
object ThemeUtils {
    /**
     *
     * @param name Theme (json) to load
     * @return IntelliJTheme
     */
    fun loadTheme(name: String): IntelliJTheme {
        return try {
            IntelliJTheme(
                java.util.Objects.requireNonNull(
                    ThemeUtils::class.java.getResourceAsStream(
                        "/themes/$name"
                    )))
        } catch (  var3: IOException){
            val msg = "THEME_LOADER: Failed to load IntelliJ theme '$name'"
            LoggingFacade.INSTANCE.logSevere(msg, var3)

            throw RuntimeException(msg, var3)
        }
    }
}
