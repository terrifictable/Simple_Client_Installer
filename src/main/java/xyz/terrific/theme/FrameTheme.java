package xyz.terrific.theme;

import com.formdev.flatlaf.IntelliJTheme;
import xyz.terrific.utils.ThemeUtils;

// Based on XCode-Dark

/**
 * @author TerrificTable55
 * Based on `XCode-Dark` FlatLaf theme
 */
public class FrameTheme extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "FrameTheme";

    /**
     * Setup Theme
     * @return success / fail
     */
    public static boolean setup() {
        try {
            return setup(new FrameTheme());
        } catch (RuntimeException var1) {
            return false;
        }
    }

    // ... I guess, IDK I just copied another Theme and changed some stuff
    /**
     * Set some infos...
     */
    public static void installLafInfo() {
        installLafInfo("FrameTheme", FrameTheme.class);
    }

    /**
     * Load Json Theme
     */
    public FrameTheme() {
        super(ThemeUtils.loadTheme("FrameTheme.theme.json"));
    }

    /**
     * Get Theme Name
     * @return Theme Name (String)
     */
    public String getName() {
        return "FrameTheme";
    }
}
