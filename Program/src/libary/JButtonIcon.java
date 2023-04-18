package libary;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonIcon extends JButton{
	   private String icon;
       public JButtonIcon()
       {
    	   super();
    	   icon = "";
       }
       public JButtonIcon(ImageIcon iconimage)
       {
    	   super();
    	   if(iconimage != null)
    		   this.setIcon(iconimage);
       }
}
