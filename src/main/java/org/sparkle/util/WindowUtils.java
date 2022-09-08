package org.sparkle.util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

/**
 * created by Sparkle_A on 2022-08-21
 */
public class WindowUtils {
    public static int SW_MINIMIZE = 0x06;
    public static int SW_MAXIMIZE = 0x03;
    public static int SW_RESTORE = 0x09;


    public static boolean showWindow(WinDef.HWND hwnd) {
        // ��һ��������Windows����Ĵ����࣬�ڶ��������Ǵ���ı��⡣����Ϥwindows��̵���Ҫ����һЩWindows�������ݽṹ��֪ʶ������������windows��Ϣѭ�����������Ķ������ÿ�̫�ࡣ
//        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
        if (hwnd == null) {
            return false;
        } else {
            User32.INSTANCE.ShowWindow(hwnd, SW_MAXIMIZE);
            User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
            return true;
        }
    }

    public static boolean restoreWindow(WinDef.HWND hwnd) {
//        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
        if (hwnd == null) {
            return false;
        } else {
            User32.INSTANCE.ShowWindow(hwnd, SW_RESTORE);
            User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
            return true;
        }
    }

    public static boolean hideWindow(WinDef.HWND hwnd) {
//        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
        if (hwnd == null) {
            return false;
        } else {
            User32.INSTANCE.ShowWindow(hwnd, SW_MINIMIZE);
            User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
            return true;
        }
    }
}
