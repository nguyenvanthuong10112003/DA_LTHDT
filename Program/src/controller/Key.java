package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.toolbar.Panel_Navigation;

public class Key implements KeyListener {
	private Panel_Navigation navi;

	public Key(Panel_Navigation navi) {
		super();
		this.navi = navi;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (navi != null) {
			if (e.getSource().equals(navi.getInput_Location().getComponents()[2])) {
				navi.eventKeySearchLocation(e.getKeyCode(), e.getKeyChar());
			} else if (e.getSource().equals(navi.getInput_Search().getComponents()[2])) {
				navi.eventKeySearch(e.getKeyCode(), e.getKeyChar());
			}
		}
	}

}
