import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private List<Object[]> rows;

    public void readCSV(String filePath, String delimiter) {
        rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);

                Object[] convertedRow = new Object[row.length];
                for (int i = 0; i < row.length; i++) {
                    String value = row[i].trim();

                    if (i < 2) {
                        convertedRow[i] = value; // Преобразование первых двух столбцов в String
                    } else if (i == 2) {
                        convertedRow[i] = Integer.parseInt(value); // Преобразование третьего столбца в int
                    } else if (i == 3) {
                        convertedRow[i] = Boolean.parseBoolean(value); // Преобразование четвертого столбца в boolean
                    }
                }

                rows.add(convertedRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public Object[][] getData() {
        int rowCount = rows.size();
        int columnCount = rows.get(0).length;

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            data[i] = rows.get(i);
        }

        return data;
    }
}
