package prisonersDilemma;

import simstation.Agent;
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

    @Override
    public String[] getStats() {
        int[][] meanFitness = new int[4][2];

        for (Agent a : getAgents()) {
            if (a instanceof Prisoner) {
                Prisoner p = (Prisoner) a;
                if (p.strategy instanceof Cooperate) {
                    meanFitness[0][0] += p.fitness;
                    meanFitness[0][1]++;
                } else if (p.strategy instanceof Cheat) {
                    meanFitness[1][0] += p.fitness;
                    meanFitness[1][1]++;
                } else if (p.strategy instanceof Tit4Tat) {
                    meanFitness[2][0] += p.fitness;
                    meanFitness[2][1]++;
                } else if (p.strategy instanceof RandomlyCooperate) {
                    meanFitness[3][0] += p.fitness;
                    meanFitness[3][1]++;
                }
            }
        }

        String[] stats = new String[4];
        stats[0] = "Cooperate Average: " + meanFitness[0][0] / meanFitness[0][1];
        stats[1] = "Cheat Average: " + meanFitness[1][0] / meanFitness[1][1];
        stats[2] = "Tit4Tat Average: " + meanFitness[2][0] / meanFitness[2][1];
        stats[3] = "RandomlyCooperate Average: " + meanFitness[3][0] / meanFitness[3][1];

        return stats;
    }
}
