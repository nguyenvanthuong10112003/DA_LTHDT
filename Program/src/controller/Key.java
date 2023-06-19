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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(navi != null)
		{
			if(e.getSource().equals(navi.getInput_Location().getComponents()[2]))
			{
				navi.eventKeySearchLocation(e.getKeyCode(), e.getKeyChar());
			} else if(e.getSource().equals(navi.getInput_Search().getComponents()[2]))
			{
				navi.eventKeySearch(e.getKeyCode(), e.getKeyChar());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(navi != null)
		{
			if(e.getSource().equals(navi.getInput_Location().getComponents()[2]))
			{
				navi.eventKeySearchLocation(e.getKeyCode(), e.getKeyChar());
			} else if(e.getSource().equals(navi.getInput_Search().getComponents()[2]))
			{
				navi.eventKeySearch(e.getKeyCode(), e.getKeyChar());
			}
		}
	}

}
