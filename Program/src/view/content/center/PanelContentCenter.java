package view.content.center;

import model.File;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import controller.mouse;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
     private String urlIconFolder = "\\Icon\\content\\center\\folder\\";
     private String urlIconFile = "\\Icon\\content\\center\\file\\";
     private Boolean onclick = false;
     public PanelContentCenter(Folder root, Element now, String url)
     {
    	 super();
    	 try {
    		 JPanel panel = new JPanel();
    		 if(now != null) {
    			 this.nows = now;
    		 }
    		 else
    		 {
    			 this.nows = root;
    		 }
    		 this.url = url;
    		 folderIcon = new ImageIcon(url + folder);
    		 this.setColumn();
    		 this.setData();
	    	 this.init();
	    	 this.root = root;
	    	 this.addMouseMotionListener(mouselisten);
	    	 DefaultTableModel model = new DefaultTableModel(data, columnNames);
	         table = new JTable(model) {
		             private static final long serialVersionUID = 1L;                                                  
		             @Override
		             public Class getColumnClass(int column) {
		                 return getValueAt(0, column).getClass();
		             }
		             @Override
		             public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		                 // TODO Auto-generated method stub
		            	 Component compent = super.prepareRenderer(renderer, row, column);
		            	 ((JLabel)compent).setBorder(new EmptyBorder(0, 2, 0, 0));
		                 return compent;
		             }  
		     };
		     onclick = false;
		     TableEditer edit = new TableEditer(onclick);
		     table.setDefaultEditor(Object.class, null);	
		     table.getColumn("Name").setCellEditor(edit);
		     table.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				    try {
						Robot r = new Robot();
						switch(table.getSelectedColumn())
						{
						    case 0: {
						    	r.mouseMove(e.getXOnScreen() + table.getColumn(table.getColumnName(0)).getWidth(), e.getYOnScreen()); 
						    }
						    break;
						    case 2: {
						    	r.mouseMove(e.getXOnScreen() - table.getColumn(table.getColumnName(2)).getWidth(), e.getYOnScreen()); 
						    }
						    break;
						    case 3: {
						    	r.mouseMove(e.getXOnScreen() - table.getColumn(table.getColumnName(2)).getWidth() 
						    			- table.getColumn(table.getColumnName(3)).getWidth(), e.getYOnScreen());
						    }
						    break;
						    case 4: {
						    	r.mouseMove(e.getXOnScreen() - table.getColumn(table.getColumnName(2)).getWidth() 
						    			- table.getColumn(table.getColumnName(3)).getWidth() 
						    			- table.getColumn(table.getColumnName(4)).getWidth(), e.getYOnScreen());
						    }
						}
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						r.mouseMove(e.getXOnScreen(), e.getYOnScreen());
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	         Edit();
	         JButton button = new JButton("click");
	         button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(table.getSelectedRow() >= 0) {
					 Component text = edit.getTableCellEditorComponent(table, table.getValueAt(table.getSelectedRow(), 1), true, table.getSelectedRow(), 1);
				     if(text.getClass().equals(JTextField.class))
				     {
				    	 ((JTextField)text).setEditable(true);
				    	 ((JTextField)text).setBorder(new LineBorder(ColorList.Fore_Ground, 1));
				    	 ((JTextField)text).setBackground(ColorList.Back_Ground);
				    	 ((JTextField)text).setForeground(Color.black);
				    	 ((JTextField)text).setSelectedTextColor(Color.WHITE);
				    	 ((JTextField)text).setSelectionColor(Color.BLUE);
				     }
					}
				}
			});
	         panel.add(button);
	         panel.add(table);
	         this.setViewportView(panel);
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
    	 Folder folder1 = new Folder(1,"folder1");
    	 Folder folder2 = new Folder(2,"folder2");
    	 Folder folder3 = new Folder(3,"folder3");
    	 Folder folder4 = new Folder(4,"folder4");
    	 Folder folder5 = new Folder(5,"folder5");
    	 Folder folder6 = new Folder(6,"folder6");
    	 Folder folder7 = new Folder(7,"folder7");
    	 LinkedList <Element> list = new LinkedList <Element>();
    	 list.add(folder1);
    	 list.add(folder2);
    	 list.add(folder3);
    	 list.add(folder4);
    	 list.add(folder5);
    	 list.add(folder6);
    	 list.add(folder7);
    	 list.add(new File(8,"File1", "txt"));
    	 nows = new Folder(9,"Folder", list);
    	 row = 8;
    	 data = new Object[8][5];
    	 if(nows == null)
    	 {	 
    	 }
    	 else
    	 {
    		 int i = 0;
    		 for(Element el : nows.getChildrents())
    		 {
    			 ImageIcon icon = new ImageIcon(url + (el.getClass().equals(Folder.class) == true
    				? urlIconFolder : urlIconFile) + el.getIcon() + this.px + this.duoi);
    			 data[i][0] = icon;
    			 data[i][1] = el.getName();
	    		 data[i][2] = (el.getClass().equals(Folder.class) == true ? el.getDateCreate() : el.getDateModified()).toLocaleString();
    			 data[i][3] = el.getExName();
    			 data[i][4] = el.getClass().equals(Folder.class) == true ? "" : ((Double)el.getSize()).intValue() + "kb";
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
         //
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
     }
     public void setColor()
     {
    	 this.setOpaque(true);
    	 //this.setBackground(back);
    	 
    	 nd.setOpaque(true);
    	 ///nd.setBackground(back);
     }
}