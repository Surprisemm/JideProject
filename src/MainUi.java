import com.jidesoft.grid.AutoFilterTableHeader;
import com.jidesoft.grid.FilterableTableModel;
import com.jidesoft.grid.JideTable;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.swing.JideScrollPane;
import com.jidesoft.swing.JideTabbedPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainUi extends JFrame {

    private final int tablePadding = 10;
    private SortableTable table;
    private final GridBagConstraints gbc;
    private final JPanel tablePanel;
    private final int rowData = 10;
    private final int colData = 4;
    private Object[][] data = new Object[rowData][colData];
    private String[] columnNames = {"Название", "Регион", "Население", "За / Против"};
    public MainUi(){

        super("JideTableProject");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();

        int windowDimWidth = dimension.width / 2;
        int windowDimHeight = dimension.height / 2;

        this.setBounds(dimension.width / 2 - windowDimWidth / 2, dimension.height / 2 - windowDimHeight / 2, windowDimWidth, windowDimHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUIFont(new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 18));

        tablePanel = new JPanel(new GridBagLayout());

        tablePanel.setBorder(new EmptyBorder(tablePadding, tablePadding, tablePadding, tablePadding));

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        tablePanel.setPreferredSize(new Dimension(windowDimWidth, windowDimHeight));

        initTable();

        cellDataToLeftSide(2);

        getContentPane().add(tablePanel);
        pack();
        setVisible(true);

    }
    public static void main(String[] args) {
        MainUi mu = new  MainUi();
    }

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

        tabbedPane.add("FilterableTableModel", new JScrollPane(table));

        tablePanel.add(tabbedPane, gbc);

    }

    private void cellDataToLeftSide( int columnIndex){
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(leftRenderer);
    }
}