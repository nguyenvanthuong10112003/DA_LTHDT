package model;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

abstract public class Element {
    protected int id;
    protected String name; 
    protected Date dateCreate;
    protected Element parent;
    public Element()
    {
   	    parent = null;
    }
    public Element(int id, String name)
    {
   	    this.id = id;
   	    this.name = name;
   	    this.dateCreate = java.util.Calendar.getInstance().getTime();
   	    parent = null;
    }
    public Element(int id, String name, Element parent)
    {
   	    this.id = id;
   	    this.name = name;
   	    this.dateCreate = java.util.Calendar.getInstance().getTime();
   	    this.parent = parent;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Element getParent() {
		return parent;
	}
	public void setParent(Element parent) {
		this.parent = parent;
	}
	public abstract LinkedList<Element> getChildrents();
	public abstract void setChildrents(LinkedList<Element> childrens);
	public abstract String getExName();
	public abstract void setExName(String exName);
	public abstract double getSize();
	public abstract void setSize(double size);
	public abstract Date getDateModified();
	public abstract void setDateModified(Date dateModified);
}
