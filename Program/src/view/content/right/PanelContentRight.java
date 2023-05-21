package view.content.right;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controller.mouse;
import libary.ColorList;
import libary.JButtonIcon;
import libary.JLabelIcon;
import view.content.PanelContent;

public class PanelContentRight extends JPanel{
	   private PanelContent pc;
	   private JLabel close;
	   private String iconClose16 = "\\Icon\\content\\right\\close16.png";
	   private String iconClose24 = "\\Icon\\content\\right\\close24.png";
	   private mouse mouselisten;
	   private Cursor cursorResize = new Cursor(Cursor.W_RESIZE_CURSOR);
	   private String url;
       public PanelContentRight(PanelContent pc, String url)
       {
    	   super();
    	   try {
	    	   this.pc = pc;
	    	   this.url = url;
	    	   mouselisten = new mouse(this, pc);
	    	   this.addMouseMotionListener(mouselisten);
	    	   this.setBorder(new LineBorder(Color.black));
	    	   this.setLayout(null);
	    	   close = new JLabelIcon(new ImageIcon(url + iconClose16));
	    	   close.addMouseMotionListener(mouselisten);
	    	   close.addMouseListener(mouselisten);
	    	   close.setBorder(new EmptyBorder(0,0,0,0));
	    	   close.setOpaque(true);
	    	   close.setBackground(Color.WHITE);
	    	   this.add(close);
	    	   close.setSize(20,20);
	    	   close.setBounds(1, 1, close.getSize().width, close.getSize().height);
	    	   System.out.println("Tải thành công content bên phải");
    	   }
    	   catch (Exception e) {
			// TODO: handle exception
    		   System.out.println("Error Content bên phải");
		}
       }
       public int getWidth()
       {
    	   return this.getSize().width;
       }
       public int getHeight()
       {
    	   return this.getSize().height;
       }       
       public JLabel getLabelIconClose()
       {
    	   return close;
       }
       public void hoverClose()
       {
    	   close.setBackground(ColorList.Hover);
       }
       public void exitClose()
       {
    	   close.setBackground(ColorList.Back_Ground);
       }
       public void closeClick()
       {
    	   pc.ClosePanelRight();
       }
       public void setCusor(int x, int y)
       {
           if(0 <= x && x <= 3)
    	   {
    		   this.setCursor(cursorResize);
    	   }
           else
           {
        	   this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
           }
       }
       public void setSize(int x)
       {
    	   if(x - 2 <= 0 || x + 2 >= 0)
    	   this.setBounds(this.getBounds().x + x, this.getBounds().y, (this.getSize().width - x) > this.getMaximumSize().width ? this.getMaximumSize().width 
    	   : ((this.getSize().width - x) >= this.getMinimumSize().width ? (this.getSize().width - x) : this.getMinimumSize().width), this.getSize().height);
    	   pc.setBoundsObj(pc.getSize().width, pc.getSize().height, pc.getPanelContentLeft().getSize().width, pc.getPanelContentLeft().getSize().height, this.getSize().width);
       }
       public void setDefaultCursor()
       {
    	   this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
       }
       public Cursor cursorResize()
       {
    	   return cursorResize;
       }
}
