package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

import define.table.FOLDER;
import define.table.QUICKACCESS;

public class Folder extends Element {
	private LinkedList<Element> childrens;
	private static String table = FOLDER.nametable;
	private static String sql;
	private static int max = 0;

	public Folder(int id) {
		super(id, "Folder new 1");
		childrens = new LinkedList<Element>();
		icon = "thispc";
		exName = "File folder";
		if (id > max)
			max = id;
	}

	public Folder(int id, String name) {
		super(id, name);
		childrens = new LinkedList<Element>();
		icon = "thispc";
		exName = "File folder";
		if (id > max)
			max = id;
	}

	public Folder(int id, String name, Folder parent) {
		super(id, name, parent);
		childrens = new LinkedList<Element>();
		icon = "folderIcon";
		exName = "File folder";
		if (id > max)
			max = id;
	}

	public Folder(int id, String name, LinkedList<Element> childrens) {
		super(id, name);
		if (childrens == null)
			this.childrens = new LinkedList<Element>();
		else
			this.childrens = childrens;
		icon = "folderIcon";
		exName = "File folder";
		if (id > max)
			max = id;
	}

	public Folder(int id, String name, Date create, LinkedList<Element> childrens, Folder parent) {
		super(id, name, create, parent);
		if (childrens == null)
			this.childrens = new LinkedList<Element>();
		else
			this.childrens = childrens;
		if (parent == null)
			icon = "thispc";
		else
			icon = "folderIcon";
		exName = "File folder";
		if (id > max)
			max = id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
		if (id > max)
			max = id;
	}

	@Override
	public void setChildrents(LinkedList<Element> childrens) {
		if (childrens == null)
			this.childrens = new LinkedList<Element>();
		else
			this.childrens = childrens;
	}

	@Override
	public LinkedList<Element> getChildrents() {
		return childrens;
	}

	@Override
	public void setSize(double size) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSize(Element e) {
		// TODO Auto-generated method stub
		double size = 0;
		if (e.getClass().equals(File.class))
			return ((File) e).getSize();
		else {
			if (childrens.size() > 0) {
				for (Element child : e.getChildrents()) {
					size += getSize(child);
				}
			}
		}
		return size;
	}

	@Override
	public void setExType(String exType) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getExType() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void setDateModified(Date dateModified) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getDateModified() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Folder searchFolder(Folder folder, int id) {
		Folder f = null;
		if (folder.id == id)
			f = folder;
		for (Element e : folder.childrens) {
			if (e.getClass().equals(Folder.class)) {
				if (searchFolder((Folder) e, id) != null)
					f = searchFolder((Folder) e, id);
			}
		}
		return f;
	}

	public Element searchFile(Element e, int id) {
		Element kq = null;
		if (e.getClass().equals(File.class) && e.id == id)
			kq = e;
		else {
			if (e.getClass().equals(Folder.class)) {
				for (Element child : e.getChildrents()) {
					if (kq == null)
						kq = searchFile(child, id);
					else
						break;
				}
			}
		}
		return kq;
	}

	public Boolean addToChilds(Element e) {
		for (Element child : childrens)
			if (child.getName() == e.getName()) {
				System.out.println("Khong the them vi trung ten");
				return false;
			}
		e.parent = this;
		childrens.add(e);
		return true;
	}

	@Override
	public void addToDB(Statement sta) {
		if (sta == null) {
			System.out.println("Khong the them vi Statement is null");
			return;
		}
		System.out.println(id);
		sql = "INSERT INTO " + table + " VALUES (" + id + ", N'" + name + "', '" + toDateTimeSQL(dateCreate) + "', "
				+ parent.id + ")";
		try {
			int check = sta.executeUpdate(sql);
			if (check >= 1)
				System.out.println("ADD to " + table + " success");
			else
				System.out.println("CAN'T ADD to " + table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR ADD to " + table);
		}
	}

	public void addToDBs(Statement sta) {
		if (sta == null) {
			System.out.println("Khong the them vi Statement is null");
			return;
		}
		addToDB(sta);
		if (childrens.size() > 0) {
			for (Element e : childrens)
				if (e.getClass().equals(Folder.class)) {
					((Folder) e).addToDBs(sta);
				} else
					e.addToDB(sta);
		}
	}

	@Override
	public void deleteToDB(Statement sta) {
		if (childrens.size() > 0) {
			for (Element child : childrens)
				child.deleteToDB(sta);
		}
		sql = "DELETE " + table + " WHERE " + FOLDER.id + " = " + id;
		try {
			int check = sta.executeUpdate(sql);
			if (check > 0)
				System.out.println("Xoa du lieu tu " + table + " thanh cong");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateToDB(Statement sta, String name, int id) {
		if (this.id != id) {
			if (childrens.size() > 0) {
				for (Element child : childrens) {
					sql = "UPDATE " + table + "SET " + FOLDER.parent + " = " + id + "WHERE " + FOLDER.id + " = "
							+ child.id;
					try {
						int check = sta.executeUpdate(sql);
						if (check > 0)
							System.out.println("Cập nhật dữ liệu thành công");
						sta.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		sql = "UPDATE " + table + " SET " + FOLDER.id + " = " + id + ", " + FOLDER.nameFolder + " = N'" + name
				+ "' WHERE " + FOLDER.id + " = " + this.id;
		try {
			int check = sta.executeUpdate(sql);
			if (check > 0)
				System.out.println("Cập nhật dữ liệu thành công");
			sta.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static Folder TaoBanSao(Folder folder, Folder parent) {
		Folder newfolder = new Folder(folder.max + 1, folder.name, folder.dateCreate, null, parent);
		if (folder.childrens.size() > 0) {
			for (Element child : folder.childrens) {
				Element e;
				if (child.getClass().equals(File.class))
					e = ((File) child).TaoBanSao((File) child, newfolder);
				else
					e = ((Folder) child).TaoBanSao((Folder) child, newfolder);
				newfolder.childrens.add(e);
			}
		}
		return newfolder;
	}

	public Boolean checkNameChild(String name) {
		for (Element child : childrens)
			if (child.name.equals(name))
				return true;
		return false;
	}

	public Boolean checkIsChild(LinkedList<Element> list, Folder nows) {
		if (nows == null)
			return true;
		for (Element e : list)
			if (e.equals(nows))
				return false;
			else if (e.equals(list.getLast()))
				return checkIsChild(list, nows.getParent());
		return true;
	}

	@Override
	public String getTable() {
		if (table == null)
			return "";
		return table;
	}

	public static void setIdMax(Statement sta) {
		sql = "SELECT MAX(" + FOLDER.id + ") AS ID FROM " + table;
		try {
			ResultSet rs = sta.executeQuery(sql);
			if (rs.next()) {
				max = rs.getInt("ID");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean addToQuickAccess(Statement sta, User user) {
		String sql = "INSERT INTO " + QUICKACCESS.nametable + "(" + QUICKACCESS.user + "," + QUICKACCESS.folder + ") "
				+ "VALUES ('" + user.getTenDangNhap() + "'," + id + ")";
		int check = 0;
		try {
			check = sta.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (check == 0)
			return false;
		return true;
	}

	public static void resertMax() {
		max = -1;
	}

	@Override
	public int getMax() {
		return max;
	}

}
