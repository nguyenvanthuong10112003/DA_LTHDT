package model;
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
public class File extends Element{
    private Date dateModified;
    private String exType;
    private double size;
    private static Map<String, String> type_link;
    private static Map<String, String> type_name;
    public File(String file)
    {
    	if(type_link == null || type_name == null) {
	    	try {
	    		type_link = new HashMap<String, String>();
	    		type_name = new HashMap<String, String>();
	    		java.io.File read = new java.io.File(file);
		    	FileReader fr = new FileReader(read);
		    	BufferedReader br = new BufferedReader(fr);
		    	String line;
		        while ((line = br.readLine()) != null){
		            String []arr = tach(line);
		            type_link.put(arr[0], arr[2]);
		            type_name.put(arr[0], arr[1]);
		        }
		        System.out.println("Đọc file Icon thành công");
		        fr.close();
		        br.close();
		        
		    }
			catch (Exception ex) {
					// TODO: handle exception
					System.out.println("Loi doc file: "+ ex);
			}
    	}
    }
    public File(int id)
    {
  	    super(id, "File new 1");
  	    this.exName = "";
  	    this.size = 0;
  	    this.exType = "";
  	    this.dateModified = java.util.Calendar.getInstance().getTime();	    
    }
    public File(int id, String name, String ex)
    {
  	    super(id, name);
  	    this.exType = ex;
  	    this.size = 0;
  	    this.dateModified = java.util.Calendar.getInstance().getTime();
  	    if(type_name.get(ex) != null)
  	    {
  	    	this.exName = type_name.get(ex);
  	    }
  	    else
  	    {
  	    	this.exName = "Unknown";
  	    }
  	    if(type_link.get(ex) != null)
  	    {
  	    	this.icon = type_link.get(ex);
  	    }
    }
    public File(int id, String name, 
    		    String ex, double size, String modifield)
    {
        super(id, name);
        this.exType = ex;
        this.size = size;
        this.dateModified = java.util.Calendar.getInstance().getTime();
  	    if(type_name.get(ex) != null)
  	    {
  	    	this.exName = type_name.get(ex);
  	    }
  	    else
  	    {
  	    	this.exName = "Unknown";
  	    }
  	    if(type_link.get(ex) != null)
  	    {
  	    	this.icon = type_link.get(ex);
  	    }
    }
    public File(int id, String name, Folder parent, 
		    String ex, double size, String modifield)
    {
    	super(id, name, parent);
        this.exType = ex;
        this.size = size;
        this.dateModified = java.util.Calendar.getInstance().getTime();
  	    if(type_name.get(ex) != null)
  	    {
  	    	this.exName = type_name.get(ex);
  	    }
  	    else
  	    {
  	    	this.exType = "Unknown";
  	    }
  	    if(type_link.get(ex) != null)
  	    {
  	    	this.icon = type_link.get(ex);
  	    }
    }
    private String[] tach(String text)
    {
    	String []kq = {"","",""};
    	int j = 0;
    	int z = 0;
    	for(int i = 0; i < text.length(); i++)
    	{
    		if(text.charAt(i) == '|')
    		{
    			kq[j] = text.substring(z, i);
    			j++;
    			z = i + 1;
    		}
    	}
    	kq[2] = text.substring(z, text.length());
    	return kq;
    }
	@Override
	public double getSize() {
		return size;
	}
	@Override
	public void setSize(double size) {
		this.size = size;
		this.dateModified = java.util.Calendar.getInstance().getTime();
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
	@Override
	public String getExType() {
		// TODO Auto-generated method stub
		return exType;
	}
	@Override
	public void setExType(String exType) {
		// TODO Auto-generated method stub
		this.exType = exType;
		this.dateModified = java.util.Calendar.getInstance().getTime();
	}
	@Override
	public Date getDateModified() {
		// TODO Auto-generated method stub
		return dateModified;
	}
	@Override
	public void setDateModified(Date dateModified) {
		// TODO Auto-generated method stub
		this.dateModified = dateModified;
		this.dateModified = java.util.Calendar.getInstance().getTime();
	} 
}
