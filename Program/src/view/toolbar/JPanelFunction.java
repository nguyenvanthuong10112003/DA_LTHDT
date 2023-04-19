package view.toolbar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import libary.ColorList;
import libary.JLabelIcon;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.mouse;

public class JPanelFunction extends JPanel{
	private Font font;
	private JLabel iconback;
	private JLabel iconforward;
	private String back = "back16.png";
	private String forward = "forward16.png";
	private mouse mouselisten;
	public JPanelFunction(Font font)
	{
		super();
		this.font = font;
		this.setBorder(new LineBorder(Color.black));
		init();
		addObj();
	}
	private void init()
	{
		mouselisten = new mouse(this);
		iconback = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(back))));
		iconback.setToolTipText("back");
		iconforward = new JLabelIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource(forward))));
		iconforward.setToolTipText("forward");
	}
	private void addObj()
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//iconback.setEnabled(false);
		//iconforward.setEnabled(false);
		iconback.setOpaque(true);
		iconforward.setOpaque(true);
		iconback.setBackground(ColorList.Back_Ground);
		iconforward.setBackground(ColorList.Back_Ground);
		this.add(iconback);
		this.add(iconforward);
		iconback.addMouseListener(mouselisten);
		iconback.addMouseMotionListener(mouselisten);
		iconforward.addMouseListener(mouselisten);
		iconforward.addMouseMotionListener(mouselisten);
	}
	public void setColorIcon(int hash, Color color)
	{
		if(iconback.hashCode() == hash)
		{
			iconback.setBackground(color);
		}
		else if(iconforward.hashCode() == hash)
		{
			iconforward.setBackground(color);
		}
	}
}
