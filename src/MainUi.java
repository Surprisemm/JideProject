import com.jidesoft.grid.FilterableTableModel;
import com.jidesoft.grid.JideTable;
import com.jidesoft.swing.JideScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class MainUi extends JFrame {

    private final int tablePadding = 10;
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

        setDataInArray();

        initTable();


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

        MyTableModel model = new MyTableModel(data, columnNames);

        FilterableTableModel ftm = new FilterableTableModel(model);

        JideTable jideTable = new JideTable(ftm);
//        jideTable.setTableHeader(null);

        //   model.configureCheckboxColumn(jideTable, colData - 2);


        tablePanel.add(new JideScrollPane(jideTable), gbc);

    }

    private void setDataInArray(){
        for (int i = 0; i < rowData; i++) {
            for (int j = 0; j < colData - 1; j++) {
                data[i][j] = "Ячейка: " + i + " " + j;
                data[i][colData - 1] = false;
            }
        }
    }
}