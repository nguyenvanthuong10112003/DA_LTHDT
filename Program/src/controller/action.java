package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.menubar.Screen_MenuBar;
import view.screen.Screen;

public class action implements ActionListener{
    private Screen sc;
    private Screen_MenuBar menu;
    public action(Screen sc)
    {
    	super();
    	this.sc = sc;
    }
    public action(Screen_MenuBar menu)
    {
    	super();
    	this.menu = menu;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(sc != null) {
		if(e.getSource().equals(sc))
		{
			System.out.println("ok");
		}}
		if(menu != null)
		{
			menu.handleEvent(e.getSource().hashCode());
		}
		
	}

}
