/*

`Inspired from Professor Ben's Code, I have decided to use his code for my simulation 2.0, because
 although my simulation worked for the previous simulation, I didn't add and know how to add the predation rate
 or what happens when there are no prey left in my previous simulation, which is what is expected in the real world.
 Part of Ben's code that I liked was the organization, in my previous sim I only had animal class and respective
 Hare and Lynx which would make it easier for me to twist the predation rate, eat methods.

 */


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