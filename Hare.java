import java.util.ArrayList;

public class Hare extends Animal {
    private double predationRte;
    public Hare() {

        super(150, 20);
        this.predationRte = 0.005;
    }
    public Hare(int maxAge, int babyCost) {

        super(maxAge, babyCost);
    }

    public void eat(ArrayList<Animal> grasses) {
        ArrayList<Animal> eaten = new ArrayList<>();

        for (Animal a: grasses) {

            if (Math.random() < predationRte) {
                eaten.add(a);

                energy+=10;
            }
        }

        grasses.removeAll(eaten);
    }
    public Hare makeBaby() {
        this.energy -= this.babyCost;
        return new Hare(this.maximum_Age, this.babyCost);
    }
}