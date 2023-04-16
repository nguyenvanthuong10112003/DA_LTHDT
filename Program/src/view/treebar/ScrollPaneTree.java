package view.treebar;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.mouse;
import view.panelcontent.JPanelContent;

public class ScrollPaneTree extends JScrollPane
{
	private double width;
	private double height;
	private TreeBar tree;
	private mouse mouselisten;
	private JPanelContent panelcontent;
	public ScrollPaneTree(DefaultMutableTreeNode root, JPanelContent panelcontent)
	{
		 super();
		 this.panelcontent = panelcontent;
         width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
         height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
         init(root);
         addObj();
         addMouse();
         this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
         this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
	}
	public void addMouse()
	{
		this.addMouseMotionListener(mouselisten);
		tree.addMouseMotionListener(mouselisten);
	}
	private void init(DefaultMutableTreeNode root)
	{
		tree = new TreeBar(root);
		mouselisten = new mouse(this);
	}
	private void addObj()
	{
		this.setViewportView(tree);
		this.setBounds(5, 5, 200, 335);
	}
	public void SetCusor(int x, int y)
	{
		if(x >= this.getSize().width - 3 && y >= this.getSize().height - 3)
		{
			this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
		}
		else if(x >= this.getSize().width - 3)
		{
			this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
		}
		else if(y >= this.getSize().height - 3)
		{
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		}
		else 
		{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			tree.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public void SetSize(int x, int y)
	{
		if(x >= this.getSize().width - 3 && y >= this.getSize().height - 3)
		{
			this.setSize(x,y);
			tree.setSize(x - 10,y - 10);
			panelcontent.setBounds(10 + this.getSize().width, 5, (int)width - 15 - this.getSize().width, 100);
		}
		else if(x >= this.getSize().width - 3)
		{
			this.setSize(x, this.getSize().height);
			tree.setSize(x, tree.getSize().height);
			panelcontent.setBounds(10 + this.getSize().width, 5, (int)width - 15 - this.getSize().width, 100);
		}
		else if(y >= this.getSize().height - 3)
		{
			this.setSize(this.getSize().width, y);
			tree.setSize(tree.getSize().width, y);
		}
	}
}
