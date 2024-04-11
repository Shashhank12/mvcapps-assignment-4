package prisonersDilemma;

import mvc.Utilities;
import simstation.*;

public class Prisoner extends Agent {
    int fitness = 0;
    boolean partnerCheated;

    Strategy strategy;

    public boolean cooperate() {
        return strategy.cooperate();
    }

    @Override
    public void update() {
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
        Prisoner other = (Prisoner) world.getNeighbor(this, 10);
        if (other != null) {
            boolean myCooperation = cooperate();
            boolean otherCooperation = other.cooperate();
            if (myCooperation && otherCooperation) {
                updateFitness(3);
                other.updateFitness(3);
            } else if (myCooperation && !otherCooperation) {
                updateFitness(0);
                other.updateFitness(5);
            } else if (!myCooperation && otherCooperation) {
                updateFitness(5);
                other.updateFitness(0);
            } else {
                updateFitness(1);
                other.updateFitness(1);
            }
            partnerCheated = otherCooperation;
        }
    }

    public void updateFitness(int amt) {
        fitness += amt;
    }

}
