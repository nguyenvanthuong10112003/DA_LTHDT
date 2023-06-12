package view.content.left;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Element;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import libary.ColorList;
import libary.URL;

public class TreeRender extends DefaultTreeCellRenderer{
	private String down = "down16px.png";
	private String right = "right16px.png";
	private String duoi = "16px.png";
	public TreeRender()
	{
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus)
	{
		Component com = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Element treenode = (Element) node.getUserObject();
		setText(treenode.getName());
		ImageIcon icon = new ImageIcon(URL.url + URL.urlContentLeftFolder + treenode.getIcon() + duoi);
		setIcon(icon);
		return com;
	}

}
