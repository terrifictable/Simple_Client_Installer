package xyz.terrific.theme

import com.formdev.flatlaf.IntelliJTheme.ThemeLaf
import xyz.terrific.utils.ThemeUtils.loadTheme

/**
 * @author TerrificTable55
 * Based on `XCode-Dark` FlatLaf theme
 */
class FrameTheme : ThemeLaf(loadTheme("FrameTheme.theme.json")) {

    /**
     * Get Theme Name
     * @return Theme Name (String)
     */
    override fun getName(): String {
        return "FrameTheme"
    }

    companion object {
        const val NAME = "FrameTheme"

        /**
         * Setup Theme
         * @return success / fail
         */
        fun setup(): Boolean {
            return try {
                setup(FrameTheme())
            } catch (var1: RuntimeException) {
                false
            }
        }

        /**
         * Set some infos...
         */
        fun installLafInfo() {
            installLafInfo("FrameTheme", FrameTheme::class.java)
        }
    }
}
