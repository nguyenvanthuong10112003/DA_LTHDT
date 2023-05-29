package model;

import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
public class Folder extends Element{
    private LinkedList<Element> childrens;
    private String icon;
    public Folder(int id)
    {
    	super(id, "Folder new 1");
    	childrens = new LinkedList<Element>();
    	icon = "folderIcon";
        exName = "File folder";
    }    
    public Folder(int id, String name)
    {
    	super(id, name);
    	childrens = new LinkedList<Element>();
    	icon = "folderIcon";
    	exName = "File folder";
    }
    public Folder(int id, String name, Folder parent)
    {
    	super(id, name, parent);
    	childrens = new LinkedList<Element>();
    	icon = "folderIcon";
    	exName = "File folder";
    }
    public Folder(int id, String name, LinkedList<Element> childrens)
    {
    	super(id ,name);
    	if(childrens == null)
      	  this.childrens = new LinkedList<Element>();	
      	else
      	  this.childrens = childrens;
    	icon = "folderIcon";
    	exName = "File folder";
    }
    public Folder(int id, String name, Date create, LinkedList<Element> childrens, Folder parent)
    {
    	super(id ,name, create, parent);
    	if(childrens == null)
    	  this.childrens = new LinkedList<Element>();	
    	else
    	  this.childrens = childrens;
    	if(parent == null)
    		icon = "thispc";
    	else
    	    icon = "folderIcon";
    	exName = "File folder";
    }
    public String getIcon()
    {
    	return icon;
    }
    @Override
	public LinkedList<Element> getChildrents() {
		return childrens;
	}
    @Override
	public void setChildrents(LinkedList<Element> childrens) {
    	if(childrens == null)
      	  this.childrens = new LinkedList<Element>();	
      	else
      	  this.childrens = childrens;
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
	public String getExType() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public void setExType(String exType) {
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
	public Folder searchFolder(Folder folder, int id)
	{
        if(folder.id == id)
            return folder;
        Folder f = null;
        if(childrens.size() > 0)
        for(Element e : folder.childrens)
        {
        	if(e.getClass().equals(Folder.class))
        	{
        		if(searchFolder((Folder) e, id) != null)
        		f = searchFolder((Folder) e, id);
        	}
        }
        return f;
	}
}
