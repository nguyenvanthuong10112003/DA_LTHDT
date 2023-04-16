package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import view.screen.Screen;
import view.toolbar.ScreenToolBar;
import view.treebar.ScrollPaneTree;
import view.treebar.TreeBar;

public class mouse extends MouseAdapter{
	private ScreenToolBar tb;
	private Screen sc;
	private ScrollPaneTree scroll;
	public mouse(ScreenToolBar tb)
	{
		this.tb = tb;
	}
	public mouse(Screen sc)
	{
		this.sc = sc;
	}
	public mouse(ScrollPaneTree scroll)
	{
		this.scroll = scroll;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
            if(e.getSource().getClass().equals(JButton.class))
            {
            	tb.onclick_Button((JButton)e.getSource());
            }
		}
		catch(Exception ex)
		{
			System.out.print("error");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().getClass().equals(ScrollPaneTree.class) || e.getSource().getClass().equals(TreeBar.class))
		{
			try {
				scroll.SetSize(e.getX(), e.getY());
			}
			catch(Exception ex)
			{
				System.out.println("error");
			}
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().getClass().equals(ScrollPaneTree.class) || e.getSource().getClass().equals(TreeBar.class))
		{
			try {
				scroll.SetCusor(e.getX(), e.getY());
			}
			catch(Exception ex)
			{
				System.out.println("error");
			}
		}
	}

}
