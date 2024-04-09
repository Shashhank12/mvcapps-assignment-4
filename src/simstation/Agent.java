package simstation;

import mvc.Utilities;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    String name;

    public double heading;
    int xc, yc;
    boolean suspended = false;
    boolean stopped = false;
    Thread myThread;
    Simulation world;

    public void run() {
        update();
    }

    public void start() {}

    public void suspend() {}

    public void resume() {}

    public void stop() {}

    public abstract void update();

    public void move(int steps) {
        for (int i = 0; i < steps; i++) {
            world.changed();
        }
    }
}
