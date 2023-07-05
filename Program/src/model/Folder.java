package model;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
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
	}

	@Override
	public double getSize() {
		return 0;
	}

	public double getSize(Element e) {
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

	}

	@Override
	public String getExType() {
		return "";
	}

	@Override
	public void setDateModified(Date dateModified) {

	}

	@Override
	public Date getDateModified() {
		return null;
	}
	
	public static Element searchChild(Element e, Folder cha) {
		if (e.getParent() == null)
			return e;
		if (e.getParent().equals((Folder) cha))
			return e;
		return searchChild(e.getParent(), cha);
	}

	public Element searchElement(int i, int j, LinkedList<String> text) {
		Element e = null;
		if (name.equals(text.get(i))) {
			if (getClass().equals(Folder.class)) {
				if (i >= j)
					return this;
				else {
					for (Element child : getChildrents())
						if (e == null) {
							if(child.getClass().equals(Folder.class))
								e = ((Folder)child).searchElement(i + 1, j, text);
							else
							{
								if(i + 1 >= j)
								{
									if(child.getName().equals(text.get(i + 1)))		
										e = child;
								}
							}
						} else break;
				}
			}
		}
		return e;
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
		sql = "INSERT INTO " + table + " VALUES (" + id + ", N'" + name + "', '" + toDateTimeSQL(dateCreate) + "', "
				+ parent.id + ")";
		try {
			int check = sta.executeUpdate(sql);
			if (check >= 1)
				System.out.println("ADD to " + table + " success");
			else
				System.out.println("CAN'T ADD to " + table);
		} catch (SQLException e) {
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
		String sql1 = "SELECT * FROM " + QUICKACCESS.nametable + " WHERE " + QUICKACCESS.folder + " = " + id;
		String sql2 = "DELETE " + QUICKACCESS.nametable + " WHERE " + QUICKACCESS.folder + " = " + id;
		try {
			ResultSet rs = sta.executeQuery(sql1);
			if(rs.next()) {
				int check1 = sta.executeUpdate(sql2);
				if(check1 > 0)
					System.out.println("Delete " + id + " from " + QUICKACCESS.nametable + "success!");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int check = sta.executeUpdate(sql);
			if (check > 0)
				System.out.println("Xoa du lieu tu " + table + " thanh cong");
		} catch (SQLException e) {
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
							System.out.println("Update success!");
						sta.close();
					} catch (SQLException e1) {
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
			sta.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static Folder TaoBanSao(Folder folder, Folder parent) {
		Folder newfolder = new Folder(Folder.max + 1, folder.name, folder.dateCreate, null, parent);
		if (folder.childrens.size() > 0) {
			for (Element child : folder.childrens) {
				Element e;
				if (child.getClass().equals(File.class))
					e = File.TaoBanSao((File) child, newfolder);
				else
					e = Folder.TaoBanSao((Folder) child, newfolder);
				newfolder.childrens.add(e);
			}
		}
		return newfolder;
	}
	
	@Override
	public void ghiFile(FileWriter out) {
		try {
			out.write(id + "|" + name + "|" + getStringTime(dateCreate) + "||||"
					+ (parent != null ? parent.id : "") + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (childrens.size() > 0) {
			for (Element el : childrens) {
				el.ghiFile(out);
			}
		}
	}

	public Boolean checkNameChild(String name) {
		for (Element child : childrens)
			if (child.name.equals(name))
				return true;
		return false;
	}

	public static Boolean checkIsChild(LinkedList<Element> list, Folder nows) {
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
			e.printStackTrace();
		}
	}

	public Boolean addToQuickAccessSQL(Statement sta, User user) {
		String sql = "INSERT INTO " + QUICKACCESS.nametable + "(" + QUICKACCESS.user + "," + QUICKACCESS.folder + ") "
				+ "VALUES ('" + user.getTenDangNhap() + "'," + id + ")";
		int check = 0;
		try {
			check = sta.executeUpdate(sql);
		} catch (SQLException e) {
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

	public static Boolean isChild(Element con, Element cha) {
		if (cha == null)
			return true;
		if (con == null)
			return false;
		if (con.equals(cha))
			return true;
		else
			return isChild(con.getParent(), cha);
	}
}
