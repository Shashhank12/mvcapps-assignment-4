package simstation;

import mvc.*;
import java.util.*;

public class Simulation extends Model {
    transient private Timer timer;
    private int clock = 0;

    List<Agent> agents;

    private  void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() { clock++; }
    }
    public void start() {
        for (Agent a : agents) {
            a.start();
        }
    }

    public void suspend() {
        for (Agent a : agents) {
            a.suspend();
        }
    }

    public void resume() {
        for (Agent a : agents) {
            a.resume();
        }
    }

    public void stop() {
        for (Agent a : agents) {
            a.stop();
        }
    }

    public String[] getStats() {
        return null;
    }

    public void addAgent(Agent a) {
        agents.add(a);
    }

    public Agent getNeighbor(Agent a, double radius) { return null; }

    public void populate() {}

    public void getClock() {}
}