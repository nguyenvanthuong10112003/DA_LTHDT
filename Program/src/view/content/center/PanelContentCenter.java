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
import test.ConnectSQL;
import view.content.PanelContent;
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
			this.setViewportView(table);
			this.add(jPopupMenu);
			this.table.add(jPopupMenu);
			this.jPopupMenu.add(open);
			System.out.println("Tải thành công Content ở giữa");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Content ở giữa");
		}
	}

	public void init() {
		mouselisten = new mouse(this);
		this.addObj();
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
				// TODO Auto-generated method stub
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

	private void addEvent() {
		this.addMouseMotionListener(mouselisten);
		this.addMouseListener(mouselisten);
		table.addMouseListener(mouselisten);
		open.addActionListener(ActionListen);
	}

	public void showPopup(Component comp, int x, int y) {
		if (table.getSelectedRow() < 0)
			open.setEnabled(false);
		else
			open.setEnabled(true);
		jPopupMenu.show(comp, x, y);
	}

	public void clickedTable() {
		if (table.getSelectedRow() >= 0 && table.getSelectedRowCount() == 1) {
			pct.SELECTtable(nows != null ? nows.getChildrents().get(table.getSelectedRow()) : root);
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
					else if (!isChild(cuoi, nows))
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
	}

	public Boolean isChild(Element con, Element cha) {
		if (cha == null)
			return true;
		if (con == null)
			return false;
		if (con.equals(cha))
			return true;
		else
			return isChild(con.getParent(), cha);
	}

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

	public void Update() {
		setData();
		setTable();
		Edit();
		table.clearSelection();
		NoSelected();
	}

	public void setRoot(Folder root) {
		this.root = root;
		this.nows = null;
		this.Update();
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
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	};

	public void addObj() {
	}

	public void setColor() {
		this.setOpaque(true);
		// this.setBackground(back);

		/// nd.setBackground(back);
	}

	public void ghiDBWhenAddRow() throws ClassNotFoundException {
		Element e = nows.getChildrents().get(nows.getChildrents().size() - 1);

		try {
			Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
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
			Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
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

	public void ghiFile() throws IOException {
		FileWriter out = null;

		try {
			out = new FileWriter(define.URL.url + define.URL.urlLuuTru);
			System.out.println("Mo file du lieu thanh cong");
			ghiAlls(root, out);
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

	public void deletedRow() throws ClassNotFoundException {
		int[] sl = table.getSelectedRows();
		int n = table.getSelectedRowCount();
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (sl[i] < sl[j]) {
					int sq = sl[i];
					sl[i] = sl[j];
					sl[j] = sq;
				}

		try {
			Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
			if (connect != null) {
				System.out.println("Ket noi database thanh cong");
			} else {
				connect.close();
				System.out.println("Ket noi database that bai");
			}
			Statement sta = connect.createStatement();
			for (int i = 0; i < n; i++) {
				Element e = nows.getChildrents().get(sl[i]);
				if (pct.isLogin()) {
					e.deleteToDB(sta);
				}
				nows.getChildrents().remove(sl[i]);
			}
			sta.close();
			connect.close();
			if (!pct.isLogin())
				try {
					ghiFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pct.UpdateLeft();
		Update();
	}

	public JTable getTable() {
		return table;
	}

	public JPopupMenu getPopupMenu() {
		return jPopupMenu;
	}

	public void Pin() {
		LinkedList<Element> arr = new LinkedList<>();
		if (nows == null)
			arr.add(root);
		else {
			for (int i = 0; i < table.getSelectedRows().length; i++)
				if (nows.getChildrents().get(table.getSelectedRows()[i]).getClass().equals(Folder.class))
					arr.add(nows.getChildrents().get(table.getSelectedRows()[i]));
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
		for (int i = 0; i < table.getSelectedRowCount(); i++) {
			cut.add(nows.getChildrents().get(table.getSelectedRows()[i]));
			selectCut.add(table.getSelectedRows()[i]);
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
			for (int i = 0; i < table.getSelectedRowCount(); i++)
				copy.add(nows.getChildrents().get(arr[i]));
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
		if (table.getSelectedRowCount() >= 2)
			return;
		Element now;
		if (table.getSelectedRowCount() > 0)
			now = nows.getChildrents().get(table.getSelectedRow());
		else
			now = nows;
		LinkedList<Element> paste = new LinkedList<Element>();
		if (cut == null && copy == null)
			return;
		else if (copy != null) {
			paste = copy;
		} else if (cut != null) {
			if (countCut == 0) {
				if (!(new Folder(0)).checkIsChild(cut, (Folder) now))
					return;
				Folder parent = (Folder) cut.get(0).getParent();
				if (parent != null) {
					for (Element e : cut) {
						if (pct.isLogin()) {
							try {
								Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
								if (connect != null) {
									System.out.println("Ket noi database thanh cong");
								} else {
									connect.close();
									System.out.println("Ket noi database that bai");
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
			if (pct.isLogin()) {
				try {
					Connection connect = ConnectSQL.getJDBCConnection(define.SQLconnect.database);
					if (connect != null) {
						System.out.println("Ket noi database thanh cong");
					} else {
						connect.close();
						System.out.println("Ket noi database that bai");
						return;
					}
					Statement sta = connect.createStatement();
					if (el.getClass().equals(Folder.class))
						((Folder) el).addToDBs(sta);
					else
						((File) el).addToDB(sta);
					sta.close();
					connect.close();
				} catch (SQLException e1) {
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		pct.UpdateLeft();
		Update();
	}

	public void MoveTo() {

	}

	public void CopyTo() {

	}

	public void newRow(Boolean folder) throws IOException, ClassNotFoundException {
		if (nows != null) {
			if (folder) {
				String name = "Thư mục mới";
				int i = 1;
				while (((Folder) nows).checkNameChild(name)) {
					name = "Thư mục mới " + i;
					i++;
				}
				((Folder) nows).addToChilds(new Folder((new Folder(0)).getMax() + 1, name, (Folder) nows));
			} else {
				String name = "Tệp mới";
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
			pct.SELECTtable(nows.getChildrents().get(nows.getChildrents().size() - 1));
			table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
			pct.UpdateLeft();
			pct.setFunSelectedTablie(true);
			if (nows == null)
				pct.getScreen().FunEnablueRoot(true);
			else
				pct.getScreen().FunEnablueRoot(false);
			pct.getScreen().FunEnablueRoot(false);
		}
	}

	public void NoSelected() {
		open.setEnabled(false);
		table.clearSelection();
		pct.noSelected();
		pct.getScreen().FunEnablueRoot(true);
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

	public void Selected() {
		open.setEnabled(true);
	}

	public PanelContent getPanelContent() {
		return pct;
	}

	public void setNows(Element e) {
		nows = e;
		cuoi = e;
		check();
		Update();
	}

}