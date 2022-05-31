package xyz.terrific.helper;


import org.apache.commons.io.FileUtils;
import xyz.terrific.Main;
import xyz.terrific.utils.version.Version;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TerrificTable55
 */
public class InstallHelper {

    public static String client_name = "test";

    /**
     * Get a list of all folders in `whatever/.minecraft/versions/` that are named like `client_name`
     * @return version class instance thingy
     */
    public static List<Version> getInstalled() {

        File dir_path = new File(OSHelper.getOS().getMc() + "versions/");
        List<Version> versions = new ArrayList<>();


        File[] filesList = dir_path.listFiles();
        assert filesList != null;
        for (File file : filesList) {
            if (file.getName().equals(client_name))
                versions.add(new Version(client_name + " - client", true));
        }

        if (versions.size() <= 0) {
            versions.add(new Version("versions/" + client_name + " - client", false));
        }


        return versions;
    }

    /**
     * Install Client
     * @param version to create folder and save files in that folder
     * @return boolean (true = success | false = failed)
     */
    public static boolean install(Version version) {
        if (version.isInstalled()) return false;

        try {

            File path = new File(OSHelper.getOS().getMc() + "versions/" + client_name);
            if (!path.mkdir()) return false;

            File jar = new File(OSHelper.getOS().getMc() + "versions/" + client_name + "/" + client_name + ".jar");
            File json = new File(OSHelper.getOS().getMc() + "versions/" + client_name + "/" + client_name + ".json");
            FileUtils.copyURLToFile(new URL(Main.jar_url), jar);
            FileUtils.copyURLToFile(new URL(Main.json_url), json);

            version.setInstalled(true);

        } catch (Exception e) {
            System.out.println("[ERROR]  " + e.getMessage());
            return false;
        }
        Main.frame.progress_bar.setValue(100);

        return true;
    }

    /**
     * UnInstall Client
     * @param version to delete folder with client files
     * @return boolean (true = success | false = failed)
     */
    public static boolean uninstall(Version version) {
        if (version.isInstalled()) return false;

        try {

            File path = new File(OSHelper.getOS().getMc() + "versions/" + client_name);
            if (!path.exists()) return false;
            if (!path.delete()) return false;

            version.setInstalled(false);

        } catch (Exception e) {
            System.out.println("[ERROR]  " + e.getMessage());
            return false;
        }
        Main.frame.progress_bar.setValue(100);

        return true;
    }
}
