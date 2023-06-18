import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


/**
 * Класс - модель таблицы, с основными настройками ее функционала
 * Created by Nikita.Manzhukov on 16.06.2023
 */
public class MyTableModel extends AbstractTableModel {
    private ArrayList<CountryInfo> data;
    private final String[] columnNames;

    public MyTableModel(String[] columnNames) {

        this.columnNames = columnNames;

        setData();


    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        CountryInfo country = data.get(row);
        switch (column) {
            case 0:
                return country;
            case 1:
                return country.getRegion();
            case 2:
                return country.getPopulation();
            case 3:
                return country.getForAgainst();
            default:
                return null;
        }
    }
    @Override
    public void setValueAt(Object value, int row, int col) {

        CountryInfo country = data.get(row);
        country.setForAgainst((boolean) value);

        fireTableCellUpdated(row,col);

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == columnNames.length - 1;
    }

    /**
     * Указываю какой тип данных должен содержаться в колонках таблицы
     */
    @Override
    public Class<?> getColumnClass(int column) {

        switch(column){

            case 0:
                return CountryInfo.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Boolean.class;
            default:
                return Object.class;

        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Заполняю таблицу данными
     */
    private void setData(){
        data = new ArrayList<>();

        data.add(new CountryInfo("Россия","Европа",150, true));
        data.add(new CountryInfo("Германия","Европа",250, false));
        data.add(new CountryInfo("Китай","Азия",1570, false));
        data.add(new CountryInfo("Испания","Европа",129, false));
        data.add(new CountryInfo("Аргентина","Америка",179, true));

    }

}
