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
import model.Element;
import model.Folder;
import model.User;
import view.content.PanelContent;
import view.content.left.ScrollPaneTree;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class PanelContentCenter extends JScrollPane {
	private PanelContent pct;
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
	private JPopupMenu jPopupMenu;
	private JMenuItem open;
	private LinkedList<ImageIcon> icon = new LinkedList<ImageIcon>();
	private String[] columnNames;
	private Object[][] data;
	private Element root;
	private LinkedList<Element> nows;
	private DefaultTableModel model;
	private Font font16 = new Font("Arial", Font.PLAIN, 16);
	private int row;
	private int col;
	private String px = "16px";
	private String duoi = ".png";
	private String url;
	private String urlIconFolder = "\\Icon\\content\\center\\folder\\";
	private String urlIconFile = "\\Icon\\content\\center\\file\\";
	private TableEditer edit = new TableEditer(false);

	public PanelContentCenter(PanelContent pct, Element root, String url) {
		super();
		try {
			this.pct = pct;
			this.url = url;
			this.root = root;
			nows = new LinkedList<Element>();
			nows.add(root);
			folderIcon = new ImageIcon(url + folder);
			this.setColumn();
			this.setData();
			this.init();
			this.addEvent();
			this.setTable();
			this.Edit();
			this.setViewportView(table);
			this.add(jPopupMenu);
			table.add(jPopupMenu);
			jPopupMenu.add(open);
			System.out.println("Tải thành công Content ở giữa");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Content ở giữa");
		}

	}

	public void setTable() {
		model.setDataVector(data, columnNames);
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
	}

	public void setColumn() {
		columnNames = new String[] { "", "Name", "Date modified", "Type", "Size" };
		col = 5;
	}

	private void addEvent() {
		this.addMouseMotionListener(mouselisten);
		this.addMouseListener(mouselisten);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.isPopupTrigger()) {
					if (table.getSelectedRow() < 0)
						open.setEnabled(false);
					else
						open.setEnabled(true);
					jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
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
				if(table.getSelectedRow() >= 0)
				{
			        pct.SELECTtable(nows.get(table.getSelectedRow()));
				}
			}
		});
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	            for(Element el : nows)
	            	if(el.getClass().equals(Folder.class))
	            		if(el.getName().equals(data[table.getSelectedRow()][1])) {
	            			nows = el.getChildrents();
	            			break;
	            		}
	            setData();
                setTable();
                Edit();
                pct.noSelected();
			}
		});
	}

	public void setData() {
		data = new Object[nows != null ? nows.size() : 0][5];
		if (nows == null) {
		} else {
			int i = 0;
			for (Element el : nows) {
				ImageIcon icon = new ImageIcon(
						url + (el.getClass().equals(Folder.class) == true ? urlIconFolder : urlIconFile) + el.getIcon()
								+ this.px + this.duoi);
				data[i][0] = icon;
				data[i][1] = el.getName();
				data[i][2] = el.getTime(el.getClass().equals(Folder.class) == true ? el.getDateCreate() : el.getDateModified());
				data[i][3] = el.getExName();
				data[i][4] = el.getClass().equals(Folder.class) == true ? ""
						: ((Double) el.getSize()).intValue() + "kb";
				i++;
			}
		}
	}

	public void Edit() {
		this.setColor();
		this.setBackground(ColorList.Back_Ground);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.setBorder(new LineBorder(Color.black));
		this.myLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.table.setSelectionBackground(ColorList.Hover);
		this.table.setSelectionForeground(ColorList.Fore_Ground);
		this.table.setRowHeight(Height2);
		this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
		this.table.setShowHorizontalLines(false);
		this.table.setShowVerticalLines(false);
		this.table.getColumnModel().getColumn(0).setMaxWidth(50);
		this.table.setBackground(ColorList.Back_Ground);
	}

	public void setListIcon(String file) {

	}

	public boolean isCellEditable(int row, int column) {
		return false;
	};

	public void init() {
		mouselisten = new mouse(this);
		this.addObj();
		this.jPopupMenu = new JPopupMenu();
		this.open = new JMenuItem("Mở");
		this.model = new DefaultTableModel();
		this.table = new JTable(){
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				Component compent = super.prepareRenderer(renderer, row, column);
				((JLabel) compent).setBorder(new EmptyBorder(0, 2, 0, 0));
				return compent;
			}
		};
	}

	public void addObj() {
	}

	public void setColor() {
		this.setOpaque(true);
		// this.setBackground(back);

		/// nd.setBackground(back);
	}

	public void Rename() {
		if (table.getSelectedRow() >= 0) {
			Component text = edit.getTableCellEditorComponent(table, table.getValueAt(table.getSelectedRow(), 1), true,
					table.getSelectedRow(), 1);
			if (text.getClass().equals(JTextField.class)) {
				((JTextField) text).setEditable(true);
				((JTextField) text).setBorder(new LineBorder(ColorList.Fore_Ground, 1));
				((JTextField) text).setBackground(ColorList.Back_Ground);
				((JTextField) text).setForeground(Color.black);
				((JTextField) text).setSelectedTextColor(Color.WHITE);
				((JTextField) text).setSelectionColor(Color.BLUE);
			}
		}
	}

	public void newRow(Boolean folder)
	{
		if(folder)
		{
			Object ob[] = {new ImageIcon(
					url + urlIconFolder + (new Folder(-1)).getIcon() + this.px + this.duoi) 
					, "Thư mục mới", 
					((Date)java.util.Calendar.getInstance().getTime()).toLocaleString()
					, "", ""};
			model.addRow(ob);
		}
		else
		{
			Object ob[] = {new ImageIcon(
					url + urlIconFile + (new File(-1)).getIcon() + this.px + this.duoi)
					, "Tệp mới",
					((Date)java.util.Calendar.getInstance().getTime()).toLocaleString()
					, "", ""};
			model.addRow(ob);
		}
		table.clearSelection();
		table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
	}
	public JTable getTable() {
		return table;
	}

	public JPopupMenu getPopupMenu() {
		return jPopupMenu;
	}

	public void NoSelected() {
		open.setEnabled(false);
	}

	public void Selected() {
		open.setEnabled(true);
	}
	
	public PanelContent getPanelContent()
	{
		return pct;
	}
}