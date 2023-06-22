package view.content.center;

import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import define.ColorList;
import java.awt.*;

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
