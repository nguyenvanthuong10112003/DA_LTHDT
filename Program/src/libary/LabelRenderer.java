package libary;

import java.awt.Component;
import java.awt.Toolkit;

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
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        //setIcon(new ImageIcon());
        return this;
    }
}