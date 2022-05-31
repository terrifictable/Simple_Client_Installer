package xyz.terrific.utils;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.util.LoggingFacade;

import java.io.IOException;
import java.util.Objects;

public class ThemeUtils {
    /**
     * To install Themes
     */
    public ThemeUtils() {  }

    /**
     *
     * @param name Theme (json) to load
     * @return IntelliJTheme
     */
    public static IntelliJTheme loadTheme(String name) {
        try {
            return new IntelliJTheme(Objects.requireNonNull(ThemeUtils.class.getResourceAsStream("/themes/" + name)));
        } catch (IOException var3) {
            String msg = "THEME_LOADER: Failed to load IntelliJ theme '" + name + "'";
            LoggingFacade.INSTANCE.logSevere(msg, var3);
            throw new RuntimeException(msg, var3);
        }
    }
}
