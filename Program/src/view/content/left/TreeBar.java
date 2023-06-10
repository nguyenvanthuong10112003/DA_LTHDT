package view.content.left;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Enumeration;
import java.util.EventObject;

import libary.MyTreeNode;
import libary.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

import controller.mouse;
import libary.ColorList;
import libary.FONT;
import model.Element;
import model.Folder;
import view.content.PanelContent;

public class TreeBar extends JTree {
	private PanelContent pc;
	private JLabel close;
	private mouse mouseListen;
	private String iconClose16 = "\\Icon\\content\\left\\close16.png";
	private String iconClose24 = "\\Icon\\content\\left\\close24.png";
	private String iconFolder = "\\Icon\\content\\left\\folder\\folderIcon16px.png";
	private String px = "16px";
	private String duoi = ".png";
	private String urlIconFolder = "\\Icon\\content\\left\\folder\\folderIcon16px.png";
	private String urlIconFile = "\\Icon\\content\\center\\file\\";
	private DefaultMutableTreeNode rootTree;
	private Element root;
	public TreeBar(PanelContent pc, Element root) {
		super();
		this.pc = pc;
		this.root = root;
		this.setRootTree();
		this.setModel(new DefaultTreeModel(rootTree));
		this.close = new JLabel(new ImageIcon(libary.URL.url + iconClose16));
		this.add(close);
		this.mouseListen = new mouse(this);
		this.addMouseListener(mouseListen);
		this.addMouseMotionListener(mouseListen);
		this.close.addMouseListener(mouseListen);
		this.close.addMouseMotionListener(mouseListen);
		TreeRender render = new TreeRender();
		this.setCellRenderer(render);
		this.Edit();
	}
	
	public void Edit()
	{
		this.close.setBackground(new Color(102, 153, 204));
		this.setFont(FONT.font_mac_dinh);
		this.setRowHeight(18);
		this.setShowsRootHandles(true);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.white);
	}

	public void setRootTree() {
		rootTree = addNodeTree(rootTree, root);
	}

	public void Update()
	{
		this.setRootTree();
		this.setModel(new DefaultTreeModel(rootTree));
	}
	
	public DefaultMutableTreeNode addNodeTree(DefaultMutableTreeNode rootTree, Element root)
	{
		rootTree = new DefaultMutableTreeNode(new view.content.left.MyTreeNode("", root.getName(), root.getIcon()));
		for(int i = 0; i < root.getChildrents().size(); i++) {
			if(root.getChildrents().get(i).getClass().equals(Folder.class))
			{
				DefaultMutableTreeNode node = new DefaultMutableTreeNode();
				if(root.getChildrents().size() > 0)
					node = addNodeTree(node, root.getChildrents().get(i));
				else
					node = new DefaultMutableTreeNode(new view.content.left.MyTreeNode("", root.getChildrents().get(i).getName(), root.getChildrents().get(i).getIcon()));
				rootTree.add(node);
			}
		}
		return rootTree;
	}

	public void setPanelContent(PanelContent pc) {
		this.pc = pc;
	}

	public void setIconClose(int size) {
		this.setVisible(false);
		close.setBounds(this.getSize().width - size, 0, size, size);
		this.setVisible(true);
	}

	public JLabel getIconClose() {
		return close;
	}

	public void hoverClose() {
		close.setIcon(new ImageIcon(libary.URL.url + iconClose24));
		setIconClose(24);
	}

	public void exitClose() {
		close.setIcon(new ImageIcon(libary.URL.url + iconClose16));
		setIconClose(18);
	}

	public void closeClick() {
		pc.ClosePanelLeft();
	}
}
