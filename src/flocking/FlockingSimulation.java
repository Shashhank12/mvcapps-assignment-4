package flocking;

import simstation.Simulation;

public class FlockingSimulation extends Simulation {
    private static final long serialVersionUID = 1898762915297837586L;

    @Override
    public void populate() {
        for(int i = 0; i < 100; i++) {
            addAgent(new Bird("Bird "+i));
        }
    }

    @Override
    public String[] getStats() {
        for(int i = 0; i < 100; i++) {
            //
        }
        return null;
    }
}
