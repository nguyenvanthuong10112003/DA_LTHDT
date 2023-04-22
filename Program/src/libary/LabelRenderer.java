package libary;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class LabelRenderer extends JLabel implements TableCellRenderer {

    public LabelRenderer(ImageIcon icon, int ALIGMENT) {
    	super(icon);
        setOpaque(true);
        setHorizontalAlignment(ALIGMENT);
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(ColorList.Back_Ground);
        this.addMouseListener(
        new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(new Color(0,120,215));
				setForeground(Color.white);
				}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        }
        );
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        if (isSelected) {
            this.setForeground(table.getSelectionForeground());
            this.setBackground(table.getSelectionBackground());
        } else {
            this.setForeground(table.getForeground());
            this.setBackground(table.getBackground());
        }
        //setIcon(new ImageIcon());
        return this;
    }
}