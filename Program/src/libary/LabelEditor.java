package libary;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.mouse;

public class LabelEditor extends DefaultCellEditor {
	private boolean isPushed;
    protected JLabel label;
    private String labelp;
    public LabelEditor(JCheckBox checkbox,ImageIcon icon, int ALIGMENT) {
    	super(checkbox);
        label = new JLabelIcon(icon);
        label.setOpaque(true);
        label.setHorizontalAlignment(ALIGMENT);
        label.setBorder(new EmptyBorder(10,10,10,10));
        label.setBackground(ColorList.Back_Ground);
        label.addMouseListener(new mouse(this));
        label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				label.setBackground(new Color(0,120,215));
				label.setForeground(Color.white);
			}
		});
    }
    public JLabel getLabel()
    {
    	return label;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
        if (isSelected) {
            label.setForeground(table.getSelectionForeground());
            label.setBackground(table.getSelectionBackground());
        } else {
            label.setForeground(table.getForeground());
            label.setBackground(table.getBackground());
        }
        //button.setText(label);
        System.out.println(label.getBackground());
        
        labelp = value == null ? "" : value.toString();
        label.setText(labelp);
        isPushed = true;
        return label;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return labelp;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

}

