package view.content.left;
import java.awt.*;
import java.util.Enumeration;
import libary.MyTreeNode;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import controller.mouse;
import libary.FONT;
import libary.JLabelIcon;
import model.Element;
import view.content.PanelContent;
public class TreeBar extends JTree{
	  private PanelContent pc;
	  private JLabelIcon close;
	  private mouse mouseListen;
	  private String iconClose16 = "\\Icon\\content\\left\\close16.png";
	  private String iconClose24 = "\\Icon\\content\\left\\close24.png";
	  private String iconFolder = "\\Icon\\content\\left\\folder\\folderIcon16px.png";
	  private String url;
	  private DefaultMutableTreeNode rootTree;
	  private Element root;
      public TreeBar(Element root, String url)
      {
    	  super();
    	  this.root = root;
    	  this.url = url;
    	  this.setRootTree();
    	  this.setModel(new DefaultTreeModel(rootTree));
    	  this.setCellRenderer(new MyNodeTreeCellRender(url, iconFolder));
    	  this.setShowsRootHandles(true);
    	  this.setBorder(new EmptyBorder(5,5,5,5));
    	  this.setBackground(Color.white);
          close = new JLabelIcon(new ImageIcon(url + iconClose16));
          this.add(close);
    	  mouseListen = new mouse(this);
    	  this.addMouseListener(mouseListen);
    	  this.addMouseMotionListener(mouseListen);
    	  close.addMouseListener(mouseListen);
    	  close.addMouseMotionListener(mouseListen);
    	  close.setBackground(new Color(102,153,204));
    	  this.setFont(FONT.font_mac_dinh);
      }
      public void setRootTree()
      {
    	  rootTree = new DefaultMutableTreeNode("pc");
    	  DefaultMutableTreeNode child = new DefaultMutableTreeNode(new MyTreeNode("", "cmn", "D:\\thuong\\DALTHDT\\Nhom11\\Program\\Icon\\content\\left\\icon\\ai16px.png"));
    	  rootTree.add(child);
      }
      private class MyNodeTreeCellRender extends DefaultTreeCellRenderer
      {
        private String iconFolder;
        private String url;
        public MyNodeTreeCellRender(String url, String iconFolder)
        {
        	super();
        	this.url = url;
        	this.iconFolder = iconFolder;
        }
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object object, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			
			Component component =  super.getTreeCellRendererComponent(tree, object, sel, expanded, leaf, row, hasFocus);
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
			if(node.isLeaf())
			{
				MyTreeNode myTreeNode = (MyTreeNode) node.getUserObject();
				setText(myTreeNode.getName());
				setIcon(new ImageIcon(myTreeNode.getIcon()));
			}
			else
			{
				setLeafIcon(new ImageIcon(this.url + this.iconFolder));
				setOpenIcon(new ImageIcon(this.url + this.iconFolder));
				setClosedIcon(new ImageIcon(this.url + this.iconFolder));
			}
			return component;
		}
    	  
      }
      public void setPanelContent(PanelContent pc)
      {
    	  this.pc = pc;
      }
      public void setIconClose(int size)
      {
    	  this.setVisible(false);
    	  close.setBounds(this.getSize().width - size, 0, size, size);
    	  this.setVisible(true);
      }
      public JLabelIcon getIconClose()
      {
    	  return close;
      }
      public JLabelIcon getLabelIconClose()
      {
    	  return close;
      }
      public void hoverClose()
      {
    	  close.setIcon(new ImageIcon(url + iconClose24));
   	      setIconClose(24);
      }
      public void exitClose()
      {
   	      close.setIcon(new ImageIcon(url + iconClose16));
	      setIconClose(18);
      }
      public void closeClick()
      {
   	      pc.ClosePanelLeft();
      }
}
