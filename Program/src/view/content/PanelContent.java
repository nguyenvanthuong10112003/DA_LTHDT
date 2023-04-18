package view.content;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import controller.mouse;
import view.content.center.PanelContentCenter;
import view.content.left.ScrollPaneTree;
import view.content.right.PanelContentRight;

public class PanelContent extends JPanel{
       private ScrollPaneTree contentLeft;
       private PanelContentCenter contentCenter;
       private PanelContentRight contentRight;
       private int space;
       private mouse mouseEvent;
       private Boolean Show_Content_Left;
       private Boolean Show_Content_Right;
       private Boolean Show_Content_Center;
       private Font font;
       private Color back;
       private Color fore;
       public PanelContent()
       {
    	   super();
    	   this.contentLeft = new ScrollPaneTree();
    	   this.contentCenter = new PanelContentCenter();
    	   this.contentRight = new PanelContentRight(this);
    	   this.space = 0;
    	   this.mouseEvent = new mouse(this);
    	   this.Show_Content_Left = true;
    	   this.Show_Content_Right = true;
    	   this.Show_Content_Center = true;
    	   this.font = new Font("Arial", Font.PLAIN, 14);
    	   this.back = Color.WHITE;
    	   this.fore = Color.BLACK;
    	   this.getSize();
    	   this.addObj();
    	   this.setColorObj();
       }
       public void update(int width, int height)
       {
    	   if(this.Show_Content_Left) {
    		   contentLeft.setVisible(true);
    		   contentLeft.getTreeBar().setVisible(true);
    	   }
    	   else {
    		   contentLeft.setVisible(false);
    		   contentLeft.getTreeBar().setVisible(false);
    	   }
    	   if(this.Show_Content_Right) contentRight.setVisible(true);
    	   else contentRight.setVisible(false);
    	   this.setBoundsObj(width, height);
    	   contentLeft.getTreeBar().setIconClose(18);
       }
       private void addObj()
       {
    	   this.setLayout(null);
    	   this.add(contentLeft);
    	   this.add(contentCenter);
    	   this.add(contentRight);
       }
       public void setSpace(int sp)
       {
    	   space = sp;
       }
       private void setColorObj()
       {
    	   this.contentCenter.setOpaque(true);
    	   //this.contentCenter.setBackground(back);
    	   this.contentCenter.setForeground(fore);
    	   
    	   this.contentRight.setOpaque(true);
    	   this.contentRight.setBackground(back);
    	   this.contentRight.setForeground(fore);	   
       }
       public ScrollPaneTree getPanelContentLeft()
       {
    	   return contentLeft;
       }
       public void setBoundsObj(int width, int height)
       {
    	   if(Show_Content_Left)
    	   this.contentLeft.setBounds(space, space, 200, height - space*2);
    	   else
    	   this.contentLeft.setSize(0,0);
    	   if(Show_Content_Right)
    	   this.contentRight.setBounds(width - 200 - space, space,200, height - space*2);
    	   else
    	   this.contentRight.setSize(0,0);
    	   this.contentCenter.setBounds(contentLeft.getSize().width + (Show_Content_Left ? space*2 : space), space, width - contentLeft.getSize().width - contentRight.getSize().width - (Show_Content_Right ? space*2 : space) - (Show_Content_Left ? space*2 : space), height - space*2);
           ///System.out.println(this.contentLeft.isVisible());
           //System.out.println(this.contentLeft.getTreeBar().isVisible());
       }
       public void ClosePanelRight()
       {
    	   Show_Content_Right = false;
    	   update(this.getSize().width, this.getSize().height);
       }
       public void ShowPanelRight()
       {
    	   if(Show_Content_Right)
    		   return;
    	   else
    	   {
    		   Show_Content_Right = true;
    		   update(this.getSize().width, this.getSize().height);
    	   }
       }
       public void ClosePanelLeft()
       {
    	   Show_Content_Left = false;
    	   update(this.getSize().width, this.getSize().height);
       }
       public void ShowPanelLeft()
       {
    	   if(Show_Content_Left)
    		   return;
    	   else
    	   {
    		   Show_Content_Left = true;
    		   update(this.getSize().width, this.getSize().height);
    	   }
       }
}
