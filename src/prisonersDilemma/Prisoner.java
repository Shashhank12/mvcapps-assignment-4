package prisonersDilemma;

import simstation.*;

public class Prisoner extends Agent {
    int fitness = 0;
    boolean cheated;

    Strategy strategy;

    public boolean cooperate() {
        return strategy.cooperate();
    }

    @Override
    public void update() {}

    public void updateFitness(int amt) {}

}
