/*
 * Created by JFormDesigner on Mon May 30 23:22:17 CEST 2022
 */
package xyz.terrific.gui

import xyz.terrific.Main.Companion.frame
import xyz.terrific.helper.InstallHelper.install
import xyz.terrific.helper.InstallHelper.installed
import xyz.terrific.helper.InstallHelper.uninstall
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.*
import javax.swing.*

/**
 * @author TerrificTable55
 * @version 1.0
 */
class InstallerFrame : JFrame() {
    private fun btn_installMouseClicked(e: MouseEvent) {
        if (!btn_install!!.isEnabled) return
        error!!.text = ""
        val version_index = installed_table!!.selectedRow
        if (version_index == -1) {
            error!!.text = "No Row Chosen"
            return
        }
        val version = installed[version_index]
        progress_bar!!.isVisible = true
        Thread {
            for (i in 0..100) {
                if (progress_bar!!.value >= 100) break
                progress_bar!!.value = i
                try {
                    Thread.sleep((100 + Math.random() * (10 - 5 + 1)).toLong())
                } catch (ex: InterruptedException) {
                    println("[ERROR] SLEEP:  " + ex.message)
                }
            }
        }.start()
        val success = install(version)
        Thread { if (success) frame!!.progress_bar!!.value = 100 }.start()
        if (!success) error!!.text = "Unable to install Client!"
        Thread {
            try {
                while (true) {
                    if (progress_bar!!.value >= 100 && success) {
                        Thread.sleep(2000)
                        progress_bar!!.isVisible = false
                        break
                    }
                    Thread.sleep(100)
                }
            } catch (ex: InterruptedException) {
                println("[ERROR] SLEEP:  " + ex.message)
            }
        }.start()
    }


    private fun btn_uninstallMouseClicked(e: MouseEvent) {
        if (!btn_uninstall!!.isEnabled) return
        error!!.text = ""
        val version_index = installed_table!!.selectedRow
        if (version_index == -1) {
            error!!.text = "No Row chosen"
            return
        }
        val version = installed[version_index]
        progress_bar!!.isVisible = true
        Thread {
            for (i in 0..100) {
                if (progress_bar!!.value >= 100) break
                progress_bar!!.value = i
                try {
                    Thread.sleep((100 + Math.random() * (5 - 1 + 1)).toLong())
                } catch (ex: InterruptedException) {
                    println("[ERROR] SLEEP:  " + ex.message)
                }
            }
        }.start()
        val success = uninstall(version)
        if (!success) error!!.text = "Unable to uninstall Client!"
        Thread {
            try {
                while (true) {
                    if (progress_bar!!.value >= 100) {
                        Thread.sleep(2000)
                        progress_bar!!.isVisible = false
                        break
                    }
                    Thread.sleep(100)
                }
            } catch (ex: InterruptedException) {
                println("[ERROR] SLEEP:  " + ex.message)
            }
        }.start()
    }

    private fun installed_table_MouseClicked(e: MouseEvent) {
        for (version in installed) {
            if (version.name == installed[installed_table!!.selectedRow].name) {
                if (version.isInstalled) {
                    btn_uninstall!!.displayedMnemonicIndex = 0
                    btn_uninstall!!.isEnabled = true
                    btn_install!!.displayedMnemonicIndex = 0
                    btn_install!!.isEnabled = false
                } else {
                    btn_install!!.displayedMnemonicIndex = 0
                    btn_install!!.isEnabled = true
                    btn_uninstall!!.displayedMnemonicIndex = 0
                    btn_uninstall!!.isEnabled = false
                }
            }
        }
    }


    private fun btn_exitMouseClicked(e: MouseEvent) {
        dispose()
        System.exit(0)
    }


    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        val bundle = ResourceBundle.getBundle("InstallerFrame")
        window_label = JLabel()
        error = JLabel()
        progress_bar = JProgressBar()
        btn_exit = JButton()
        btn_exit!!.putClientProperty("JButton.buttonType", "roundRect")
        btn_install = JButton()
        btn_install!!.putClientProperty("JButton.buttonType", "roundRect")
        btn_uninstall = JButton()
        btn_uninstall!!.putClientProperty("JButton.buttonType", "roundRect")
        scrollPane1 = JScrollPane()
        val versions = installed
        val column_names = arrayOf("Test", "Test1")
        val data: MutableList<Array<Any>> = ArrayList(versions.size + 1)
        for (version in versions) {
            data.add(arrayOf(version.name, version.isInstalled))
        }
        val t = data.toTypedArray()
        installed_table = JTable(t, column_names)
        installed_table!!.setSelectionMode(0)


        //======== this ========
        minimumSize = Dimension(900, 550)
        val contentPane = contentPane
        contentPane.layout = null

        //---- window_label ----
        window_label!!.text = bundle.getString("InstallerFrame.window_label.text")
        window_label!!.autoscrolls = true
        window_label!!.font =
            window_label!!.font.deriveFont(window_label!!.font.style or Font.BOLD, window_label!!.font.size + 20f)
        window_label!!.horizontalAlignment = SwingConstants.CENTER
        contentPane.add(window_label)
        window_label!!.setBounds(0, 0, 900, 60)

        //---- btn_install ----
        btn_install!!.text = bundle.getString("InstallerFrame.btn_install.text")
        btn_install!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                btn_installMouseClicked(e)
            }
        })
        contentPane.add(btn_install)
        btn_install!!.setBounds(380, 450, 135, 40)

        //---- btn_uninstall ----
        btn_uninstall!!.text = bundle.getString("InstallerFrame.btn_uninstall.text")
        btn_uninstall!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                btn_uninstallMouseClicked(e)
            }
        })
        contentPane.add(btn_uninstall)
        btn_uninstall!!.setBounds(210, 450, 135, 40)

        //======== scrollPane1 ========
        run { scrollPane1!!.setViewportView(installed_table) }
        contentPane.add(scrollPane1)
        scrollPane1!!.setBounds(25, 60, 850, 305)

        //======== installed_table ========
        installed_table!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                installed_table_MouseClicked(e)
            }
        })


        //---- error ----
        error!!.isEnabled = false
        error!!.font = error!!.font.deriveFont(error!!.font.size + 3f)
        error!!.horizontalAlignment = SwingConstants.CENTER
        error!!.foreground = Color(238, 238, 238)
        error!!.background = Color(238, 238, 238)
        contentPane.add(error)
        error!!.setBounds(0, 380, 900, 30)
        contentPane.add(progress_bar)
        progress_bar!!.setBounds(235, 420, 430, 20)

        //---- btn_exit ----
        btn_exit!!.text = bundle.getString("InstallerFrame.btn_exit.text")
        btn_exit!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                btn_exitMouseClicked(e)
            }
        })
        contentPane.add(btn_exit)
        btn_exit!!.setBounds(565, 450, 100, 40)
        run {

            // compute preferred size
            val preferredSize = Dimension()
            for (i in 0 until contentPane.componentCount) {
                val bounds = contentPane.getComponent(i).bounds
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width)
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height)
            }
            val insets = contentPane.insets
            preferredSize.width += insets.right
            preferredSize.height += insets.bottom
            contentPane.minimumSize = preferredSize
            contentPane.preferredSize = preferredSize
        }
        pack()
        setLocationRelativeTo(owner)
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    var window_label: JLabel? = null
    var btn_install: JButton? = null
    var btn_uninstall: JButton? = null
    var scrollPane1: JScrollPane? = null
    var installed_table: JTable? = null
    var error: JLabel? = null
    var progress_bar: JProgressBar? = null
    var btn_exit: JButton? = null // JFormDesigner - End of variables declaration  //GEN-END:variables

    init {
        initComponents()
    }
}
