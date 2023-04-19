package libary;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class LabelEditor extends DefaultCellEditor {

    protected JLabel label;
    private String labelp;
    public LabelEditor(JCheckBox checkbox,ImageIcon icon, int ALIGMENT) {
    	super(checkbox);
        label = new JLabelIcon(icon);
        label.setOpaque(true);
        label.setHorizontalAlignment(ALIGMENT);
        label.setBorder(new EmptyBorder(10,10,10,10));

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
        labelp = value == null ? "" : value.toString();
        label.setText(labelp);
        return label;
    }

    @Override
    public Object getCellEditorValue() {
        return labelp;
    }

}

