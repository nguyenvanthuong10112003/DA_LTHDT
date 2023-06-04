package view.content.center;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.text.TabableView;
import javax.swing.ImageIcon;
import libary.ColorList;
import java.awt.*;
import javax.swing.*;

public class TableEditer extends AbstractCellEditor implements TableCellEditor {
	private JTextField input;
	private Object old;
	private Boolean onclick;

	public TableEditer(Boolean onclick) {
		input = new JTextField();
		this.onclick = onclick;
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return input.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		input.setText(value.toString());
		input.setEditable(false);
		input.setOpaque(true);
		input.setBorder(new EmptyBorder(0, 2, 0, 0));
		input.setBackground(ColorList.Hover);
		return input;
	}

	private String ImageIcon(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		// TODO Auto-generated method stub
		return true;
	}

}