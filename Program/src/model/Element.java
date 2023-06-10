package model;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;

import java.sql.Statement;
import java.time.LocalDate;
abstract public class Element{
	protected int id;
	protected String name;
    protected Date dateCreate;
    protected String icon;
    protected Folder parent;
    protected String exName;
    protected String table;
    private LinkedList<Authority> authority;
    public Element()
    {   	
    }
    public Element(int id)
    {   	
    	this.id = id;
    	this.name = "New Folder";
   	    this.parent = null;
   	    this.dateCreate = java.util.Calendar.getInstance().getTime();
   	    this.icon = "";
   	    this.exName = "";
   	    this.authority = new LinkedList<Authority>();
   	    this.dateCreate.setYear(java.time.LocalDate.now().getYear());
    }
	public Element(int id, String name) {
		this.id = id;
		this.dateCreate = java.util.Calendar.getInstance().getTime();
		this.name = name;
		this.parent = null;
		this.icon = "";
		this.exName = "";
		this.authority = new LinkedList<Authority>();
   	    this.dateCreate.setYear(java.time.LocalDate.now().getYear());
	}
    public Element(int id, String name, Folder parent)
    {
    	this.id = id;
    	this.name = name;
   	    this.dateCreate = java.util.Calendar.getInstance().getTime();
   	    this.parent = parent;
   	    this.icon = "";
   	    this.exName = "";
   	    this.authority = new LinkedList<Authority>();
   	    this.dateCreate.setYear(java.time.LocalDate.now().getYear());
    }
    public Element(int id, String name, String icon, Folder parent) {
   	    this.id = id;
		this.name = name;
		this.icon = icon;
		this.parent = parent;
		this.dateCreate = java.util.Calendar.getInstance().getTime();
   	    this.exName = "";
   	    this.authority = new LinkedList<Authority>();
   	    this.dateCreate.setYear(java.time.LocalDate.now().getYear());
	}
	public Element(int id, String name, Date dateCreate, Folder parent) {
		this.id = id;
		this.name = name;
		this.dateCreate = dateCreate;
		this.icon = "";
		this.parent = parent;
		this.exName = "";
		this.authority = new LinkedList<Authority>();
	}
	public Element(int id, String name, Date dateCreate, String icon, Folder parent, String exName,
			LinkedList<Authority> authority) {
		this.name = name;
		this.dateCreate = dateCreate;
		this.icon = icon;
		this.parent = parent;
		this.exName = exName;
		this.authority = authority;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	public LinkedList<Authority> getAuthority() {
		return authority;
	}
	public void setAuthority(LinkedList<Authority> authority) {
		this.authority = authority;
	}
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	public String getName()
	{
	    return name;	
	}
	public void setName(String name)
	{
	    this.name = name;
	}
	public String getTime(Date date)
	{
		if(date != null)
		  return check(date.getDate()) + "/" + check(date.getMonth()) + "/" + check(date.getYear()) + " " + check(date.getHours()) + ":" + check(date.getMinutes());
		return "";
	}
	public String check(String a)
	{
		if(a.length() >= 2)
			return a;
		return "0" + a;
	}
	
	public String check(int a)
	{
		if(a >= 10)
			return ((Integer)a).toString();
		return "0" + a;
	}
	
	public String to2(String s) {
		if (s.length() > 1)
			return s;
		else
			return "0" + s;
	}
	
	public String toDateTimeSQL(java.util.Date date) {
		return date.getYear() + "-" + to2(((Integer) date.getMonth()).toString()) + "-"
				+ to2(((Integer) date.getDate()).toString()) + " " + to2(((Integer) date.getHours()).toString()) + ":"
				+ to2(((Integer) date.getMinutes()).toString()) + ":" + to2(((Integer) date.getSeconds()).toString());
	}
	
	public Element timCon(Element e, Element cha) {
		if (e.getParent() == null)
			return e;
		if (e.getParent().equals((Folder) cha))
			return e;
		return timCon(e.getParent(), cha);
	}
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
