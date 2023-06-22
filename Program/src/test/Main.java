package test;

import java.sql.Connection;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.formlogin.FormLogin;
import view.formregister.FormRegister;
import view.screen.Screen;
import libary.ConnectSQL;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		/*
		 * Look and feel javax.swing.plaf.metal.MetalLookAndFeel
		 * javax.swing.plaf.nimbus.NimbusLookAndFeel
		 * com.sun.java.swing.plaf.motif.MotifLookAndFeel
		 * com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		 * com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
		 */
		try {
			UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
			String lookandfeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			for (UIManager.LookAndFeelInfo look : looks) {
				if (look.getClassName().equals(lookandfeel)) {
					UIManager.setLookAndFeel(lookandfeel);
				}
			}
			Screen form = new Screen("Chương trình quản lý file và folder", null, false);
			System.out.println("Tải chương trình thành công");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Tải chương trình thất bại");
		}
	}
}
