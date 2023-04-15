package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.screen.Screen;
import view.toolbar.ScreenToolBar;

public class mouse implements MouseListener{
	private ScreenToolBar tb;
	private Screen sc;
	public mouse(ScreenToolBar tb)
	{
		this.tb = tb;
	}
	public mouse(Screen sc)
	{
		this.sc = sc;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
            if(e.getSource().getClass().equals(JButton.class))
            {
            	sc.onclick_Button((JButton)e.getSource());
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

}
