import java.util.ArrayList;

/**
 * Here is a bare-bones class that holds some relevant information for each tile in your world.
 * It is intended to be used as a starting point - depending on your design choices, you may decide
 * that you want different attributes, to add some more specific methods, or to scrap the whole thing.
 * The biggest reason that I am providing this file is for the "initializeTiles" function, which steps
 * through the reasonably straight-forward (but tedious) code for going from a 2D array of chars (such
 * as you may read from a file) and actually building objects in the same fashion as those from the
 * previous homework.
 */

public class Tile {
    Processor data;
    ArrayList<Animal> preds;
    ArrayList<Animal> preys;
    ArrayList<Animal> grasses;
    ArrayList<Tile> neighbors;
    char type;

    public Tile(char type, int predCount, int preyCount, int grassCount) {
        data = new Processor();
        this.type = type;
        neighbors = new ArrayList<>();
        // we'll need to populate our tiles somewhere! Sim?
        preds = new ArrayList<>();
        preys = new ArrayList<>();
        grasses = new ArrayList<>();
        for (int i = 0; i < predCount; i++) {
            preds.add(new Lynx());
        }
        for (int i = 0; i < preyCount; i++) {
            preys.add(new Hare());
        }
        for (int i = 0; i < grassCount; i++) {
            grasses.add(new Grass());
        }
    }

    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Tile tile) {
        this.neighbors.add(tile);
    }

    private void addPrey(Animal prey) {
        preys.add(prey);
    }

    private void addPred(Animal pred) {
        preds.add(pred);
    }

    private void movePreds() {
        ArrayList<Animal> predsMigrated = new ArrayList<>();
        for (Animal pred : preds) {
            Tile choice = pred.move(this);
            if (choice == this) {
                continue;
            }
            choice.addPred(pred);
            predsMigrated.add(pred);
        }
        preds.remove(predsMigrated);
    }

    private void movePreys() {
        ArrayList<Animal> preysMigrated = new ArrayList<>();
        for (Animal prey : preys) {
            Tile choice = prey.move(this);
            if (choice == this) {
                continue;
            }
            choice.addPrey(prey);
            preysMigrated.add(prey);
        }
        preys.remove(preysMigrated);
    }

    private void age(ArrayList<Animal> animals) {
        // go through all the animals, and kill them if they're too old
        ArrayList<Animal> dead = new ArrayList<>();
        for (Animal a : animals) {
            a.incrAge();
            if (a.tooOld()) {
                dead.add(a);
            }
        }
        animals.removeAll(dead);
    }

    private void reproduce(ArrayList<Animal> animals) {
        // go through all the animals, and make a baby if they have enough energy
        ArrayList<Animal> babies = new ArrayList<>();
        for (Animal a : animals) {
            if (a.canReproduce()) {
                babies.add(a.makeBaby());
            }
        }
        animals.addAll(babies);
    }

    // herbivore version of eating; since Animal has both methods we can up cast here, although in practice
    // we will only call this method with our Hares and never our Lynx
    private void graze(ArrayList<Animal> animals, ArrayList<Animal> grasses) {
        for (Animal a : animals) {
            a.eat(grasses);
        }
    }

    // carnivore version of eating; again, we can up cast since all Animals technically have both methods, but
    // we will only ask out Lynx to do this.
    // since Hare doesn't override this version, dynamic dispatch would call the (useless) Animal version for hares
    private void predate(ArrayList<Animal> predators, ArrayList<Animal> preys) {
        for (Animal a : predators) {
            a.eat(preys);
        }
    }

    // let's put it all together to execute one time step of our sim!
    private int[] step() {
        // first everyone eats
        graze(preys, grasses);
        predate(preds, preys);
        // then they age
        age(preds);
        age(preys);
        age(grasses);
        // then they experience the miracle of life
        reproduce(preds);
        reproduce(preys);
        reproduce(grasses);
        // move preys and preds
        movePreds();
        movePreys();
        // finally, let's track the populations
        data.addDataPoint(preds.size(), preys.size());
        data.printData();
        return new int[]{preds.size(), preys.size()};
    }

    public boolean predDead() {
        return preds.size() == 0;
    }

    public int[] runOneYear() {
        return step();

    }
}
