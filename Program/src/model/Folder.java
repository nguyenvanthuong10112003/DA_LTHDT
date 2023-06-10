package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

public class Folder extends Element {
	private LinkedList<Element> childrens;
	private String icon;
	private String table = "_Folder";
	private String sql;
    private static int max = 0;
	public Folder(int id) {
		super(id, "Folder new 1");
		childrens = new LinkedList<Element>();
		icon = "thispc";
		exName = "File folder";
		if(id > max)
			max = id;
	}

	public Folder(int id, String name) {
		super(id, name);
		childrens = new LinkedList<Element>();
		icon = "thispc";
		exName = "File folder";
		if(id > max)
			max = id;
	}

	public Folder(int id, String name, Folder parent) {
		super(id, name, parent);
		childrens = new LinkedList<Element>();
		icon = "folderIcon";
		exName = "File folder";
		if(id > max)
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
		if(id > max)
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
		if(id > max)
			max = id;
	}

	public String getIcon() {
		return icon;
	}

	@Override 
	public void setId(int id)
	{
		this.id = id;
		if(id > max)
			max = id;
	}
	
	@Override
	public LinkedList<Element> getChildrents() {
		return childrens;
	}

	@Override
	public void setChildrents(LinkedList<Element> childrens) {
		if (childrens == null)
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

	public Folder searchFolder(Folder folder, int id) {
		if (folder.id == id)
			return folder;
		Folder f = null;
		if (childrens.size() > 0)
			for (Element e : folder.childrens) {
				if (e.getClass().equals(Folder.class)) {
					if (searchFolder((Folder) e, id) != null)
						f = searchFolder((Folder) e, id);
				}
			}
		return f;
	}

	public Boolean addToChilds(Element e) {
        for(Element child : childrens)
        	if(child.getName() == e.getName())
        	{
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
		sql = "INSERT INTO _Folder VALUES (" + id + ", N'" + name + "', '" + toDateTimeSQL(dateCreate) + "', "
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
	
	public void addToAllDB(Statement sta) {
		if (sta == null) {
			System.out.println("Khong the them vi Statement is null");
			return;
		}
        addToDB(sta);
		if(childrens.size() > 0)
		{
			for(Element e : childrens)
				if(e.getClass().equals(Folder.class))
				{
					((Folder)e).addToAllDB(sta);
				}
				else
					e.addToDB(sta);
			    
		}
	}
	
	@Override
	public void deleteToDB(Statement sta)
	{
		if (childrens.size() > 0) {
			for (Element child : childrens) 
				child.deleteToDB(sta);
		}
		sql = "DELETE _Folder WHERE id = " + id;
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
	public void updateToDB(Statement sta, String name, int id)
	{
		if(this.id != id)
		{
			if(childrens.size() > 0)
			{
				for(Element child : childrens)
				{
					sql = "UPDATE " + table + "SET id_parent = " + id + "WHERE id = " + child.id;
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
		sql = "UPDATE " + table + " SET id = " + id + 
			  ", Fullname = N'" + name + "' WHERE id = " + this.id;
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
	
	public Folder TaoBanSao(Folder folder, Folder parent)
	{
		Folder newfolder = new Folder(folder.max + 1, folder.name, folder.dateCreate, null, parent);
		if(folder.childrens.size() > 0)
		{
			for(Element child : folder.childrens)
			{
				Element e;
				if(child.getClass().equals(File.class))
					e = ((File)child).TaoBanSao((File)child, newfolder);
				else
					e = ((Folder)child).TaoBanSao((Folder)child, newfolder);
				newfolder.childrens.add(e);
			}
		}
		return newfolder;
	}
	
	@Override
	public int getMax()
	{
		return max;
	}
	
	public Boolean checkNameChild(String name) {
		for(Element child : childrens)
			if(child.name.equals(name))
				return true;
		return false;
	}
	
	public Boolean checkIsChild(LinkedList<Element> list, Folder nows)
	{
		for(Element e : list)
			if(e.equals(nows))
				return false;
			else if(e.equals(list.getLast()))
				return checkIsChild(list, nows.getParent());
		return true;
	}
}
