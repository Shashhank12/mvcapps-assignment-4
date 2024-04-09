package simstation;

import mvc.*;

public class SimulationView extends View {
    public SimulationView(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void update() {
        Simulation simulation = (Simulation) model;
        repaint();
    }
}
