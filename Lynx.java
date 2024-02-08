import java.util.ArrayList;

public class Lynx extends Animal {

    private double predationRate;

    public Lynx() {
        super(100, 150);
        this.predationRate = 0.0005;
    }
    public Lynx(int maxAge, int babyCost, double predationRate) {

        super(maxAge, babyCost);
        this.predationRate = predationRate;
    }

    public void eat(ArrayList<Animal> preys) {
        ArrayList<Animal> eaten = new ArrayList<>();

        for (Animal a: preys) {

            if (Math.random() < predationRate) {
                eaten.add(a);

                energy++;
            }
        }

        preys.removeAll(eaten);
    }
    public Lynx makeBaby() {
        this.energy -= this.babyCost;
        return new Lynx(this.maximum_Age, this.babyCost, this.predationRate);
    }
}