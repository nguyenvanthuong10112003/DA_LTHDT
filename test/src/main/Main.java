package main;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.*;
import java.io.*;
class type {
	private String name;
	private String ex;
	private String icon;
	public type(String ex, String name, String icon)
	{
        this.name = name;
        this.ex = ex;
        this.icon = icon;
	}
	public String getName()
	{
		return name;
	}
	public String getEx()
	{
		return ex;
	}
	public String getIcon()
	{
		return icon;
	}
}

public class Main extends JFrame{
	private static int count = -1;
	private static LinkedList <type> types = new LinkedList<type>();
	private static JFrame jframe = new JFrame();
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel label3;
	private static JButton button;
	public Main()
	{
		
	}
    public static String[] tach(String text)
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
	public static void main(String []args) throws IOException
    {
		
		String currentDirectory = System.getProperty("user.dir");
		for(int i = 0; i < currentDirectory.length(); i++)
		{
			if(currentDirectory.charAt(i) == '\\')
			{
				currentDirectory = currentDirectory.substring(0, i) + '/' + currentDirectory.substring(i + 1, currentDirectory.length());
			}
		}
		currentDirectory = currentDirectory + "/src/Icon/afile.txt";
	    System.out.println("user.dir: " + currentDirectory);
		try {
	    	File file = new File(currentDirectory);
	    	FileReader fr = new FileReader(file);
	    	
	    	BufferedReader br = new BufferedReader(fr);
	    	String line;
	        while ((line = br.readLine()) != null){
	           String[] kq = tach(line);
	           type t = new type(kq[0], kq[1], kq[2]);
	           types.add(t);
	        }
	        //Bước 3: Đóng luồng
	        fr.close();
	        br.close();
	        }
		catch (Exception ex) {
				// TODO: handle exception
				System.out.println("Loi doc file: "+ ex);
		}
		
		jframe.setSize(500,600);
		jframe.setLayout(new GridLayout(4,1));
		label1 = new JLabel("Ex: ");
    	label2 = new JLabel("Name: ");
    	label3 = new JLabel();
    	button = new JButton("Next");
		jframe.add(label1);
		jframe.add(label2);
		try {
		jframe.add(label3);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		jframe.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				count++;
				System.out.println(count);
				label1.setText("Ex: " + types.get(count).getEx());
		    	label2.setText("Name: " + types.get(count).getName());
		    	System.out.println("..//Icon//" + types.get(count).getIcon() + "16px.png");
		    	label3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("..//Icon//" + types.get(count).getIcon() + "32px.png"))));
		    	button = new JButton("Next");
		    	if(count == types.size() - 1)
		    		count = -1;
			}
		});
		jframe.setVisible(true);
		
    }
}
