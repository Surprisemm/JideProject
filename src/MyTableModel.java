import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class MyTableModel extends AbstractTableModel {
    private Object[][] data;
    private String[] columnNames;

    public MyTableModel(String[] columnNames) {

        this.columnNames = columnNames;

        setDataInArray();

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


        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                System.out.print(data[i][j] + " || ");
            }
            System.out.println();
        }
        System.out.println("===============================================");

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == columnNames.length - 1;
    }

    @Override
    public Class<?> getColumnClass(int column) {

        switch(column){

            case 0:
            case 1:
            default:
                return String.class;

            case 2:
                return Integer.class;

            case 3:
                return Boolean.class;

        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    private void setDataInArray(){

        /*data = new Object[10][4];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnNames.length - 1; j++) {
                data[i][j] = "Ячейка: " + i + " " + j;
                data[i][columnNames.length - 1] = false;
            }
        }*/



        CSVReader reader = new CSVReader();
        reader.readCSV("data.csv", ",");

        data = new Object[reader.getRowCount()][columnNames.length];

        data = reader.getData();

    }



}
