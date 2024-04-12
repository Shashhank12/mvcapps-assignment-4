package plague;

import mvc.Utilities;
import simstation.Agent;
import simstation.Simulation;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50;
    public static int RESISTANCE = 2;

    @Override
    public void populate() {
        for (int i = 0; i < 20; i++) {
            Plague p = new Plague();
            int infected = Utilities.rng.nextInt(2);
            if (infected == 1)
            {
                int resisted = Utilities.rng.nextInt(100);
                p.infected = resisted != 0 && resisted != 1;
            }
            else {
                p.infected = false;
            }
            addAgent(p);
        }
    }

    public int getInfectedAmount()
    {
        int infectedAmount = 0;
        for (Agent a: getAgents())
        {
            if (a instanceof Plague p) {
                if (p.infected) {
                    infectedAmount++;
                }
            }
        }
        return infectedAmount;
    }

    @Override
    public String[] getStats()
    {
        float infectedPercent = (float) getInfectedAmount() / getAgents().size();
        return new String[] {"Agents: " + getAgents().size(),"Clock: " + clock, "% Infected: " + infectedPercent};
    }
}
