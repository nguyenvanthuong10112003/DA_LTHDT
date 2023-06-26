package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.content.PanelContent;
import view.content.center.PanelContentCenter;
import view.content.left.TreeBar;
import view.menubar.Screen_MenuBar;
import view.screen.Screen;

public class action implements ActionListener {
	private Screen sc;
	private Screen_MenuBar menu;
	private PanelContent pc;
	private TreeBar tree;
	private PanelContentCenter pct;

	public action(Screen sc) {
		super();
		this.sc = sc;
	}

	public action(Screen_MenuBar menu) {
		super();
		this.menu = menu;
	}

	public action(PanelContent pc) {
		this.pc = pc;
	}

	public action(TreeBar tree) {
		this.tree = tree;
	}

	public action(PanelContentCenter pct) {
		this.pct = pct;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (sc != null) {
			if (e.getSource().equals(sc)) {
				System.out.println("ok");
			}
		}
		if (menu != null) {
			menu.handleEvent(e.getSource().hashCode());
		} else if (pc != null) {

		} else if (tree != null) {
			tree.eventaction(e.getSource().hashCode());
		} else if (pct != null) {
			pct.clickedItemPopup(e.getSource().hashCode());
		}
	}

}
