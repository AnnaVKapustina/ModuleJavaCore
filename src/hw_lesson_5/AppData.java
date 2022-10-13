package hw_lesson_5;

import java.io.*;
import java.util.ArrayList;

public class AppData {
    private String[] header;
    private Integer[][] data;

    public String[] getHeader() {
        return header;
    }
    public void setHeader (String[] header) {
        this.header = header;
    }
    public Integer[][] getData() {
        return data;
    }
    public void setData(Integer[][] data) {
        this.data = data;
    }

    private Integer[] stringToDataRow(String str) {
        String[] strings = str.split(";");
        Integer[] integers = new Integer[strings.length];

        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
    public void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            header = br.readLine().split(";");
            ArrayList<Integer[]> dataArr = new ArrayList<>();
            String temp;
            while ((temp = br.readLine()) != null) {
                dataArr.add(stringToDataRow(temp));
            }
            data = dataArr.toArray(new Integer[][]{});

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private <T> String rowToString(T[] row) {
        String res = "";
        for (int i = 0; i < row.length; i++) {
            res = res + row[i].toString();
            if (i != row.length - 1) {
                res += ";";
            }
        }
        res += "\n";
        return res;
    }
    public void writeFile(String fileName) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(fileName))) {
            wr.write(rowToString(header));
            for (Integer[] row: data) {
                wr.write(rowToString(row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
