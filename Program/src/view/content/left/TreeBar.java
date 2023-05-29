package view.content.left;

import java.awt.*;
import java.io.File;
import java.util.Enumeration;
import libary.MyTreeNode;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.JLabel;
import controller.mouse;
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
	private String url;
	private String px = "16px";
	private String duoi = ".png";
	private String urlIconFolder = "\\Icon\\content\\center\\folder\\";
	private String urlIconFile = "\\Icon\\content\\center\\file\\";
	private DefaultMutableTreeNode rootTree;
	private Element root;

	public TreeBar(PanelContent pc, Element root, String url) {
		super();
		this.pc = pc;
		this.root = root;
		this.url = url;
		this.setRootTree();
		this.setModel(new DefaultTreeModel(rootTree));
		//this.setCellRenderer(new MyNodeTreeCellRender(url, iconFolder));
		this.setShowsRootHandles(true);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(Color.white);
		close = new JLabel(new ImageIcon(url + iconClose16));
		this.add(close);
		mouseListen = new mouse(this);
		this.addMouseListener(mouseListen);
		this.addMouseMotionListener(mouseListen);
		close.addMouseListener(mouseListen);
		close.addMouseMotionListener(mouseListen);
		close.setBackground(new Color(102, 153, 204));
		this.setFont(FONT.font_mac_dinh);
	}

	public void setRootTree() {
		rootTree = addNodeTree(rootTree, root);
	}

	public DefaultMutableTreeNode addNodeTree(DefaultMutableTreeNode rootTree, Element root)
	{
		rootTree = new DefaultMutableTreeNode(root.getName());
		for(int i = 0; i < root.getChildrents().size(); i++) {
			
			rootTree.add(new DefaultMutableTreeNode());
			
		}
			//if(root.getChildrents().get(i).getClass().equals(File.class))
			//        rootTree.add(new DefaultMutableTreeNode(new MyTreeNode(
			//		root.getChildrents().get(i).getId(), 
			//		root.getChildrents().get(i).getName(),
			//		url + urlIconFolder + root.getChildrents().get(i).getIcon() 
			//		+ px + duoi)));
			//else
		    //rootTree.add(new DefaultMutableTreeNode(root.getChildrents().get(i).getName()));
		//for(int i = 0; i < root.getChildrents().size(); i++)
		//	if(root.getChildrents().get(i).getClass().equals(Folder.class)) {
//
		 //   }
		return rootTree;
	}
	
	/*private class MyNodeTreeCellRender extends DefaultTreeCellRenderer {
		private String iconFolder;
		private String url;

		public MyNodeTreeCellRender(String url, String iconFolder) {
			super();
			this.url = url;
			this.iconFolder = iconFolder;
		}

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object object, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {

			Component component = super.getTreeCellRendererComponent(tree, object, sel, expanded, leaf, row, hasFocus);
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
			if (node.isLeaf()) {
				MyTreeNode myTreeNode = (MyTreeNode) node.getUserObject();
				setText(myTreeNode.getName());
				setIcon(new ImageIcon(myTreeNode.getIcon()));
			} else {
				setLeafIcon(new ImageIcon(this.url + this.iconFolder));
				setOpenIcon(new ImageIcon(this.url + this.iconFolder));
				setClosedIcon(new ImageIcon(this.url + this.iconFolder));
			}
			return component;
		}

	}*/

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
		close.setIcon(new ImageIcon(url + iconClose24));
		setIconClose(24);
	}

	public void exitClose() {
		close.setIcon(new ImageIcon(url + iconClose16));
		setIconClose(18);
	}

	public void closeClick() {
		pc.ClosePanelLeft();
	}
}
