package libary;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelIcon extends JLabel
{
	public JLabelIcon()
	{
		super();
	}
	public JLabelIcon(ImageIcon icon)
	{
		super();
		if(icon != null) {
			setIcon(icon);
		setHorizontalAlignment(JLabel.CENTER);
		}
	}
}
			