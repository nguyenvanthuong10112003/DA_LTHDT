package view.treebar;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
public class TreeBar extends JTree{
      public TreeBar()
      {
    	  super();
      }
      public TreeBar(DefaultMutableTreeNode root)
      {
    	  super(root);    	  
    	  root.add(new DefaultMutableTreeNode("con 1"));
    	  root.add(new DefaultMutableTreeNode("con 2"));
    	  root.add(new DefaultMutableTreeNode("con 3"));
    	  root.add(new DefaultMutableTreeNode("con 4"));
    	  root.add(new DefaultMutableTreeNode("con 5"));
    	  root.add(new DefaultMutableTreeNode("con 6"));
    	  root.add(new DefaultMutableTreeNode("con 7"));
    	  root.add(new DefaultMutableTreeNode("con 8"));
    	  this.setShowsRootHandles(true);
      }
}
