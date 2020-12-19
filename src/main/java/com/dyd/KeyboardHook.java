package com.dyd;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import java.util.HashSet;
import java.util.Set;


public class KeyboardHook implements Runnable {
    private static WinUser.HHOOK hhk;
    private static WinUser.LowLevelKeyboardProc keyboardHook;
    final static User32 lib = User32.INSTANCE;
    private boolean[] on_off = null;

    public KeyboardHook(boolean[] on_off) {
        this.on_off = on_off;
    }


    public static int out;

    public void run() {

        WinDef.HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        keyboardHook = new WinUser.LowLevelKeyboardProc() {
            public WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, WinUser.KBDLLHOOKSTRUCT info) {
                if (on_off[0] == false) {
                    System.exit(0);
                }


                out = info.vkCode;

                System.out.println("按得键位是" + out);



                return lib.CallNextHookEx(hhk, nCode, wParam, info.getPointer());
            }
        };
        hhk = lib.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                System.err.println("error in get message");
                break;
            } else {
                System.err.println("got message");
                lib.TranslateMessage(msg);
                lib.DispatchMessage(msg);
            }
        }
        lib.UnhookWindowsHookEx(hhk);
    }

    /*public static int getKey() {
        Integer[] a = new Integer[]{out};
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < a.length; i++) {
            boolean b = set.add(a[i]);
            if (!b) {
                out=i;
                System.out.println("重复数据：" + a[i] + "   索引位置：" + i);
            }
        }
            return out;
    }*/




}




/**
 *
 * https://blog.csdn.net/zhujunxxxxx/article/details/41381017?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-10.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-10.control
 *https://www.cnblogs.com/mh-study/p/9754692.html
**/
