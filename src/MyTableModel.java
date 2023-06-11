import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class MyTableModel extends AbstractTableModel {
    private Object[][] data;
    private String[] columnNames;

    public MyTableModel(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        data[row][column] = value;
        fireTableCellUpdated(row, column);
//        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 0;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 0) {
            return Boolean.class;
        }
        return super.getColumnClass(column);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void configureCheckboxColumn(JTable table, int column) {
        TableColumn checkboxColumn = table.getColumnModel().getColumn(column);
        checkboxColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                Boolean selected = (value != null && (Boolean) value);
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected(selected);
                checkbox.setHorizontalAlignment(SwingConstants.CENTER);
                checkbox.setBackground(table.getBackground());
                checkbox.setBorderPainted(true);
                checkbox.setBorder(table.getBorder());
                super.setValue(checkbox);
            }
        });

        checkboxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Object getCellEditorValue() {
                return ((JCheckBox) editorComponent).isSelected();
            }
        });
    }
}
