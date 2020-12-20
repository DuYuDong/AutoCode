package com.dyd;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Monitor {


    public Monitor()  {
        boolean [] on_off={true};
        new Thread(new KeyboardHook(on_off)).start();
        final TrayIcon trayIcon;

        if (SystemTray.isSupported()) {

            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("..//src//main//resources//monitor.png");

            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            };

            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);

            trayIcon = new TrayIcon(image, "monitor", popup);

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Action Event", "An Action Event Has Been Peformed!", TrayIcon.MessageType.INFO);
                }
            };

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }

        }
    }
    public static void main(String[] args)  {
        Test.GV();
        new Monitor();
    }
}
