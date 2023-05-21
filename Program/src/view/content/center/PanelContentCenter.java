package view.content.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.File;
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
	 private String folder = "\\Icon\\content\\center\\folder\\folderIcon16px.png";
	 private static final long serialVersionUID = 1L;
	 private JTable table;
	 private JLabel myLabel = new JLabel("waiting");
	 private DefaultTableModel tableModel;
	 private int Height1 = 25;
	 private int Height2 = 40;
	 private int Height3 = 50;
	 private ImageIcon folderIcon;
     private LinkedList <ImageIcon>icon = new LinkedList<ImageIcon>();
     private String[] columnNames;
     private Object[][] data;
     private Folder root;
     private Element nows;
     private Font font16 = new Font("Arial", Font.PLAIN, 16);
     private int row;
     private int col;
     private String px = "16px";
     private String duoi = ".png";
     private String url;
     public PanelContentCenter(Folder root, Element now, String url)
     {
    	 super();
    	 try {
    		 if(now != null) {
    			 this.nows = now;
    			 row = this.nows.getChildrents().size();
    		 }
    		 else
    		 {
    			 this.nows = root;
    			 row = this.root.getChildrents().size();
    		 }
    		 this.url = url;
    		 folderIcon = new ImageIcon(url + folder);
    		 this.setColumn();
    		 this.setData();
	    	 this.init();
	    	 this.root = root;
	    	 this.addMouseMotionListener(mouselisten);
	    	 //Object[][] data = {{folderIcon, "About", "", "", ""}, {folderIcon, "Add", "", "", ""}, {folderIcon, "Copy", "", "", ""}, {folderIcon, "Copy", "", "", ""}};
	        
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
     public void setColumn()
     {
    	 columnNames = new String[] {"", "Name", "Date modified", "Type", "Size"};
    	 col = 5;
    	 
     }
     public void setData()
     {
    	 Folder folder1 = new Folder(1, "folder1");
    	 Folder folder2 = new Folder(2, "folder2");
    	 Folder folder3 = new Folder(3, "folder3");
    	 Folder folder4 = new Folder(4, "folder4");
    	 Folder folder5 = new Folder(5, "folder5");
    	 Folder folder6 = new Folder(6, "folder6");
    	 Folder folder7 = new Folder(7, "folder7");
    	 LinkedList <Element> list = new LinkedList <Element>();
    	 list.add(folder1);
    	 list.add(folder2);
    	 list.add(folder3);
    	 list.add(folder4);
    	 list.add(folder5);
    	 list.add(folder6);
    	 list.add(folder7);
    	 list.add(new File(1, "File1"));
    	 nows = new Folder(8, "Folder", list);
    	 row = 8;
    	 data = new Object[9][5];
    	 if(nows == null)
    	 {	 
    	 }
    	 else
    	 {
    		 int i = 0;
    		 for(Element el : nows.getChildrents())
    		 {
    			 ImageIcon icon = new ImageIcon(url + "\\Icon\\content\\center\\folder\\" + el.getIcon() + this.px + this.duoi);
    			 data[i][0] = icon;
    			 data[i][1] = el.getName();
    			 data[i][3] = "";
    			 data[i][4] = el.getSize();
	    		 data[i][2] = el.getDateModified().toLocaleString();
    			 i++;
    		 }
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
         this.myLabel.setHorizontalAlignment(SwingConstants.CENTER);
         this.table.setSelectionBackground(ColorList.Hover);
         this.table.setSelectionForeground(ColorList.Fore_Ground);
         this.table.setRowHeight(Height2);
         this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
         this.table.setDefaultEditor(Object.class, null);
         this.table.setShowHorizontalLines(false);
         this.table.setShowVerticalLines(false);
         this.table.getColumnModel().getColumn(0).setMaxWidth(50);
         this.table.setBackground(ColorList.Back_Ground);

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