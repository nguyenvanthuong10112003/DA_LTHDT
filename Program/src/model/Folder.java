package model;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
public class Folder extends Element{
    private LinkedList<Element> childrens;
    public Folder(int id)
    {
    	super(id, "Folder new 1");
    	childrens = new LinkedList<Element>();
    }    
    public Folder(int id, String name)
    {
    	super(id, name);
    	childrens = new LinkedList<Element>();
    }
    public Folder(int id, String name, LinkedList<Element> childrens)
    {
    	super(id, name);
    	this.childrens = childrens;
    }
    @Override
	public LinkedList<Element> getChildrents() {
		return childrens;
	}
    @Override
	public void setChildrents(LinkedList<Element> childrens) {
		this.childrens = childrens;
	}
	@Override
	public String getExName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setExName(String exName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setSize(double size) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Date getDateModified() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDateModified(Date dateModified) {
		// TODO Auto-generated method stub	
	}

    
}
