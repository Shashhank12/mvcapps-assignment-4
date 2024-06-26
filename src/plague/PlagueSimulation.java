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
            int infected = Utilities.rng.nextInt(2); // 50% chance to be infected based on Virulence
            if (infected == 1)
            {
                int resisted = Utilities.rng.nextInt(100); // 2% chance to resist based on Resistance
                if (resisted == 0 || resisted == 1)
                {
                    p.infected = false;
                    p.resistance = true;
                }
            }
            else {
                p.infected = true;
                p.resistance = false;
            }
            addAgent(p);
        }
    }

    public int getInfectedAmount()
    {
        int infectedAmount = 0;
        for (Agent a: getAgents())
        {
            if (a instanceof Plague) {
                Plague p = (Plague) a;
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
        float infectedPercent = ((float) getInfectedAmount() / getAgents().size()) * 100;
        return new String[] {"Agents: " + getAgents().size(),"Clock: " + clock, "% Infected: " + String.format("%.2f", infectedPercent)};
    }
}
