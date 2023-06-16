import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    private ArrayList<CountryInfo> data;
    private String[] columnNames;

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
        CountryInfo country = data.get(row); // Получаем объект CountryInfo из списка
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

        CountryInfo country = data.get(row); // Получаем объект CountryInfo из списка
        country.setForAgainst((boolean) value);

        fireTableCellUpdated(row,col);

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == columnNames.length - 1;
    }

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

    private void setData(){
        data = new ArrayList<>();

        data.add(new CountryInfo("Россия","Европа",150, true));
        data.add(new CountryInfo("Германия","Европа",250, false));
        data.add(new CountryInfo("Китай","Азия",1570, false));
        data.add(new CountryInfo("Испания","Европа",129, false));
        data.add(new CountryInfo("Аргентина","Америка",179, true));

    }





}
