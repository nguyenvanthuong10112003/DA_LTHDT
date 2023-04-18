package view.content.center;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.LineBorder;

import controller.mouse;
import view.content.left.ScrollPaneTree;

public class PanelContentCenter extends JPanel{

	//private static final long serialVersionUID = 1L;
	 private JPanel nd;
	 private int width, height, space;
	 private ScrollPaneTree scroll; 
	 private Color black = Color.black;
	 private Color white = Color.white;
	 private mouse mouselisten;
     public PanelContentCenter()
     {
    	 super();
    	 mouselisten = new mouse(this);
    	 nd = new JPanel();
    	 this.setColor(black, black);
    	 //this.init();
    	 //this.addObj();
    	 //this.updateBound();
    	 //this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	 //this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	 this.setBorder(new LineBorder(Color.black));
         //this.setLayout(new BorderLayout());
    	 //this.add(nd, BorderLayout.EAST);
    	 //this.add(new JPanel(), BorderLayout.WEST);
    	 this.addMouseMotionListener(mouselisten);
     }
     public void init()
     {
    	 
     }
     public void setSizeJpanel()
     {
    	 nd.setSize(this.getSize().width - 20, this.getSize().height - 20);
     }
     public void addObj()
     {
    	 
     }
     public void setColor(Color back, Color font)
     {
    	 this.setOpaque(true);
    	 this.setBackground(back);
    	 
    	 nd.setOpaque(true);
    	 ///nd.setBackground(back);
     }
}
