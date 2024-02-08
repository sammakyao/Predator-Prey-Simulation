import javax.swing.*;
import java.awt.*;

public class Component extends JComponent {
    char[][] world_Char;
    int[][][] population_data;

    public Component(char[][] world_Char, int[][] frameDatas) {
        setBackground(Color.WHITE);
        this.world_Char = world_Char;
        this.population_data = modifyTo3d(frameDatas);
        setSize(100 * world_Char[0].length, 100 * world_Char.length);
    }

    private int[][][] modifyTo3d(int[][] frameDatas) {
        int[][][] new_data = new int[world_Char.length][world_Char[0].length][2];
        int currIdx = 0;
        for (int i = 0; i < world_Char.length; i++) {
            for (int j = 0; j < world_Char[0].length; j++) {
                new_data[i][j] = frameDatas[currIdx];
                currIdx++;
            }
        }
        return new_data;
    }

    public void paintComponent(Graphics g) {

        for (int i = 0; i < world_Char.length; i++) {
            for (int j = 0; j < world_Char[0].length; j++) {
                Square square = new Square(world_Char[i][j], population_data[i][j], 100, 100 * i, 100 * j);
                square.paint(g);
            }
        }
    }
}