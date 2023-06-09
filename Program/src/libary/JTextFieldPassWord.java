package libary;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class Key implements KeyListener
{
    private Input tf;
    public Key(Input pl)
    {
    	tf = pl;
    }
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if(!tf.IsView()) {
			if(Integer.valueOf(e.getKeyChar()) == 8) {
	              if(tf.getPass().length() > 0)
	              {
	            	  tf.setPass(tf.getPass().substring(0, tf.getPass().length() - (tf.getPass().length() - tf.getText().length())));
	              }
			}
			else
			{
				if(tf.getText().length() > tf.getPass().length())
				  tf.setPass(tf.getPass() + e.getKeyChar());
				else if(tf.getText().length() == 1)
				  tf.setPass(tf.getText());	
				else
				  tf.setPass((tf.getPass().substring(0, tf.getText().length()) + e.getKeyChar()).substring(0, tf.getText().length()));
			}
			String t = "";
			for(int i = 0; i < tf.getText().length(); i++)
				t += "*";
			tf.setText(t);
		}
		else
			tf.setPass(tf.getText());
	}
	
}
class Input extends JTextField{
	/**
	 * 
	 */
	private String pass;
	private Boolean isView;
    public Input()
    {
    	super();
    	isView = false;
    	pass = new String();
    	pass = "";
    	this.addKeyListener(new Key(this));
    } 
    public void setPass(String p)
    {
    	pass = p;
    }
    public String getPass()
    {
    	return pass;
    }
    public void setView()
    {
    	this.isView = !isView;
    }
    public Boolean IsView()
    {
    	return isView;
    }
}
public class JTextFieldPassWord	extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Input input;
	private JLabel label;
	public JTextFieldPassWord()
	{
		this.setLayout(new BorderLayout());
		input = new Input();
		label = new JLabel();
		label.setBorder(new EmptyBorder(0,2,0,2));
		label.setIcon(new ImageIcon(define.URL.url + "\\Icon\\inputpassword\\" + "view16px.png"));
		this.setBackground(input.getBackground());
		this.setBorder(input.getBorder());
		this.add(input, BorderLayout.CENTER);
		this.add(label, BorderLayout.EAST);
		input.setBorder(new EmptyBorder(0, 2, 0, 0));
		label.addMouseListener(new MouseListener() {
			private Boolean isView = false;
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseClicked(MouseEvent e) {
				if(!isView)
				{
					label.setIcon(new ImageIcon(define.URL.url + "\\Icon\\inputpassword\\" + "hidden16px.png"));
					isView = true;
					input.setText(input.getPass());
					input.setView();
				}
				else
				{
					label.setIcon(new ImageIcon(define.URL.url + "\\Icon\\inputpassword\\" + "view16px.png"));
					isView = false;
					String str = "";
					input.setPass(input.getText());
					for(int i = 0; i < input.getText().length(); i++)
						str += "*";
					input.setText(str);
					input.setView();
				}
			}
		});
	}
	
	public String getPass()
	{
		return input.getPass();
	}
	
	public String getText()
	{
		return input.getText();
	}
	
	public void setText(String str)
	{
		input.setPass(str);
		input.setText("");
		for(int i = 0; i < str.length(); i++)
			input.setText(input.getText() + "*");
	}
}
