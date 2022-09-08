package org.sparkle;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.sparkle.util.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * created by Sparkle_A on 2022-09-07
 */
public class Window extends JFrame {
    private final JLabel jl = new JLabel();
    private final JLabel jl1 = new JLabel();
    private final JLabel jl2 = new JLabel();
    private final JLabel jl3 = new JLabel();
    private final JLabel jl4 = new JLabel();
    private final JLabel jl5 = new JLabel();


    private final JLabel msg = new JLabel(" ");
    private final JPanel jp = new JPanel();
    private final JPanel jp1 = new JPanel();
    private final JPanel jp2 = new JPanel();
    private final JButton show = new JButton("ȫ��");
    private final JToggleButton hide = new JToggleButton("��С��");
    private final JButton restore = new JButton("�ָ�");
    private final JToggleButton alwaysOnTop = new JToggleButton("ǿ���ö�(��Ӱ����꽹��)");
    public boolean front = false;


    public Window() {
        this.setTitle("ClassInKiller");
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setSize(240, 288);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jl.setText("����: ǳ��");
        jl1.setText("QQ: 2604372114");
        jl2.setText("������С����ClassIn���һ���˸");
        jl3.setText("��ʱ���ٵ��ClassInKiller����ClassIn");
        jl4.setText("���ҽ���ֹͣ��˸��������С��״̬");
        jl5.setText("�ɹ���������С����ť�Խ�����Դ����");

        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl3.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl4.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl5.setAlignmentX(Component.CENTER_ALIGNMENT);

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.add(jl);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        jp.add(jl5);

        jp1.add(show);
        jp1.add(hide);
        jp1.add(restore);
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        alwaysOnTop.setAlignmentX(Component.CENTER_ALIGNMENT);
        jp2.add(msg);
        jp2.add(alwaysOnTop);
        this.add(jp, BorderLayout.PAGE_START);
        this.add(jp1, BorderLayout.PAGE_END);
        this.add(jp2);
        final boolean[] run = {false};
//        this.add(jp3);
        show.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final User32 user32 = User32.INSTANCE;
                final boolean[] success = {false};

                //                    int count = 0;
                user32.EnumWindows((hWnd, arg1) -> {
                    char[] windowText = new char[512];
                    user32.GetWindowText(hWnd, windowText, 512);
                    String wText = Native.toString(windowText);
                    WinDef.RECT rectangle = new WinDef.RECT();
                    user32.GetWindowRect(hWnd, rectangle);
                    // get rid of this if block if you want all windows regardless
                    // of whether
                    // or not they have text
                    // second condition is for visible and non minimised windows
                    if (wText.isEmpty() || !User32.INSTANCE.IsWindowVisible(hWnd)) {
                        return true;
                    }
                    if (wText.startsWith("Classroom_")) {
                        success[0] = true;
                        if (WindowUtils.showWindow(hWnd)) {
                            msg.setText("ȫ���ɹ�");
                        } else {
                            msg.setText("ȫ��ʧ��");
                        }
                    }
                    //                        System.out.println("Found window with text " + hWnd
                    //                                + ", total " + ++count + " Text: " + wText);
                    return true;
                }, null);
                if (!success[0]) {
                    msg.setText("����δ�ҵ�");
                }
            }
        });
        hide.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run[0] = !run[0];
                final User32 user32 = User32.INSTANCE;
                final boolean[] success = {false};
                //                    int count = 0;
                user32.EnumWindows((hWnd, arg1) -> {
                    char[] windowText = new char[512];
                    user32.GetWindowText(hWnd, windowText, 512);
                    String wText = Native.toString(windowText);
                    WinDef.RECT rectangle = new WinDef.RECT();
                    user32.GetWindowRect(hWnd, rectangle);
                    // get rid of this if block if you want all windows regardless
                    // of whether
                    // or not they have text
                    // second condition is for visible and non minimised windows
                    if (wText.isEmpty() || !(User32.INSTANCE.IsWindowVisible(hWnd)
                            && rectangle.left > -32000)) {
                        return true;
                    }
                    if (wText.startsWith("Classroom_")) {
                        success[0] = true;
                        final int[] count = {0};
                        final long[] startTime = {0};
                        new Thread(() -> {
                            while (run[0]) {
                                //rectangle.left <= -32000
                                WindowUtils.hideWindow(hWnd);
                                if (rectangle.left <= -32000) {
                                    if (count[0] == 0) {
                                        startTime[0] = System.currentTimeMillis();
                                    }

                                    if (System.currentTimeMillis() - startTime[0] > 1000) {
                                        startTime[0] = 0;
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                count[0]++;
                            }
                        }).start();
                    }
                    return true;
                }, null);
                if (!success[0] && run[0]) {
                    msg.setText("����δ�ҵ�");
                }
            }
        });

        restore.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final User32 user32 = User32.INSTANCE;
                final boolean[] success = {false};
                //int count = 0;
                user32.EnumWindows((hWnd, arg1) -> {
                    char[] windowText = new char[512];
                    user32.GetWindowText(hWnd, windowText, 512);
                    String wText = Native.toString(windowText);
                    WinDef.RECT rectangle = new WinDef.RECT();
                    user32.GetWindowRect(hWnd, rectangle);
                    // get rid of this if block if you want all windows regardless
                    // of whether
                    // or not they have text
                    // second condition is for visible and non minimised windows
                    if (wText.isEmpty() || !User32.INSTANCE.IsWindowVisible(hWnd)) {
                        return true;
                    }
                    if (wText.startsWith("Classroom_")) {
                        success[0] = true;
                        if (WindowUtils.restoreWindow(hWnd)) {
                            msg.setText("�ָ��ɹ�");
                        } else {
                            msg.setText("�ָ�ʧ��");
                        }
                    }
                    return true;
                }, null);
                if (!success[0]) {
                    msg.setText("����δ�ҵ�");
                }
            }
        });

        alwaysOnTop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                front = !front;
            }
        });
        this.setVisible(true);
    }
}
