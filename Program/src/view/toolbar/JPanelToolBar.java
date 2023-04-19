package view.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class JPanelToolBar extends JPanel{
       private ScreenToolBar toolbar;
       private JPanelFunction function;
       private Font font;
       private Color black = Color.black;
       private Color white = Color.white;
       public JPanelToolBar(Font font)
       {
    	   super();
    	   this.font = font;
    	   toolbar = new ScreenToolBar(font);
    	   function = new JPanelFunction(font);
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
       }
}
