package org.sparkle;

import javax.swing.*;

/**
 * created by Sparkle_A on 2022-09-07
 */
public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        Window window = new Window();
        new Thread(() -> {
            while (true) {
                if (window.front && window.getExtendedState() != JFrame.ICONIFIED) {
                    window.toFront();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
