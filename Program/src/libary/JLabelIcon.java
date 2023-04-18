package libary;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JLabelIcon extends JLabel{
	   private String icon;
       public JLabelIcon()
       {
    	   super();
    	   icon = "";
       }
       public JLabelIcon(ImageIcon iconimage)
       {
    		 super(iconimage, JLabel.CENTER);
       }
}