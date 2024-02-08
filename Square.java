import java.awt.*;
import java.util.HashMap;

public class Square {
    char panelType;
    int size, x, y;
    int[] population_data
            ;
    HashMap<Character, Color> colorMap = new HashMap() {{
        put('G', Color.GREEN); put('R', Color.BLUE); put('M', Color.LIGHT_GRAY);
    }};

    public Square(char panelType, int[] population_data, int size, int x, int y) {
        this.panelType = panelType;
        this.x = x;
        this.y = y;
        this.size = size;
        this.population_data = population_data;
    }

    public void paint(Graphics g) {
        Color color = colorMap.get(panelType);
        g.setColor(color);
        g.drawRect(x, y, size, size);
        g.fillRect(x, y, size, size);

        if (panelType == 'G'){
            g.setColor(Color.black);
            g.drawString("Lynx: " + population_data[0], x+15, y+40);
            g.drawString("Hare: " + population_data[1], x+15, y+60);
        }
    }

}
