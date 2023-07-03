package view.content.left;

import java.awt.Component;
import model.Element;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import define.URL;

public class TreeRender extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String duoi = "16px.png";

	public TreeRender() {
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		Component com = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Element treenode = (Element) node.getUserObject();
		setText(treenode.getName());
		ImageIcon icon = new ImageIcon(URL.url + URL.urlContentLeftFolder + treenode.getIcon() + duoi);
		setIcon(icon);
		return com;
	}

}
