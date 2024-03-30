import java.util.ArrayList;

public abstract class Animal {

    protected int age;

    protected int maximum_Age;

    protected int energy;

    protected int babyCost;

    public Animal(int maximum_Age, int babyCost) {
        this.maximum_Age = maximum_Age;
        this.babyCost = babyCost;
        this.age = 0;
        this.energy = 0;
    }


    public void incrAge() {
        age++;
    }
    public boolean tooOld() {
        return age >= maximum_Age;
    }
    public boolean canReproduce() {
        return energy >= babyCost;
    }


    public abstract Animal makeBaby();

    public void eat(ArrayList<Animal> preys) {

    }

    public Tile move(Tile tile){
        ArrayList<Tile> moves = tile.getNeighbors();
        if(moves.size() == 0){
            return tile;
        }
        int index = (int)(Math.random() * moves.size());
        return moves.get(index);
    }
}
