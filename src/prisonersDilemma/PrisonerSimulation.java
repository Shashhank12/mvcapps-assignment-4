package prisonersDilemma;

import simstation.Simulation;

public class PrisonerSimulation extends Simulation {
    public PrisonerSimulation() {
        super();
    }

    @Override
    public void populate() {
        Strategy[] strategies = {new Cooperate(), new Cheat(), new Tit4Tat(), new RandomlyCooperate()};
        for (int i = 0; i < 20; i++) {
            Prisoner p = new Prisoner();
            p.strategy = strategies[i%4];
            addAgent(p);
        }
    }
}
