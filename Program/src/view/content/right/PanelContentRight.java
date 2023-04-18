package view.content.right;

import java.awt.Color;
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
import libary.JButtonIcon;
import libary.JLabelIcon;
import view.content.PanelContent;

public class PanelContentRight extends JPanel{
	   private PanelContent pc;
	   private JLabel close;
	   private String iconClose16 = "close16.png";
	   private String iconClose24 = "close24.png";
	   private mouse mouselisten;
       public PanelContentRight(PanelContent pc)
       {
    	   super();
    	   this.pc = pc;
    	   mouselisten = new mouse(this);
    	   this.addMouseMotionListener(mouselisten);
    	   this.setBorder(new LineBorder(Color.black));
    	   this.setLayout(null);
    	   close = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(iconClose16))));
    	   close.addMouseMotionListener(mouselisten);
    	   close.addMouseListener(mouselisten);
    	   close.setBorder(new EmptyBorder(0,0,0,0));
    	   close.setOpaque(true);
    	   close.setBackground(Color.WHITE);
    	   this.add(close);
    	   close.setSize(20,20);
    	   close.setBounds(1, 1, close.getSize().width, close.getSize().height);
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
    	   close.setBackground(new Color(102,153,204));
       }
       public void exitClose()
       {
    	   close.setBackground(Color.white);
       }
       public void closeClick()
       {
    	   pc.ClosePanelRight();
       }
       
}
