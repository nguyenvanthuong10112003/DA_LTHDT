package view.content.center;

import model.File;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import controller.mouse;
import define.ColorList;
import model.Element;
import model.Folder;
import libary.ConnectSQL;
import view.content.PanelContent;
import view.menubar.Screen_MenuBar;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import controller.*;

public class PanelContentCenter extends JScrollPane {
	private PanelContent pct;
	private mouse mouselisten;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel myLabel = new JLabel("waiting");
	private int Height2 = 40;
	private JPopupMenu jPopupMenu;
	private JMenuItem open;
	private String[] columnNames;
	private Object[][] data;
	private Element root;
	private Element nows;
	private Element cuoi;
	private Element dau;
	private LinkedList<Element> copy;
	private LinkedList<Element> cut;
	private int countCut;
	private DefaultTableModel model;
	private String px = "24px";
	private String duoi = ".png";
	private String urlIconFolder = "\\Icon\\content\\center\\folder\\";
	private String urlIconFile = "\\Icon\\content\\center\\file\\";
	private LinkedList<Integer> selectCut;
	private action ActionListen;
	private String select = null;
	
	public PanelContentCenter(PanelContent pct, Element root) {
		super();
		try {
			this.pct = pct;
			this.root = root;
			this.dau = null;
			this.cuoi = null;
			this.nows = null;
			this.copy = null;
			this.cut = null;
			this.selectCut = null;
			this.setColumn();
			this.setData();
			this.init();
			this.addEvent();
			this.setTable();
			this.Edit();
			this.addObj();
			System.out.println("Upload success content center");
		} catch (Exception e) {
			System.out.println("Error Content center");
		}
	}
	
	public void init() {
		mouselisten = new mouse(this);
		this.jPopupMenu = new JPopupMenu();
		this.open = new JMenuItem("Mở");
		this.model = new DefaultTableModel();
		this.table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component compent = super.prepareRenderer(renderer, row, column);
				((JLabel) compent).setBorder(new EmptyBorder(0, 2, 0, 0));
				((JLabel) compent).setOpaque(true);
				((JLabel) compent).setForeground(ColorList.Fore_Ground);
				if (selectCut != null) {
					if (selectCut.size() > 0) {
						for (int e : selectCut)
							if (e == row) {
								((JLabel) compent).setForeground(new Color(0, 0, 0, 64));
							}
					}
				}
				return compent;
			}
		};
		ActionListen = new action(this);
	}

	public void addObj() {
		this.setViewportView(table);
		this.add(jPopupMenu);
		this.table.add(jPopupMenu);
		this.jPopupMenu.add(open);
	}
	
	public void Edit() {
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
		this.setBackground(ColorList.Back_Ground);
	}
	
	public void setTable() {
		model.setDataVector(data, columnNames);
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		selectCut = null;
	}
	
	public void setColumn() {
		columnNames = new String[] { "", "Name", "Date modified", "Type", "Size" };
	}
	
	public void setData() {
		data = new Object[nows != null ? nows.getChildrents().size() : 1][5];
		if (nows == null) {
			ImageIcon icon = new ImageIcon(define.URL.url + urlIconFolder + root.getIcon() + this.px + this.duoi);
			data[0][0] = icon;
			data[0][1] = root.getName();
			data[0][2] = root.getTime(root.getDateCreate());
			data[0][3] = root.getExName();
			data[0][4] = "";
		} else if (nows.getChildrents().size() > 0) {
			int i = 0;
			for (Element el : nows.getChildrents()) {
				ImageIcon icon = new ImageIcon(
						define.URL.url + (el.getClass().equals(Folder.class) == true ? urlIconFolder : urlIconFile)
								+ el.getIcon() + this.px + this.duoi);
				data[i][0] = icon;
				data[i][1] = el.getName();
				data[i][2] = el.getTime(
						el.getClass().equals(Folder.class) == true ? el.getDateCreate() : el.getDateModified());
				data[i][3] = el.getExName();
				data[i][4] = el.getClass().equals(Folder.class) == true ? ""
						: ((Double) el.getSize()).intValue() + "kb";
				i++;
			}
		}
		sort();
	}

	public void sort() {
		if (nows == null)
			return;
		int x = Screen_MenuBar.getSort();
		if (x == 0) {
			while (true) {
				int dem = 0;
				for (int i = 0; i < nows.getChildrents().size() - 1; i++) {
					if (!sosanh((String) data[i][1], (String) data[i + 1][1])) {
						Object temp[] = { data[i][0], data[i][1], data[i][2], data[i][3], data[i][4] };
						data[i][0] = data[i + 1][0];
						data[i][1] = data[i + 1][1];
						data[i][2] = data[i + 1][2];
						data[i][3] = data[i + 1][3];
						data[i][4] = data[i + 1][4];
						data[i + 1][0] = temp[0];
						data[i + 1][1] = temp[1];
						data[i + 1][2] = temp[2];
						data[i + 1][3] = temp[3];
						data[i + 1][4] = temp[4];
						dem++;
					}
				}
				if (dem == 0)
					return;
			}
		} else if (x == 2) {
			while (true) {
				int dem = 0;
				for (int i = 0; i < nows.getChildrents().size() - 1; i++) {
					if (!sosanhTime((String) data[i][2], (String) data[i + 1][2])) {
						Object temp[] = { data[i][0], data[i][1], data[i][2], data[i][3], data[i][4] };
						data[i][0] = data[i + 1][0];
						data[i][1] = data[i + 1][1];
						data[i][2] = data[i + 1][2];
						data[i][3] = data[i + 1][3];
						data[i][4] = data[i + 1][4];
						data[i + 1][0] = temp[0];
						data[i + 1][1] = temp[1];
						data[i + 1][2] = temp[2];
						data[i + 1][3] = temp[3];
						data[i + 1][4] = temp[4];
						dem++;
					}
				}
				if (dem == 0)
					return;
			}
		}
	}
	
	private Boolean sosanh(String a, String b) {
		if (a.length() == b.length()) {
			for (int i = 0; i < a.length(); i++) {
				if (a.charAt(i) < b.charAt(i)) {
					return true;
				} else if (a.charAt(i) > b.charAt(i)) {
					return false;
				}
			}
		} else if (a.length() > b.length()) {
			for (int i = 0; i < b.length(); i++)
				if (a.charAt(i) < b.charAt(i)) {
					return true;
				} else if (a.charAt(i) > b.charAt(i)) {
					return false;
				}
			return false;
		} else if (a.length() < b.length()) {
			for (int z = 0; z < a.length(); z++)
				if (a.charAt(z) < b.charAt(z)) {
					return true;
				} else if (a.charAt(z) > b.charAt(z))
					return false;
		}
		return true;
	}

	private Boolean sosanhTime(String a, String b) {
		int da = Integer.parseInt(a.substring(0, 2)), db = Integer.parseInt(b.substring(0, 2)),
				ma = Integer.parseInt(a.substring(3, 5)), mb = Integer.parseInt(b.substring(3, 5)),
				ya = Integer.parseInt(a.substring(6, 10)), yb = Integer.parseInt(b.substring(6, 10)),
				ha = Integer.parseInt(a.substring(11, 13)), hb = Integer.parseInt(b.substring(11, 13)),
				pa = Integer.parseInt(a.substring(14, 16)), pb = Integer.parseInt(b.substring(14, 16)),
				sa = Integer.parseInt(a.substring(17, 19)), sb = Integer.parseInt(b.substring(17, 19));
		if (ya == yb) {
			if (ma == mb) {
				if (da == db) {
					if (ha == hb) {
						if (pa == pb) {
							if(sa == sb)
							{
								return true;
							} else if (sa < sb)
								return false;
						} else if (pa < pb)
							return false;
					} else if (ha < hb)
						return false;
				} else if (da < db)
					return false;
			} else if (ma < mb)
				return false;
		} else if (ya < yb)
			return false;
		return true;
	}

	public void setNows(Element e) {
		nows = e;
		cuoi = e;
		check();
		Update();
	}
	
/**************************************************************************************************
 01*******************************************Event************************************************
 *************************************************************************************************/
	
	private void addEvent() {
		this.addMouseMotionListener(mouselisten);
		this.addMouseListener(mouselisten);
		table.addMouseListener(mouselisten);
		open.addActionListener(ActionListen);
	}
	
	public void clickedTable() {
		if (table.getSelectedRow() >= 0 && table.getSelectedRowCount() == 1) {
			if (nows == null) {
				pct.SELECTtable(root);
			}
			else {
				for (Element child : nows.getChildrents()) {
					if (child.getName().equals(data[table.getSelectedRow()][1])) {
						pct.SELECTtable(child);
						break;
					}
				}
			}
			pct.setFunSelectedTablie(true);
			if (nows == null)
				pct.getScreen().FunEnablueRoot(true);
			else {
				pct.getScreen().FunEnablueRoot(false);
			}
		}
	}

	public void clickedItemPopup(int hash) {
		if (hash == open.hashCode()) {
			Open();
		}
	}
	
	public void Open()
	{
		if (nows != null) {
			if (nows.getChildrents().size() > 0) {
				for (Element el : nows.getChildrents())
					if (el.getClass().equals(Folder.class))
						if (el.getName().equals(data[table.getSelectedRow()][1])) {
							nows = el;
							break;
						}
			}
		} else {
			nows = root;
		}
		if (cuoi == null) {
			if (nows.getParent() == cuoi)
				cuoi = nows;
		} else {
			if (nows != root) {
				if (nows.getParent() == cuoi)
					cuoi = nows;
				else if (!Folder.isChild(cuoi, nows))
					cuoi = nows;
			}
		}
		pct.getScreen().showNew(true);
		check();
		if (cut == null && copy == null)
			pct.getScreen().setEnPaste(false);
		else
			pct.getScreen().setEnPaste(true);
		pct.getScreen().FunEnablueRoot(true);
		pct.getScreen().setNowsNavi(nows);
		Update();
	}
	
	public void showPopup(Component comp, int x, int y) {
		if (table.getSelectedRow() < 0)
			open.setEnabled(false);
		else
			open.setEnabled(true);
		jPopupMenu.show(comp, x, y);
	}
	
/***************************************************************************************************
02*************************************************************************************************
**************************************************************************************************/
	
/***************************************************************************************************
03*****************************************chuc nang************************************************
**************************************************************************************************/
	
	public void check() {
		if (nows != null) {
			pct.getScreen().setBack(true);
		} else {
			pct.getScreen().setBack(false);
		}
		if (cuoi != null) {
			if (cuoi.equals(nows)) {
				pct.getScreen().setForward(false);
			} else {
				pct.getScreen().setForward(true);
			}
		} else if (cuoi == null)
			pct.getScreen().setForward(false);
	}

	public void back() {
		if (dau != null) {
			if (dau.getParent().equals(nows.getParent()))
				nows = nows.getParent();
		} else if (dau == null) {
			nows = nows.getParent();
		}
		if (nows == null) {
			pct.getScreen().showNew(false);
		} else {
			pct.getScreen().showNew(true);
		}
		pct.getScreen().setNowsNavi(nows);
		Update();
		check();
	}

	public void forward() {
		nows = (new Folder(0)).timCon(cuoi, nows);
		pct.getScreen().setNowsNavi(cuoi);
		if (nows == null) {
			pct.getScreen().showNew(false);
		} else {
			pct.getScreen().showNew(true);
		}
		Update();
		check();
	}

	public void Pin() {
		LinkedList<Element> arr = new LinkedList<Element>();
		if (nows == null)
			arr.add(root);
		else {
			for (int i = 0; i < table.getSelectedRowCount(); i++) {
				for (Element child : nows.getChildrents()) {
					if (child.getName().equals(data[table.getSelectedRows()[i]][1])
							&& child.getClass().equals(Folder.class)) {
						arr.add(child);
						break;
					}
				}
			}
		}
		try {
			pct.getPanelContentLeft().getTreeBar().addPin(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Cut() {
		cut = new LinkedList<Element>();
		selectCut = new LinkedList<Integer>();
		int arr[] = table.getSelectedRows();
		if (nows != null) {
			for (int i = 0; i < table.getSelectedRowCount(); i++) {
				for (Element child : nows.getChildrents()) {
					if (child.getName().equals(data[arr[i]][1])) {
						cut.add(child);
						break;
					}
				}
				selectCut.add(table.getSelectedRows()[i]);
			}
		}
		table.setVisible(false);
		table.setVisible(true);
		if (nows != null)
			pct.getScreen().setEnPaste(true);
		copy = null;
		countCut = 0;
	}

	public void Copy() {
		copy = new LinkedList<Element>();
		selectCut = null;
		int arr[] = table.getSelectedRows();
		if (nows != null) {
			for (int i = 0; i < table.getSelectedRowCount(); i++) {
				for (Element child : nows.getChildrents()) {
					if (child.getName().equals(data[arr[i]][1])) {
						copy.add(child);
						break;
					}
				}
			}
		} else {
			copy.add(root);
		}
		table.setVisible(false);
		table.setVisible(true);
		if (nows != null)
			pct.getScreen().setEnPaste(true);
		cut = null;
	}

	public void Paste() throws ClassNotFoundException {
		if (table.getSelectedRowCount() > 1)
			return;
		Element now = null;
		LinkedList<Element> paste = new LinkedList<Element>();
		Connection connect = null;
		Statement sta = null;
		if (pct.isLogin()) {
			try {
				connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
				if (connect != null) {
					System.out.println("Ket noi database thanh cong");
				} else {
					connect.close();
					System.out.println("Ket noi database that bai");
					return;
				}
				sta = connect.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (table.getSelectedRowCount() > 0) {
			for(int i = 0; i < nows.getChildrents().size(); i++)
				if(data[table.getSelectedRow()][1].equals(nows.getChildrents().get(i).getName()))
				{
					now = nows.getChildrents().get(i);
					break;
				}
		}
		else
			now = nows;
		if (now == null || (cut == null && copy == null))
			return;
		if (copy != null) {
			paste = copy;
		} else if (cut != null) {
			if (countCut == 0) {
				// delete 
				if (!(new Folder(0)).checkIsChild(cut, (Folder) now))
					return;
				Folder parent = (Folder) cut.get(0).getParent();
				if (parent != null) {
					for (Element e : cut) {
						if(pct.getPanelContentLeft().getTreeBar().getListquick().contains(e))
							try {
								pct.getPanelContentLeft().getTreeBar().removePin(e);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						if (sta != null) {
							try {
								e.deleteToDB(sta);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						for (int i = 0; i < parent.getChildrents().size(); i++) {
							if (parent.getChildrents().get(i).equals(e)) {
								parent.getChildrents().remove(i);
								break;
							}
						}
						e.setParent(null);
					}
				}
				countCut++;
			}
			paste = cut;
		}
		LinkedList<Element> list = new LinkedList<Element>();
		for (Element e : paste) {
			Element el;
			if (e.getClass().equals(Folder.class)) {
				el = (Folder.TaoBanSao((Folder) e, (Folder) now));
			} else {
				el = (File.TaoBanSao((File) e, (Folder) now));
			}
			for (int i = 0; i < now.getChildrents().size(); i++) {
				if (now.getChildrents().get(i).getName().equals(el.getName()) || el.getName().equals("")) {
					String str = JOptionPane.showInputDialog(
							table, "Tên File/Folder \"" + el.getName()
									+ "\" bạn muốn Paste đã tồn tại trong bản ghi này.\n " + "Hãy đặt lại tên khác.",
							"Thông báo", JOptionPane.OK_OPTION);
					el.setName(str);
					i = -1;
				}
			}
			if (sta != null) {
				try {
					if (el.getClass().equals(Folder.class))
						((Folder) el).addToDBs(sta);
					else
						((File) el).addToDB(sta);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			list.add(el);
			now.getChildrents().add(el);
		}
		if (copy != null)
			copy = list;
		else
			cut = list;
		if (!pct.isLogin())
			try {
				ghiFile();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		if(connect != null)
		{
			try {
				sta.close();
				connect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		pct.getPanelContentLeft().getTreeBar().setListQuick();
		pct.UpdateLeft();
		Update();
	}

	public void MoveTo() {

	}

	public void CopyTo() {

	}

	public void deletedRow() throws ClassNotFoundException {
		int[] sl = table.getSelectedRows();
		for (int i = table.getSelectedRowCount() - 1; i >= 0; i--) {
			for (Element e : nows.getChildrents()) {
				if (e.getName().equals(data[table.getSelectedRows()[i]][1])) {
					if(pct.getPanelContentLeft().getTreeBar().getListquick().contains(e))
						try {
							pct.getPanelContentLeft().getTreeBar().removePin(e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					if (pct.isLogin()) {
						try {
							Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
							if (connect != null) {
								System.out.println("Ket noi database thanh cong");
							} else {
								System.out.println("Ket noi database that bai");
								connect.close();
								return;
							}
							Statement sta = connect.createStatement();
							e.deleteToDB(sta);
							sta.close();
							connect.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					nows.getChildrents().remove(e);
					break;
				}
			}
		}
		if (!pct.isLogin())
			try {
				ghiFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		pct.getPanelContentLeft().getTreeBar().setListQuick();
		try {
			pct.getPanelContentLeft().getTreeBar().ghiFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pct.UpdateLeft();
		Update();
	}
	
	public void newRow(Boolean folder) throws IOException, ClassNotFoundException {
		if (nows != null) {
			String name;
			if (folder) {
				name = "Thư mục mới";
				int i = 1;
				while (((Folder) nows).checkNameChild(name)) {
					name = "Thư mục mới " + i;
					i++;
				}
				((Folder) nows).addToChilds(new Folder((new Folder(0)).getMax() + 1, name, (Folder) nows));
			} else {
				name = "Tệp mới";
				int i = 1;
				while (((Folder) nows).checkNameChild(name)) {
					name = "Tệp mới " + i;
					i++;
				}
				((Folder) nows).addToChilds(new File((new File(0)).getMax() + 1, name, "", (Folder) nows));
			}
			if (!pct.isLogin())
				ghiFile();
			else
				ghiDBWhenAddRow();
			Update();
			for (int i = 0; i < nows.getChildrents().size(); i++)
				if (data[i][1].equals(name)) {
					table.setRowSelectionInterval(i, i);
					break;
				}
			for (Element e : nows.getChildrents())
				if (e.getName().equals(name)) {
					pct.SELECTtable(e);
					break;
				}
			pct.UpdateLeft();
			pct.setFunSelectedTablie(true);
			if (nows == null)
				pct.getScreen().FunEnablueRoot(true);
			else
				pct.getScreen().FunEnablueRoot(false);
			pct.getScreen().FunEnablueRoot(false);
		}
	}

	public void selectAll() {
		table.selectAll();
		if (table.getSelectedRowCount() > 0) {
			pct.setFunSelectedTablie(true);
			if (nows == null)
				pct.getScreen().FunEnablueRoot(true);
			else
				pct.getScreen().FunEnablueRoot(false);
		}
	}
	
	public void NoSelected() {
		open.setEnabled(false);
		table.clearSelection();
		pct.noSelected();
		pct.getScreen().FunEnablueRoot(true);
	}

/**********************************************************
 04********************Chuc nang end***********************
 *********************************************************/
	
/**********************************************************
05*************Thao tac voi kho luu tru********************
**********************************************************/
	
	public void ghiDBWhenAddRow() throws ClassNotFoundException {
		Element e = nows.getChildrents().get(nows.getChildrents().size() - 1);

		try {
			Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
				return;
			}
			Statement statement = connect.createStatement();
			e.addToDB(statement);
			statement.close();
			connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Update();
			nows.getChildrents().remove(nows.getChildrents().size() - 1);
			pct.SELECTtable(nows.getChildrents().get(nows.getChildrents().size() - 1));
			table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
			e1.printStackTrace();
		}
	}

	public void updateDB(Element e) throws ClassNotFoundException {
		try {
			Connection connect = ConnectSQL.getJDBCConnection(define.DefineSQL.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
				return;
			}
			Statement sta = connect.createStatement();
			e.updateToDB(sta, e.getName(), e.getId());
			sta.close();
			connect.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void ghiFile() throws IOException
	{
		FileWriter out = null;
		try {
			out = new FileWriter(define.URL.url + define.URL.urlLuuTru);
			System.out.println("Mo file du lieu thanh cong");
			root.ghiFile(out);
			System.out.println("Ghi file thanh cong");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public void ghiAlls(Element e, FileWriter out) throws IOException {
		if (e == null)
			return;
		if(pct.getPanelContentLeft().getTreeBar().getListquick().contains(e))
			pct.getPanelContentLeft().getTreeBar().removePin(e);
		out.write(e.getId() + "|" + e.getName() + "|" + e.getTime(e.getDateCreate()) + "|"
				+ e.getTime(e.getDateModified()) + "|" + e.getExType() + "|"
				+ (e.getClass().equals(Folder.class) == true ? "" : e.getSize()) + "|"
				+ (e.getParent() != null ? e.getParent().getId() : "") + '\n');
		if (e.getClass().equals(Folder.class)) {
			for (Element el : e.getChildrents()) {
				ghiAlls(el, out);
			}
		}
	}
	
/**********************************************************
06*********************************************************
**********************************************************/
	
	public void setRoot(Folder root) {
		this.root = root;
		this.nows = null;
		this.Update();
	}
	
	public PanelContent getPanelContent() {
		return pct;
	}

	public JTable getTable() {
		return table;
	}

	public JPopupMenu getPopupMenu() {
		return jPopupMenu;
	}
	
	public void Update() {
		setData();
		setTable();
		Edit();
		table.clearSelection();
		NoSelected();
	}
}