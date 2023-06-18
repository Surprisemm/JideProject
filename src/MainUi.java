import com.jidesoft.grid.AutoFilterTableHeader;
import com.jidesoft.grid.BooleanCheckBoxCellEditor;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.swing.JideTabbedPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


/**
 * Класс - основной, тут создается и настраивается внешний вид.
 * Created by Nikita.Manzhukov on 16.06.2023
 */

public class MainUi extends JFrame {

    private SortableTable table;
    private final GridBagConstraints gbc;
    private final JPanel tablePanel;
    private final String[] columnNames = {"Название", "Регион", "Население", "За / Против"};
    public MainUi(){

        super("JideTableProject");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();

        int windowDimWidth = dimension.width / 2;
        int windowDimHeight = dimension.height / 2;

        this.setBounds(dimension.width / 2 - windowDimWidth / 2, dimension.height / 2 - windowDimHeight / 2, windowDimWidth, windowDimHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUIFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.PLAIN, 18));

        tablePanel = new JPanel(new GridBagLayout());

        int tablePadding = 10;
        tablePanel.setBorder(new EmptyBorder(tablePadding, tablePadding, tablePadding, tablePadding));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        tablePanel.setPreferredSize(new Dimension(windowDimWidth, windowDimHeight));

        initTable();

        myCellRender();

        getContentPane().add(tablePanel);
        pack();
        setVisible(true);

    }
    public static void main(String[] args) {
        MainUi mu = new  MainUi();
    }

    /**
     * Установка шрифта для всего проекта
     */
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements()){
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof javax.swing.plaf.FontUIResource){
                UIManager.put(key, f);
            }
        }
    }

    /**
     * Создние сортируемой таблицы и установка модели для нее
     */
    public void initTable(){

        JideTabbedPane tabbedPane = new JideTabbedPane();
        tabbedPane.setTabShape((JideTabbedPane.SHAPE_BOX));

        MyTableModel model = new MyTableModel(columnNames);

        table = new SortableTable(model);
        table.setRowResizable(true);
        table.setVariousRowHeights(true);
        table.setSelectInsertedRows(false);

        AutoFilterTableHeader header = new AutoFilterTableHeader(table);
        header.setAutoFilterEnabled(true);
        header.setUseNativeHeaderRenderer(true);

        table.setTableHeader(header);

        tablePanel.add(new JScrollPane(table), gbc);

    }

    /**
     * Настройка рендера ячеек в определенных колонках (1, 3, 4)
     */
    private void myCellRender(){

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

        TableCellRenderer checkBoxRenderer = new DefaultTableCellRenderer(){
            private final JCheckBox checkBox = new JCheckBox();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                checkBox.setSelected(value != null && (boolean) value);
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                if (isSelected){
                    checkBox.setBackground(table.getSelectionBackground());
                } else {
                    checkBox.setBackground(Color.white);
                }
                return checkBox;
            }
        };
        table.getColumnModel().getColumn(3).setCellRenderer(checkBoxRenderer);
        table.getColumnModel().getColumn(3).setCellEditor(new BooleanCheckBoxCellEditor());

        TableCellRenderer countryRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof CountryInfo) {
                    CountryInfo country = (CountryInfo) value;
                    JLabel label = new JLabel(country.getIcon());
                    label.setText(country.getCountryName());
                    label.setOpaque(true);
                    label.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                    label.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(countryRenderer);


    }
}