import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Processor {
    private ArrayList<Integer> xInts;
    private ArrayList<Integer> yInts;
    private String fileName;

    public Processor() {
        xInts = new ArrayList<>();
        yInts = new ArrayList<>();
    }

    public void addDataPoint(int x, int y) {
        xInts.add(x);
        yInts.add(y);
    }

    public void readFile(String fileName) {
        this.fileName = fileName;
    }

    public void writeData(String filename) throws IOException {
        FileWriter outFile = new FileWriter(filename);
        for (int i = 0; i < xInts.size(); i++) {
            outFile.write(xInts.get(i) + "," + yInts.get(i) + "\n");
        }
        outFile.close();
    }

    public void printData() {
        for (int i = 0; i < xInts.size(); i++) {
            System.out.println("i: " + i + ", x: " + xInts.get(i) + ", y: " + yInts.get(i));
        }
    }

    private File getFile() {
        return new File(fileName);
    }

    private int[] getDimension() throws IOException {
        File file = getFile();
        int row = (int) Files.lines(Paths.get(fileName)).count();
        Scanner scanner = new Scanner(file);
        int column = scanner.nextLine().split(" ").length;
        scanner.close();
        return new int[]{row, column};
    }

    private char[][] makeChar(String[][] data) {
        char[][] newData = new char[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                newData[i][j] = data[i][j].charAt(0);
            }
        }
        return newData;
    }

    public char[][] getData() throws IOException {
        int[] dimension = getDimension();
        String[][] data = new String[dimension[0]][dimension[1]];
        Scanner scanner = new Scanner(getFile());
        int currIdx = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] charLine = line.split(" ");
            data[currIdx] = charLine;
            currIdx++;
        }
        scanner.close();
        return makeChar(data);
    }
}
