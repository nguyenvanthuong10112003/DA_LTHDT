package model;

import java.util.Date;
import java.util.LinkedList;
import java.io.FileWriter;
import java.sql.Statement;
import java.time.LocalDateTime;

abstract public class Element {
	protected int id;
	protected String name;
	protected Date dateCreate;
	protected String icon;
	protected Folder parent;
	protected String exName;
	protected String table;

	public Element() {
	}

	@SuppressWarnings("deprecation")
	public Element(int id) {
		this.id = id;
		this.name = "New Folder";
		this.parent = null;
		this.dateCreate = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		this.icon = "";
		this.exName = "";
	}

	@SuppressWarnings("deprecation")
	public Element(int id, String name) {
		this.id = id;
		this.dateCreate = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		this.name = name;
		this.parent = null;
		this.icon = "";
		this.exName = "";
	}

	@SuppressWarnings("deprecation")
	public Element(int id, String name, Folder parent) {
		this.id = id;
		this.name = name;
		this.dateCreate = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		this.parent = parent;
		this.icon = "";
		this.exName = "";
	}

	@SuppressWarnings("deprecation")
	public Element(int id, String name, String icon, Folder parent) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.parent = parent;
		this.dateCreate = new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
				LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(),
				LocalDateTime.now().getSecond());
		this.exName = "";
	}

	public Element(int id, String name, Date dateCreate, Folder parent) {
		this.id = id;
		this.name = name;
		this.dateCreate = dateCreate;
		this.icon = "";
		this.parent = parent;
		this.exName = "";
	}

	public Element(int id, String name, Date dateCreate, String icon, Folder parent, String exName) {
		this.name = name;
		this.dateCreate = dateCreate;
		this.icon = icon;
		this.parent = parent;
		this.exName = exName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}

	public Folder getParent() {
		return parent;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static String to2(String s) {
		if (s.length() > 1)
			return s;
		else
			return "0" + s;
	}

	public String getLocation() {
		if (parent == null)
			return "/";
		return parent.getLocation() + parent.name + "/";
	}
	
	@SuppressWarnings("deprecation")
	public static java.util.Date toDate(String d) {
		return new Date(Integer.parseInt(d.substring(0, 4)), Integer.parseInt(d.substring(5, 7)),
				Integer.parseInt(d.substring(8, 10)));
	}
	
	@SuppressWarnings("deprecation")
	public static java.util.Date toDateTime(String so) {
		return new java.util.Date(Integer.parseInt(so.substring(6, 10)), Integer.parseInt(so.substring(3, 5)),
				Integer.parseInt(so.substring(0, 2)), Integer.parseInt(so.substring(11, 13)),
				Integer.parseInt(so.substring(14, 16)), Integer.parseInt(so.substring(17, 19)));
	}
	
	@SuppressWarnings("deprecation")
	public static java.util.Date toDateTimeSQL(String d) {
		return new Date(Integer.parseInt(d.substring(0, 4)), Integer.parseInt(d.substring(5, 7)),
				Integer.parseInt(d.substring(8, 10)), Integer.parseInt(d.substring(11, 13)),
				Integer.parseInt(d.substring(14, 16)), Integer.parseInt(d.substring(17, 19)));
	}
	
	@SuppressWarnings("deprecation")
	public static String toDateTimeSQL(java.util.Date date) {
		return date.getYear() + "-" + to2(((Integer) date.getMonth()).toString()) + "-"
				+ to2(((Integer) date.getDate()).toString()) + " " + to2(((Integer) date.getHours()).toString()) + ":"
				+ to2(((Integer) date.getMinutes()).toString()) + ":" + to2(((Integer) date.getSeconds()).toString());
	}

	@SuppressWarnings("deprecation")
	public static String getStringTime(Date date) {
		if (date != null)
			return check(date.getDate()) + "/" + check(date.getMonth()) + "/" + check(date.getYear()) + " "
					+ check(date.getHours()) + ":" + check(date.getMinutes()) + ":" + check(date.getSeconds());
		return "";
	}
	
	public static String check(int a) {
		if (a >= 10)
			return ((Integer) a).toString();
		return "0" + a;
	}

	public static String check(String a) {
		if (a.length() >= 2)
			return a;
		return "0" + a;
	}
	
	public abstract void ghiFile(FileWriter out);
	
	public abstract String getTable();

	public abstract int getMax();

	public abstract String getExType();

	public abstract void setExType(String exType);

	public abstract Date getDateModified();

	public abstract void setDateModified(Date dateModified);

	public abstract LinkedList<Element> getChildrents();

	public abstract void setChildrents(LinkedList<Element> childrens);

	public abstract double getSize();

	public abstract void setSize(double size);

	public abstract void updateToDB(Statement sta, String name, int id);

	public abstract void addToDB(Statement sta);

	public abstract void deleteToDB(Statement sta);
}
