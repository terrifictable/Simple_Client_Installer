package xyz.terrific

import xyz.terrific.gui.InstallerFrame
import xyz.terrific.theme.FrameTheme
import javax.swing.*

/**
 * @author TerrificTable55
 * @version 1.0
 */
class Main {
    var resources               = "src/main/resources/"
    var json_url                = "https://raw.githubusercontent.com/TerrificTable/Simple_Client_Installer/master/libraries/client/test.json" // Change to your Client Download URl
    var jar_url                 = "https://raw.githubusercontent.com/TerrificTable/Simple_Client_Installer/master/libraries/client/test.jar" // Change to your Client Download URl
    var frame: InstallerFrame?  = null


    /**
     * Creates a new InstallerFrame, and sets some stuff like icon and title, and does some other stuff
     * @param args never used
     */
    fun main(args: Array<String>) {
        frame                           = InstallerFrame()
        frame!!.iconImage               = ImageIcon(resources + "images/icon.png").image
        frame!!.defaultCloseOperation   = JFrame.EXIT_ON_CLOSE
        frame!!.title                   = "Client Installer"

        // Theme Based on XCode-Dark
        UIManager.setLookAndFeel(FrameTheme())
        SwingUtilities.updateComponentTreeUI(frame)
        frame!!.btn_uninstall?.isEnabled = false
        frame!!.btn_install?.isEnabled   = false
        frame!!.installed_table?.setDefaultEditor(Any::class.java, null)
        frame!!.progress_bar?.isVisible = false
        frame!!.isVisible               = true
    }

    companion object {
        val resources: String       = Main().resources
        val json_url: String        = Main().json_url
        val jar_url: String         = Main().jar_url
        val frame: InstallerFrame?  = Main().frame
    }
}
