package model;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
public class File extends Element{
    private String exName;
    private double size;
    private java.util.Date dateModified;
    private static Map<String, String> type_link;
    private static Map<String, String> type_name;
    public File(int id)
    {
  	    super(id, "File new 1");
  	    this.exName = "";
  	    this.size = 0;
  	    this.dateModified = java.util.Calendar.getInstance().getTime();	    
    }
    public File(int id, String name)
    {
  	    super(id, name);
  	    this.exName = "";
  	    this.size = 0;
  	    this.dateModified = java.util.Calendar.getInstance().getTime();
    }
    public File(int id, String name, String dateCreate, 
    		    String exName, double size, String modifield)
    {
        super(id, name);
        this.exName = exName;
        this.size = size;
        this.dateModified = java.util.Calendar.getInstance().getTime();
    }
    public File(int id, String name, String dateCreate, Element parent, 
		    String exName, double size, String modifield)
    {
    	super(id, name, parent);
        this.exName = exName;
        this.size = size;
        this.dateModified = java.util.Calendar.getInstance().getTime();
    }
	@Override
	public String getExName() {
		return exName;
	}
	@Override
	public void setExName(String exName) {
		this.exName = exName;
	}
	@Override
	public double getSize() {
		return size;
	}
	@Override
	public void setSize(double size) {
		this.size = size;
	}
	@Override
	public Date getDateModified(){
		return (Date) dateModified;
	}
	@Override
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	@Override
	public LinkedList<Element> getChildrents() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setChildrents(LinkedList<Element> childrens) {
		// TODO Auto-generated method stub
		
	}


      
}
