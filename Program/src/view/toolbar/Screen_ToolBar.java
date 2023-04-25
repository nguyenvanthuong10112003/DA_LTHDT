package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import libary.ColorList;
import libary.FONT;

public class Screen_ToolBar extends JPanel{
       private Panel_Functions toolbar;
       private Panel_Navigation function;
       private Color black = Color.black;
       private Color white = Color.white;
       public Screen_ToolBar() {
    	   super();
    	   toolbar = new Panel_Functions();
    	   function = new Panel_Navigation();
    	   setColor(white, black);
    	   this.setLayout(new BorderLayout());
    	   this.add(toolbar, BorderLayout.CENTER);
    	   this.add(function, BorderLayout.SOUTH); 
       }
       private void setColor(Color back, Color font)
       {
    	   function.setOpaque(true);
    	   function.setBackground(back);
    	   function.setForeground(font);
    	   
    	   toolbar.setForeground(font);
     	   this.setOpaque(true);
     	   this.setBackground(ColorList.Back_Ground);
       }
}
