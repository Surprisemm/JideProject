import javax.swing.table.AbstractTableModel;
import java.util.Arrays;

/**
 * Класс - модель таблицы, тут обрабатываются все указанные данные и заполняются ячейки таблицы сооответственно
 * Обрабатываются значения введенные в ячейки
 * Created by Nikita.Manzhukov on 01.06.2023
 */
public class MyTableModel extends AbstractTableModel {
    private final Object[][] data;
    private final boolean[] rowEditable;
    private final boolean[] columnEditable;

    private int rowData = 10; //tmp
    private int colData = 4;



    public MyTableModel() {

        data =  new Object[rowData][colData];

        for (int i = 0; i < rowData; i++) {
            for (int j = 0; j < colData - 1; j++) {
                data[i][j] = "Ячейка " + i + " " + j;
            }
        }

        rowEditable = new boolean[getRowCount()];
        columnEditable = new boolean[getColumnCount()];
        // По умолчанию все строки и столбцы редактируемые
        Arrays.fill(rowEditable, true);
        Arrays.fill(columnEditable, true);

    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        if (data.length > 0) {
            return data[0].length;
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * При изменении значения в ячейке
     * Ячейкам присваивается указанное значение
     * Обновляются итоги
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex){

        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == columnIndex - 1) {
            return Boolean.class; // Указываем, что последний столбец будет содержать чекбоксы
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return "Column " + column;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return rowEditable[row] && columnEditable[column];
    }

    /**
     * Указывает редактируемость строки
     */
    public void setRowEditable(int rowIndex, boolean editable) {
        if (rowIndex >= 0 && rowIndex < rowEditable.length) {
            rowEditable[rowIndex] = editable;
        }
    }

    /**
     * Указывает редактируемость столбца
     */
    public void setColumnEditable(int columnIndex, boolean editable) {
        if (columnIndex >= 0 && columnIndex < columnEditable.length) {
            columnEditable[columnIndex] = editable;
        }
    }



    }
