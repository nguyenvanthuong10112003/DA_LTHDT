package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.screen.Screen;

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
			System.out.println("Upload success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
