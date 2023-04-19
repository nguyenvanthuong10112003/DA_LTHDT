package controller;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import libary.ColorList;
import libary.JLabelIcon;
import view.content.PanelContent;
import view.content.center.PanelContentCenter;
import view.content.left.ScrollPaneTree;
import view.content.left.TreeBar;
import view.content.right.PanelContentRight;
import view.menubar.Screen_MenuBar;
import view.screen.Screen;
import view.toolbar.JPanelFunction;
import view.toolbar.ScreenToolBar;

public class mouse extends MouseAdapter{
	private JPanelFunction pf;
	private PanelContentRight cr;
	private JPanel content;
	private ScreenToolBar tb;
	private Screen sc;
	private ScrollPaneTree scroll;
	private TreeBar tree;
	private Boolean mousep = false;
	private Screen_MenuBar menu;
	private PanelContentCenter pct;
	public mouse(JPanelFunction pf)
	{
		this.pf = pf;
	}
	public mouse(ScreenToolBar tb)
	{
		this.tb = tb;
	}
	public mouse(PanelContentCenter pct)
	{
		this.pct = pct;
	}
	public mouse(PanelContent content)
	{
		this.content = content;
	}
	public mouse(Screen_MenuBar menu)
	{
		this.menu = menu;
	}
	public mouse(Screen sc)
	{
		this.sc = sc;
	}
	public mouse(TreeBar tree)
	{
		this.tree = tree;
	}
	public mouse(PanelContentRight cr)
	{
		this.cr = cr;
	}
	public mouse(ScrollPaneTree scroll, Screen sc, TreeBar tree)
	{
		this.scroll = scroll;
		this.sc = sc;
		this.tree = tree;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getSource());
		/*try {
            if(e.getSource().getClass().equals(JButton.class))
            {
            	tb.onclick_Button((JButton)e.getSource());
            }
            if(e.getSource().getClass().equals(ScrollPaneTree.class))
            {
            	scroll.SetCusorScroll(e.getX(), e.getY());
            }
            	
		}
		catch(Exception ex)
		{
			System.out.print("error");
		}*/
		if(tree != null)
		{
			if(e.getSource().equals(tree.getLabelIconClose()))
			{
				tree.closeClick();
			}
		}
	    else if(cr != null)
		{
			if(e.getSource().equals(cr.getLabelIconClose()))
			{
				//System.out.println(e.getSource());
				cr.closeClick();
			}
		}
		else if(menu != null)
		{
			System.out.println(e.getSource());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().getClass().equals(ScrollPaneTree.class))
		{
			mousep = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(pf != null) {
			if(e.getSource().getClass().equals(JLabelIcon.class))
			{
				pf.setColorIcon(e.getSource().hashCode(), ColorList.Back_Ground);
			}
		}
		else if(tree != null) {
			if(e.getSource().equals(tree.getLabelIconClose()))
			{
				tree.exitClose();
			}
		}
		else if(cr != null) {
		if(e.getSource().equals(cr.getLabelIconClose()))
		{
			cr.exitClose();
		}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getSource().getClass().equals(ScrollPaneTree.class) || e.getSource().getClass().equals(sc.getClass()) || e.getSource().getClass().equals(TreeBar.class) )
		{
			try {
				mousep = true;
				//scroll.SetSize(e.getX(), e.getY());
			}
			catch(Exception ex)
			{
				System.out.println("error");
			}
		}*/
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getSource().getClass().equals(ScrollPaneTree.class) || e.getSource().getClass().equals(TreeBar.class) || e.getSource().getClass().equals(sc.getClass()))
		{
			try {
				if(e.getSource().getClass().equals(ScrollPaneTree.class))
				scroll.SetCusorScroll(e.getX(), e.getY());
				//else 
			    //scroll.SetCusorTreeBar(e.getX(), e.getY());
			}
			catch(Exception ex)
			{
				System.out.println("error");
			}
		}*/
		if(pf != null) {
			if(e.getSource().getClass().equals(JLabelIcon.class))
			{
				pf.setColorIcon(e.getSource().hashCode(), ColorList.Hover);
			}
		}
		else if(tree != null) {
			if(e.getSource().equals(tree.getLabelIconClose()))
			{
				tree.hoverClose();;
			}
		}
		else if(pct != null)
		{
		}
		else if(cr != null) {
		if(e.getSource().equals(cr.getLabelIconClose()))
		{
			cr.hoverClose();
		}
		}
	}

}
