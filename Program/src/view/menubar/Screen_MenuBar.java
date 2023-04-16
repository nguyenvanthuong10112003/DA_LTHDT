package view.menubar;

import java.awt.Color;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class Screen_MenuBar extends JMenuBar{
	 private JMenu File;
	 private JMenu Edit;
	 private JMenu View;
	 private JMenuItem item1_1;
	 private JMenuItem item1_2;
	 private JMenu item3_1;
	 private JRadioButtonMenuItem item3_1_1;
	 private JRadioButtonMenuItem item3_1_2;
	 private JRadioButtonMenuItem item3_1_3;
	 private JMenu item3_2;
     private ButtonGroup groupbutton;
	 
	 private Color backcl;
	 private Font font;
     public Screen_MenuBar(Font font, Color color)
     {
    	 super();
    	 this.font = font;
    	 this.backcl = color;
    	 init();
    	 setIconItem();
    	 setText();
    	 addObj();
         setBack();
     }
     private void setBack()
     {
    	 //panel_file.setBackground(backcl);
         this.setBackground(backcl);
         item1_1.setBackground(backcl);
         item1_2.setBackground(backcl);
         item3_1.setOpaque(true);
         item3_1.setBackground(backcl);
         item3_2.setOpaque(true);
         item3_2.setBackground(backcl);
         item3_1_1.setBackground(backcl);
         item3_1_2.setBackground(backcl);
         item3_1_3.setBackground(backcl);
     }
     private void init()
     {
    	 File = new JMenu();
    	 Edit = new JMenu();
    	 View = new JMenu();
    	 item1_1 = new JMenuItem();
    	 item1_2 = new JMenuItem();
    	 item3_1 = new JMenu();
    	 item3_1_1 = new JRadioButtonMenuItem();
    	 item3_1_2 = new JRadioButtonMenuItem();
    	 item3_1_3 = new JRadioButtonMenuItem();
    	 item3_2 = new JMenu();
         groupbutton = new ButtonGroup();
     }
     private void setText()
     {
    	 File.setText("File");
    	 File.setFont(font);
    	 Edit.setText("Edit");
    	 Edit.setFont(font);
    	 View.setText("View");
    	 View.setFont(font);
    	 item1_1.setText("Mở trong cửa sổ mới");
    	 item1_1.setFont(font);
    	 item1_2.setText("Đóng");
    	 item1_2.setFont(font);
    	 item3_1.setText("Chế độ xem");
    	 item3_1.setFont(font);
    	 item3_1_1.setText("Chi tiết");
    	 item3_1_1.setFont(font);
    	 item3_1_2.setText("Biểu tượng");
    	 item3_1_2.setFont(font);
    	 item3_1_3.setText("Biểu tượng lớn");
    	 item3_1_3.setFont(font);
    	 item3_2.setText("Sắp xếp theo");
    	 item3_2.setFont(font);
     }
     private void addObj()
     {
    	 item3_1_1.setSelected(true);	 
    	 groupbutton.add(item3_1_1);
    	 groupbutton.add(item3_1_2);
    	 groupbutton.add(item3_1_3);

    	 item3_1.add(item3_1_1);
    	 item3_1.add(item3_1_2);
    	 item3_1.add(item3_1_3);
    	 
    	 File.add(item1_1);
    	 File.addSeparator();
    	 File.add(item1_2);
    	 View.add(item3_1);
    	 View.addSeparator();
    	 View.add(item3_2);
    	 this.add(File);
    	 this.add(Edit);
    	 this.add(View);
     }
     private void setIconItem()
     {
    	 try {
	    	 item1_1.setIcon((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("new window1_16px.png")))));
	         item1_1.setIconTextGap(7);
	         item1_2.setIcon((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("close_16px.png")))));
	         item1_2.setIconTextGap(7);
	         item3_1.setIconTextGap(7);
	         item3_1_1.setIcon((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("che do xem chi tiet1.png")))));
	         item3_1_1.setIconTextGap(7);
	         item3_1_2.setIcon((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("bieu tuong.png")))));
	         item3_1_2.setIconTextGap(7);
	         item3_1_3.setIcon((new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("bieu tuong lon.png")))));
	         item3_1_3.setIconTextGap(7);
	         item3_2.setIconTextGap(7);
    	 }
    	 catch(Exception e) {}
     }
}
