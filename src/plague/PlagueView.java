package plague;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;

public class PlagueView extends SimulationView {
    public PlagueView(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation) model;
        for (Agent a : simulation.getAgents()) {
            if (a instanceof Plague && !a.isStopped()) {
                Plague p = (Plague) a;
                if (p.resistance) {
                    gc.setColor(Color.BLUE);
                    gc.fillRect(a.getXc(), a.getYc(), 5, 5);
                }
                else if (p.infected)
                {
                    gc.setColor(Color.RED);
                    gc.fillRect(a.getXc(), a.getYc(), 5, 5);
                }
                else {
                    gc.setColor(Color.GREEN);
                    gc.fillRect(a.getXc(), a.getYc(), 5, 5);
                }
            }
        }
    }
}
