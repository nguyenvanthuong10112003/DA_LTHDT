package view.content.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import controller.mouse;
import libary.ButtonEditor;
import libary.ButtonRenderer;
import libary.ColorList;
import libary.FONT;
import libary.LabelEditor;
import libary.LabelRenderer;
import model.Element;
import model.Folder;
import model.User;
import view.content.left.ScrollPaneTree;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class PanelContentCenter extends JScrollPane{
	 private JPanel nd;
	 private int width, height, space;
	 private ScrollPaneTree scroll; 
	 private mouse mouselisten;
	 private String folder = ".//imageIcon//folder-icon24.png";
	 private static final long serialVersionUID = 1L;
	 private JTable table;
	 private JLabel myLabel = new JLabel("waiting");
	 private int Height1 = 25;
	 private int Height2 = 40;
	 private int Height3 = 50;
	 private ImageIcon folderIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(".//imageIcon////folder-icon24.png")));
     private LinkedList <ImageIcon>icon = new LinkedList<ImageIcon>();
     private Folder root;
     private LinkedList <Element> nows;
     private Font font16 = new Font("Arial", Font.PLAIN, 16);
     public PanelContentCenter(Folder root)
     {
    	 super();
    	 try {
	    	 this.init();
	    	 this.root = root;
	    	 this.addMouseMotionListener(mouselisten);
	    	 String[] columnNames = {"", "Name", "Date", "Date Modifield", "Size"};
	         Object[][] data = {{folderIcon, "About", "", "", ""}, {folderIcon, "Add", "", "", ""}, {folderIcon, "Copy", "", "", ""}, {folderIcon, "Copy", "", "", ""}};
	         DefaultTableModel model = new DefaultTableModel(data, columnNames);
	         table = new JTable(model) {
	             private static final long serialVersionUID = 1L;                                                  
	             @Override
	             public Class getColumnClass(int column) {
	                 return getValueAt(0, column).getClass();
	             }
	
	             /*@Override
	             public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	                 Component comp = super.prepareRenderer(renderer, row, column);
	                 return comp;
	             }*/
	         };
	         Edit();
	         System.out.println("Tải thành công Content ở giữa");
    	 }
    	 catch (Exception e) {
			// TODO: handle exception
    		 System.out.println("Error Content ở giữa");
		}

     }
     public void Edit()
     {
    	 this.setColor();
         this.setBackground(ColorList.Back_Ground);
    	 this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	 this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	 this.setBorder(new LineBorder(Color.black));
         //myLabel.setPreferredSize(new Dimension(200, Height));
         myLabel.setHorizontalAlignment(SwingConstants.CENTER);
         table.setSelectionBackground(ColorList.Hover);
         table.setSelectionForeground(ColorList.Fore_Ground);
         table.setRowHeight(Height2);
         table.setPreferredScrollableViewportSize(table.getPreferredSize());
         table.setDefaultEditor(Object.class, null);
         table.setShowHorizontalLines(false);
         table.setShowVerticalLines(false);
         table.getColumnModel().getColumn(0).setMaxWidth(50);
         table.setBackground(ColorList.Back_Ground);

     }
     public void setListIcon(String file)
     {
    	 
     }
     public boolean isCellEditable(int row, int column) {                
         return false;               
     };
     public void init()
     {
    	 mouselisten = new mouse(this);
    	 nd = new JPanel();
    	 this.addObj();
     }
     public void addObj()
     {
        this.setViewportView(table);
     }
     public void setColor()
     {
    	 this.setOpaque(true);
    	 //this.setBackground(back);
    	 
    	 nd.setOpaque(true);
    	 ///nd.setBackground(back);
     }
}