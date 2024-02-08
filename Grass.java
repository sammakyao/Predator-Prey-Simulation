public class Grass extends Animal{

    public Grass() {
        super(150, 30);

    }

    @Override
    public Animal makeBaby() {
        this.energy -= this.babyCost;
        return new Grass();
    }
}