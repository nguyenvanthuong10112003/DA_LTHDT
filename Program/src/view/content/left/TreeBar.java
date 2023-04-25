package view.content.left;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.mouse;
import libary.FONT;
import libary.JLabelIcon;
import view.content.PanelContent;
public class TreeBar extends JTree{
	  private PanelContent pc;
	  private JLabelIcon close;
	  private mouse mouseListen;
	  private String iconClose16 = "close16.png";
	  private String iconClose24 = "close24.png";
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
    	  this.setBorder(new EmptyBorder(5,5,5,5));
    	  this.setBackground(Color.white);
          close = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(iconClose16))));
          this.add(close);
    	  mouseListen = new mouse(this);
    	  this.addMouseListener(mouseListen);
    	  this.addMouseMotionListener(mouseListen);
    	  close.addMouseListener(mouseListen);
    	  close.addMouseMotionListener(mouseListen);
    	  close.setBackground(new Color(102,153,204));
    	  this.setFont(FONT.font_mac_dinh);
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
    	  close.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(iconClose24))));
   	      setIconClose(24);
      }
      public void exitClose()
      {
   	      close.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(iconClose16))));
	      setIconClose(18);
      }
      public void closeClick()
      {
   	      pc.ClosePanelLeft();
      }
      /*public void setCursor(int x, int y)
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
      }*/
}
