package simstation;

import mvc.*;

import java.awt.*;

public class SimulationView extends View {
    public SimulationView(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation simulation = (Simulation) model;
        for (Agent agent : simulation.getAgents()) {
            if (!agent.isStopped()) {
                gc.setColor(Color.WHITE);
                gc.fillRect(agent.getXc(), agent.getYc(), 5, 5);
            }
        }
    }
}
