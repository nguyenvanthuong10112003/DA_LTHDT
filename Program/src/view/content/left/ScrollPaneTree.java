package view.content.left;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.mouse;
import libary.JLabelIcon;
import view.screen.Screen;

public class ScrollPaneTree extends JScrollPane
{
	private int width;
	private int height;
	private int space;
	private TreeBar tree;
	public ScrollPaneTree()
	{
		 super();
         tree = new TreeBar(new DefaultMutableTreeNode("This pc"));
		 this.setViewportView(tree);
         this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
         this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
	}
	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		tree.setSize(width - (width >= 20 ? 20 : 0), height - (height >= 20 ? 20 : 0));
	}
	public void setTree(DefaultMutableTreeNode root)
	{
		tree = new TreeBar(root);
	}
	public void SetCusorScroll(int x, int y)
	{

		if(x >= this.getSize().width - 2 && x <= this.getSize().width + space + 2 && y >= this.getSize().height - 2 && y <= this.getSize().height + space + 2)
		{
			this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
		}
		else if(x >= this.getSize().width - 2 && x <= this.getSize().width + space + 2)
		{
			this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
		}
		else if(y >= this.getSize().height - 2 && y <= this.getSize().height + space + 2)
		{
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
		}
		else 
		{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public TreeBar getTreeBar()
	{
		return tree;
	}
}
