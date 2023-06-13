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
                String[] row = parseCSVLine(line, delimiter);

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

    private String[] parseCSVLine(String line, String delimiter) {
        List<String> values = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean insideQuotes = false;
        for (char c : line.toCharArray()) {
            if (c == '\"') {
                insideQuotes = !insideQuotes;
            } else if (c == ';' && !insideQuotes) {
                values.add(sb.toString());
                sb.setLength(0);
            } else if (c == ',' && !insideQuotes) {
                values.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        values.add(sb.toString());

        return values.toArray(new String[0]);
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

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(data[i][j] + " || ");
            }
            System.out.println();
        }
        System.out.println("===============================================");

        return data;
    }
}
