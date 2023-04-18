package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.screen.Screen;

public class Main {
    public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        /* Look and feel
         javax.swing.plaf.metal.MetalLookAndFeel
         javax.swing.plaf.nimbus.NimbusLookAndFeel
         com.sun.java.swing.plaf.motif.MotifLookAndFeel
         com.sun.java.swing.plaf.windows.WindowsLookAndFeel
         com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
         */
    	UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            if(look.getClassName().equals("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"))
            {
            	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
        }
        Screen form = new Screen("Chương trình quản lý file và folder");
    }
}
