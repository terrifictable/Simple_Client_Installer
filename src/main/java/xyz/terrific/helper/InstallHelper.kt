package xyz.terrific.helper

import org.apache.commons.io.FileUtils
import xyz.terrific.Main
import xyz.terrific.utils.version.Version
import java.io.File
import java.net.URL
import java.util.*

/**
 * @author TerrificTable55
 */
object InstallHelper {
    var client_name = "test"

    /**
     * Get a list of all folders in `whatever/.minecraft/versions/` that are named like `client_name`
     * @return version class instance thingy
     */
    val installed: List<Version>
        get() {
            val dir_path = File(OSHelper.OS.getMc() + "versions/")
            val versions: MutableList<Version> = ArrayList()
            val filesList: Array<File> = dir_path.listFiles()!!
            for (file in filesList) {
                if (file.name == client_name) versions.add(Version(client_name + " - client", true))
            }
            if (versions.size <= 0) {
                versions.add(Version("versions/" + client_name + " - client", false))
            }
            return versions
        }

    /**
     * Install & Download Client
     * @param version to create folder and save files in that folder
     * @return boolean (true = success | false = failed)
     */
    fun install(version: Version): Boolean {
        if (version.isInstalled) return false
        try {
            val path = File(OSHelper.OS.getMc() + "versions/" + client_name)
            if (!path.mkdir()) return false
            val jar = File(OSHelper.OS.getMc() + "versions/" + client_name + "/" + client_name + ".jar")
            val json = File(OSHelper.OS.getMc() + "versions/" + client_name + "/" + client_name + ".json")
            FileUtils.copyURLToFile(URL(Main.json_url), jar)
            FileUtils.copyURLToFile(URL(Main.json_url), json)
            version.isInstalled = true
        } catch (e: Exception) {
            println("[ERROR]  " + e.message)
            return false
        }
        return true
    }

    /**
     * UnInstall Client
     * @param version to delete folder with client files
     * @return boolean (true = success | false = failed)
     */
    fun uninstall(version: Version): Boolean {
        if (!version.isInstalled) return false
        try {
            val path = File(OSHelper.OS.getMc() + "versions/" + client_name)
            if (!path.exists()) return false
            for (file in Objects.requireNonNull<Array<File>>(path.listFiles())) file.delete()
            path.delete()
            if (path.exists()) return false
            version.isInstalled = false
        } catch (e: Exception) {
            println("[ERROR]  " + e.message)
            return false
        }
        Main.frame?.progress_bar?.value = 100
        return true
    }
}
