import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

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

       /* for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                System.out.print(data[i][j] + " || ");
            }
            System.out.println();
        }
        System.out.println("===============================================");*/

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

        CSVReader reader = new CSVReader();
        reader.readCSV("data.csv", ",");

        data = new Object[reader.getRowCount()][columnNames.length];

        data = reader.getData();

        for (int i = 0; i < reader.getRowCount(); i++) {
            data[i][0] = unescapeEmojiCode(String.valueOf(data[i][0]));
        }

    }


    private static String unescapeEmojiCode(String text) {
        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < text.length()) {
            if (text.charAt(i) == '\\' && i + 1 < text.length() && text.charAt(i + 1) == 'u') {
                // Найден код эмоджи
                String emojiCode = text.substring(i + 2, i + 6);
                char emojiChar = (char) Integer.parseInt(emojiCode, 16);
                result.append(emojiChar);

                i += 6;
            } else {
                // Обычный символ
                result.append(text.charAt(i));

                i++;
            }
        }

        return result.toString();
    }

}
