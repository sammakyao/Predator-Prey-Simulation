import java.io.IOException;
import java.util.ArrayList;

public class World {
    ArrayList<Tile> tile;
    char[][] world_Chars;
    Processor processor;

    public World() {
        this.tile = new ArrayList<>();
        processor = new Processor();
    }

    int numEdges() {
        int edges = 0;
        for (Tile tile : tile) {
            edges += tile.getNeighbors().size();
        }
        return edges;
    }

    int numTiles() {
        return tile.size();
    }

    ArrayList<Tile> getNeighbors(Tile v) {
        return v.getNeighbors();
    }

    boolean checkEdge(Tile v1, Tile v2) {
        return (v1.getNeighbors().contains(v2));
    }

    void addEdge(Tile v1, Tile v2) {
        v1.addNeighbor(v2);
    }

    void addTile(Tile v) {
        tile.add(v);
    }


    public boolean isPassable(char tileType) {
        return tileType == 'G';
    }


    public int convert2d(int row, int col, int nCols) {
        return (row * nCols) + col;
    }

    public void initializeTiles(String filename) throws IOException {
        processor.readFile(filename);
        this.world_Chars = processor.getData();

        int nRows = world_Chars.length;
        int nCols = world_Chars[0].length;

        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                tile.add(new Tile(world_Chars[row][col], 77, 1100, 10000));
            }
        }

        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {

                if (isPassable(world_Chars[row][col])) {
                    int curTileIndex = convert2d(row, col, nCols);

                    if (row > 0 && isPassable(world_Chars[row - 1][col])) {
                        tile.get(curTileIndex).addNeighbor(tile.get(convert2d(row - 1, col, nCols)));
                    }

                    if (row < nRows - 1 && isPassable(world_Chars[row + 1][col])) {
                        tile.get(curTileIndex).addNeighbor(tile.get(convert2d(row + 1, col, nCols)));
                    }

                    if (col > 0 && isPassable(world_Chars[row][col - 1])) {
                        tile.get(curTileIndex).addNeighbor(tile.get(convert2d(row, col - 1, nCols)));
                    }

                    if (col < nCols - 1 && isPassable(world_Chars[row][col + 1])) {
                        tile.get(curTileIndex).addNeighbor(tile.get(convert2d(row, col + 1, nCols)));
                    }
                }
            }
        }
    }

    public void run(int nSteps) throws InterruptedException {

        for (int t = 0; t < nSteps; t++) {
            int[][] frameDatas = new int[tile.size()][2];
            int currIdx = 0;
            for (Tile tile : tile) {
                int[] frameData = tile.runOneYear();
                frameDatas[currIdx] = frameData;
                currIdx++;
                if (tile.predDead()) {
                    break;
                }
            }

            Frame frame = new Frame(world_Chars, frameDatas);
            frame.simulate();
            Thread.sleep(100);

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        World world = new World();
        world.initializeTiles("../src/world2.txt");
        world.run(8);
    }
}
