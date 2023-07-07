package view.toolbar;

import java.awt.BorderLayout;
import view.screen.Screen;
import javax.swing.JPanel;
import define.ColorList;
import model.Element;

public class Screen_ToolBar extends JPanel {
	/**
	 * 
	 */
	private Panel_Functions function;
	private Panel_Navigation navigation;
	private Screen screen;
	private Boolean islogin;

	public Screen_ToolBar(Screen screen, Boolean islogin, Element root) {
		super();
		try {
			if (screen != null)
				this.screen = screen;
			else
				this.screen = null;
			this.islogin = islogin;
			this.function = new Panel_Functions(this.screen, this.islogin);
			this.navigation = new Panel_Navigation(this.screen, root);
			this.setColor();
			this.setLayout(new BorderLayout());
			this.add(function, BorderLayout.CENTER);
			this.add(navigation, BorderLayout.SOUTH);
			System.out.println("Upload success ToolBar");
		} catch (Exception e) {
			System.out.println("Error ToolBar");
		}
	}

	private void setColor() {
		this.navigation.setOpaque(true);
		this.navigation.setBackground(ColorList.Back_Ground);
		this.navigation.setForeground(ColorList.Fore_Ground);
		this.function.setForeground(ColorList.Fore_Ground);
		this.setOpaque(true);
		this.setBackground(ColorList.Back_Ground);
	}

	public void Show_Hide_Function() {
		if (function.isVisible()) {
			this.function.setVisible(false);
		} else
			this.function.setVisible(true);
	}

	public Panel_Functions getFunction() {
		return function;
	}

	public Panel_Navigation getNavi() {
		return navigation;
	}
}
