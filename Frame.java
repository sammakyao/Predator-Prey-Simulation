import javax.swing.*;

public class Frame extends JFrame {
    char[][] world_Char;
    int[][] frame_data;

    public Frame(char[][] world_Char, int[][] frame_data){
        this.frame_data = frame_data;
        this.world_Char = world_Char;
    }

    public void simulate(){
        setTitle("Welcome to Samwel's World Simulation");
        Component component = new Component(world_Char, frame_data);
        setSize(world_Char.length*100, world_Char[0].length*100);
        add(component);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
