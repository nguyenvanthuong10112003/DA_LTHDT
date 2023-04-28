package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import view.toolbar.Panel_Functions;
import javax.swing.JPanel;

import libary.ColorList;
import libary.FONT;

public class Screen_ToolBar extends JPanel{
       private Panel_Functions function;
       private Panel_Navigation navigation;
       public Screen_ToolBar() {
    	   super();
    	   function = new Panel_Functions();
    	   navigation = new Panel_Navigation();
    	   setColor();
    	   this.setLayout(new BorderLayout());
    	   this.add(function, BorderLayout.CENTER);
    	   this.add(navigation, BorderLayout.SOUTH); 
    	   function.setVisible(false);
       }
       private void setColor()
       {
    	   navigation.setOpaque(true);
    	   navigation.setBackground(ColorList.Back_Ground);
    	   navigation.setForeground(ColorList.Fore_Ground);
    	   
    	   function.setForeground(ColorList.Fore_Ground);
     	   this.setOpaque(true);
     	   this.setBackground(ColorList.Back_Ground);
       }
       public void Show_Hide_Function()
       {
    	   if(function.isVisible())
    	   {
    		   function.setVisible(false);
    	   }
    	   else
    		   function.setVisible(true);
       }
}
