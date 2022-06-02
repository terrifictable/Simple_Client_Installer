/*
 * Created by JFormDesigner on Mon May 30 23:22:17 CEST 2022
 */

package xyz.terrific.gui;

import javax.swing.plaf.*;

import xyz.terrific.Main;
import xyz.terrific.helper.InstallHelper;
import xyz.terrific.utils.version.Version;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author TerrificTable55
 * @version 1.0
 */
public class InstallerFrame extends JFrame {
    public InstallerFrame() {
        initComponents();
    }

    private void btn_installMouseClicked(MouseEvent e) {
        if (!btn_install.isEnabled()) return;
        error.setText("");

        int version_index = this.installed_table.getSelectedRow();
        if (version_index == -1) {
            error.setText("No Row Chosen");
            return;
        }

        Version version = InstallHelper.getInstalled().get(version_index);

        progress_bar.setVisible(true);
        new Thread(() -> {
            for (int i = 0; i < 101; i++) {
                if (progress_bar.getValue() >= 100) break;

                progress_bar.setValue(i);
                try {
                    Thread.sleep((long) (100 + (Math.random() * (10 - 5 + 1))));
                } catch (InterruptedException ex) {
                    System.out.println("[ERROR] SLEEP:  " + ex.getMessage());
                }
            }
        }).start();

        boolean success = InstallHelper.install(version);

        new Thread(() -> {
            if (success)
                Main.frame.progress_bar.setValue(100);
        }).start();

        if (!success) error.setText("Unable to install Client!");
        new Thread(() -> {
            try {
                while (true) {
                    if (progress_bar.getValue() >= 100 && success) {
                        Thread.sleep(2000);
                        progress_bar.setVisible(false);
                        break;
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                System.out.println("[ERROR] SLEEP:  " + ex.getMessage());
            }
        }).start();

    }

    private void btn_uninstallMouseClicked(MouseEvent e) {
        if (!btn_uninstall.isEnabled()) return;
        error.setText("");

        int version_index = this.installed_table.getSelectedRow();
        if (version_index == -1) {
            error.setText("No Row chosen");
            return;
        }

        Version version = InstallHelper.getInstalled().get(version_index);

        progress_bar.setVisible(true);
        new Thread(() -> {
            for (int i = 0; i < 101; i++) {
                if (progress_bar.getValue() >= 100) break;

                progress_bar.setValue(i);
                try {
                    Thread.sleep((long) (100 + (Math.random() * (5 - 1 + 1))));
                } catch (InterruptedException ex) {
                    System.out.println("[ERROR] SLEEP:  " + ex.getMessage());
                }
            }
        }).start();

        boolean success = InstallHelper.uninstall(version);
        if (!success) error.setText("Unable to uninstall Client!");
        new Thread(() -> {
            try {
                while (true) {
                    if (progress_bar.getValue() >= 100) {
                        Thread.sleep(2000);
                        progress_bar.setVisible(false);
                        break;
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                System.out.println("[ERROR] SLEEP:  " + ex.getMessage());
            }
        }).start();
    }

    private void installed_table_MouseClicked(MouseEvent e) {
        for (Version version : InstallHelper.getInstalled()) {
            if (Objects.equals(version.getName(), InstallHelper.getInstalled().get(installed_table.getSelectedRow()).getName())) {
                if (version.isInstalled()) {
                    btn_uninstall.setDisplayedMnemonicIndex(0);
                    btn_uninstall.setEnabled(true);

                    btn_install.setDisplayedMnemonicIndex(0);
                    btn_install.setEnabled(false);
                } else {
                    btn_install.setDisplayedMnemonicIndex(0);
                    btn_install.setEnabled(true);

                    btn_uninstall.setDisplayedMnemonicIndex(0);
                    btn_uninstall.setEnabled(false);
                }
            }
        }
    }

    private void btn_exitMouseClicked(MouseEvent e) {
        this.dispose();
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("InstallerFrame");
        window_label = new JLabel();
        error = new JLabel();
        progress_bar = new JProgressBar();
        btn_exit = new JButton();
        btn_exit.putClientProperty("JButton.buttonType", "roundRect");

        btn_install = new JButton();
        btn_install.putClientProperty("JButton.buttonType", "roundRect");

        btn_uninstall = new JButton();
        btn_uninstall.putClientProperty("JButton.buttonType", "roundRect");


        scrollPane1 = new JScrollPane();

        List<Version> versions = InstallHelper.getInstalled();
        String[] column_names = { "Test", "Test1" };
        List<Object[]> data = new ArrayList<>(versions.size() + 1);

        for (Version version : versions) {
            data.add(new Object[] { version.getName(), version.isInstalled() });
        }

        Object[][] t = data.toArray(new Object[0][]);
        installed_table = new JTable(t, column_names);
        installed_table.setSelectionMode(0);



        //======== this ========
        setMinimumSize(new Dimension(900, 550));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- window_label ----
        window_label.setText(bundle.getString("InstallerFrame.window_label.text"));
        window_label.setAutoscrolls(true);
        window_label.setFont(window_label.getFont().deriveFont(window_label.getFont().getStyle() | Font.BOLD, window_label.getFont().getSize() + 20f));
        window_label.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(window_label);
        window_label.setBounds(0, 0, 900, 60);

        //---- btn_install ----
        btn_install.setText(bundle.getString("InstallerFrame.btn_install.text"));
        btn_install.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_installMouseClicked(e);
            }
        });
        contentPane.add(btn_install);
        btn_install.setBounds(380, 450, 135, 40);

        //---- btn_uninstall ----
        btn_uninstall.setText(bundle.getString("InstallerFrame.btn_uninstall.text"));
        btn_uninstall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_uninstallMouseClicked(e);
            }
        });
        contentPane.add(btn_uninstall);
        btn_uninstall.setBounds(210, 450, 135, 40);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(installed_table);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(25, 60, 850, 305);

        //======== installed_table ========
        installed_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                installed_table_MouseClicked(e);
            }
        });


        //---- error ----
        error.setEnabled(false);
        error.setFont(error.getFont().deriveFont(error.getFont().getSize() + 3f));
        error.setHorizontalAlignment(SwingConstants.CENTER);
        error.setForeground(new Color(238, 238, 238));
        error.setBackground(new Color(238, 238, 238));
        contentPane.add(error);
        error.setBounds(0, 380, 900, 30);
        contentPane.add(progress_bar);
        progress_bar.setBounds(235, 420, 430, 20);

        //---- btn_exit ----
        btn_exit.setText(bundle.getString("InstallerFrame.btn_exit.text"));
        btn_exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_exitMouseClicked(e);
            }
        });
        contentPane.add(btn_exit);
        btn_exit.setBounds(565, 450, 100, 40);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JLabel window_label;
    public JButton btn_install;
    public JButton btn_uninstall;
    public JScrollPane scrollPane1;
    public JTable installed_table;
    public JLabel error;
    public JProgressBar progress_bar;
    public JButton btn_exit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
