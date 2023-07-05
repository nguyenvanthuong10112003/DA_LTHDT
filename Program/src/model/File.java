package model;

import java.util.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import define.table.FILE;
import java.time.LocalDateTime;

public class File extends Element {
	private Date dateModified;
	private String exType;
	private double size;
	private static Map<String, String> type_link;
	private static Map<String, String> type_name;
	private static String table = FILE.nametable;
	private static String sql;
	private static int max = 0;

	public File(String file) {
		if (type_link == null || type_name == null) {
			try {
				type_link = new HashMap<String, String>();
				type_name = new HashMap<String, String>();
				java.io.File read = new java.io.File(file);
				FileReader fr = new FileReader(read);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] arr = tach(line);
					type_link.put(arr[0], arr[2]);
					type_name.put(arr[0], arr[1]);
				}
				System.out.println("Read file icon success");
				fr.close();
				br.close();

			} catch (Exception ex) {
				System.out.println("error read file icon: " + ex);
			}
		}
	}

	private String[] tach(String text) {
		String[] kq = { "", "", "" };
		int j = 0;
		int z = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '|') {
				kq[j] = text.substring(z, i);
				j++;
				z = i + 1;
			}
		}
		kq[2] = text.substring(z, text.length());
		return kq;
	}

	@SuppressWarnings("deprecation")
	public File(int id) {
		super(id, "File new 1");
		this.exName = "";
		this.size = 0;
		this.exType = "";
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		this.icon = "null";
		if (id > max)
			max = id;
	}

	@SuppressWarnings("deprecation")
	public File(int id, String name, String ex) {
		super(id, name);
		this.exType = ex;
		this.size = 0;
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		if (type_name.get(ex) != null) {
			this.exName = type_name.get(ex);
		} else {
			this.exName = "Unknown";
		}
		if (type_link.get(ex) != null) {
			this.icon = type_link.get(ex);
		} else {
			this.icon = "null";
		}
		if (id > max)
			max = id;
	}

	@SuppressWarnings("deprecation")
	public File(int id, String name, String ex, Folder parent) {
		super(id, name, parent);
		this.exType = ex;
		this.size = 0;
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		if (type_name.get(ex) != null) {
			this.exName = type_name.get(ex);
		} else {
			this.exName = "Unknown";
		}
		if (type_link.get(ex) != null) {
			this.icon = type_link.get(ex);
		} else {
			this.icon = "null";
		}
		if (id > max)
			max = id;
	}

	@SuppressWarnings("deprecation")
	public File(int id, String name, String ex, double size, String modifield) {
		super(id, name);
		this.exType = ex;
		this.size = size;
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		if (type_name.get(ex) != null) {
			this.exName = type_name.get(ex);
		} else {
			this.exName = "Unknown";
		}
		if (type_link.get(ex) != null) {
			this.icon = type_link.get(ex);
		} else {
			this.icon = "null";
		}
		if (id > max)
			max = id;
	}

	@SuppressWarnings("deprecation")
	public File(int id, String name, Folder parent, String ex, double size, String modifield) {
		super(id, name, parent);
		this.exType = ex;
		this.size = size;
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		if (type_name.get(ex) != null) {
			this.exName = type_name.get(ex);
		} else {
			this.exName = "Unknown";
		}
		if (type_link.get(ex) != null) {
			this.icon = type_link.get(ex);
		} else {
			this.icon = "null";
		}
		if (id > max)
			max = id;
	}

	public File(int id, String name, Date create, Date modifield, String ex, double size, Folder parent) {
		super(id, name, create, parent);
		this.exType = ex;
		this.size = size;
		this.dateModified = modifield;
		if (type_name.get(ex) != null) {
			this.exName = type_name.get(ex);
		} else {
			this.exName = "Unknown";
		}
		if (type_link.get(ex) != null) {
			this.icon = type_link.get(ex);
		} else {
			this.icon = "null";
		}
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
	public int getMax() {
		return max;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setSize(double size) {
		this.size = size;
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
	}

	@Override
	public double getSize() {
		return size;
	}

	@Override
	public void setChildrents(LinkedList<Element> childrens) {
	}

	@Override
	public LinkedList<Element> getChildrents() {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setExType(String exType) {
		this.exType = exType;
		if (type_name.get(exType) != null) {
			this.exName = type_name.get(exType);
		} else {
			this.exName = "Unknown";
		}
		if (type_link.get(exType) != null) {
			this.icon = type_link.get(exType);
		} else {
			this.icon = "null";
		}
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
	}

	@Override
	public String getExType() {
		return exType;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
		this.dateModified = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
	}

	@Override
	public Date getDateModified() {
		return dateModified;
	}

	@Override
	public void addToDB(Statement sta) {
		if (sta == null)
			System.out.println("Khong the them vi Statement is null");
		sql = "INSERT INTO " + table + " VALUES (" + id + ", N'" + name + "', '" + toDateTimeSQL(dateCreate) + "', '"
				+ toDateTimeSQL(dateModified) + "', " + size + ", '" + exType + "', " + parent.id + ")";
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

	@Override
	public void deleteToDB(Statement sta) {
		if (sta == null)
			return;
		sql = "DELETE " + table + " WHERE " + FILE.id + " = " + id;
		try {
			int check;
			check = sta.executeUpdate(sql);
			if (check >= 1)
				System.out.println("Xoa du lieu tu " + table + " thanh cong");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateToDB(Statement sta, String name, int id) {
		sql = "UPDATE " + table + " SET " + FILE.id + " = " + id + ", " + FILE.namefile + " = N'" + name + "', "
				+ FILE.type + " = '" + exType + "' WHERE " + FILE.id + " = " + this.id;
		try {
			int check = sta.executeUpdate(sql);
			if (check > 0)
				System.out.println("Cập nhật dữ liệu thành công");
			sta.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static File TaoBanSao(File file, Folder parent) {
		return new File(File.max + 1, file.name, file.dateCreate, file.dateModified, file.exType, file.size, parent);
	}

	@Override
	public String getTable() {
		if (table == null)
			return "";
		return table;
	}

	public static void resertMax() {
		max = -1;
	}

	public static void setIdMax(Statement sta) {
		sql = "SELECT MAX(" + FILE.id + ") AS ID FROM " + table;
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

	@Override
	public void ghiFile(FileWriter out) {
		try {
			out.write(id + "|" + name + "|" + getStringTime(dateCreate) + "|"
					+ getStringTime(dateModified) + "|" + exType + "|"
					+ size + "|" + (parent != null ? parent.id : "") + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
