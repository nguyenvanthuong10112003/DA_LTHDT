package libary;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
class Key implements KeyListener
{
    private JTextFieldPassWord tf;
    public Key(JTextFieldPassWord pl)
    {
    	tf = pl;
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		String p = "";
		if(Integer.valueOf(e.getKeyChar()) == 8) {
              if(tf.getPass().length() > 0)
              {
            	  tf.setPass(tf.getPass().substring(0, tf.getPass().length() - (tf.getPass().length() - tf.getText().length())));
              }
		}
		else
		{
			if(tf.getText().length() > tf.getPass().length())
			  tf.setPass(tf.getPass() + tf.getText().charAt(tf.getText().length() - 1));
		}
		String t = "";
		for(int i = 0; i < tf.getText().length(); i++)
			t += "*";
		tf.setText(t);
	}
	
}
public class JTextFieldPassWord extends JTextField{
	private String pass;
    public JTextFieldPassWord()
    {
    	super();
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
}
