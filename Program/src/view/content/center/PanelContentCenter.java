package view.content.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import controller.mouse;
import libary.ButtonEditor;
import libary.ButtonRenderer;
import libary.LabelEditor;
import libary.LabelRenderer;
import view.content.left.ScrollPaneTree;

public class PanelContentCenter extends JScrollPane{

	//private static final long serialVersionUID = 1L;
	 private JTable table;
	 private JPanel nd;
	 private int width, height, space;
	 private ScrollPaneTree scroll; 
	 private mouse mouselisten;
	 private String folder = "folder-icon.png";
     public PanelContentCenter()
     {
    	 super();
    	 mouselisten = new mouse(this);
    	 nd = new JPanel();
    	 this.setColor();
    	 //this.init();

    	 //this.updateBound();
    	 this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	 this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	 this.setBorder(new LineBorder(Color.black));
         //this.setLayout(new BorderLayout());
    	 //this.add(nd, BorderLayout.EAST);
    	 //this.add(new JPanel(), BorderLayout.WEST);
    	 this.addObj();
    	 this.addMouseMotionListener(mouselisten);
     }
     public void init()
     {
    	 
     }
     public void addObj()
     {
    	 DefaultTableModel dm = new DefaultTableModel();
         dm.setDataVector(new Object[][]{{"folder 1", "foo"},
                     {"folder 2", "bar"}}, new Object[]{"Name", "Type"});

         JTable table = new JTable(dm);
         table.getColumn("Name").setCellRenderer(new LabelRenderer(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(folder))), JLabel.LEFT));
         table.getColumn("Name").setCellEditor(new LabelEditor(new JCheckBox(), new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(folder))), JLabel.LEFT));


         JScrollPane scroll = new JScrollPane(table);

         table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

         table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..
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