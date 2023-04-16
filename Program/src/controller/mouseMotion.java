package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import view.screen.Screen;

public class mouseMotion implements MouseMotionListener{
    private Screen sc;
    mouseMotion(Screen sc)
    {
    	this.sc = sc;
    }
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.print(e.getSource());
	}

}
