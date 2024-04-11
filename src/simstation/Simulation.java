package simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.*;

public class Simulation extends Model {
    private static final long serialVersionUID = -9221800546606875714L;
    transient private Timer timer;
    private int clock = 0;
    Console console;
    List<Agent> agents;

    // 0: not started, 1: running, 2: suspended, 3: stopped
    private int state;
    public Simulation() {
        this.agents = new ArrayList<>();
        this.console = new Console();
        this.state = 0; // not started
    }
    public void addAgent(Agent a) {
        agents.add(a);
        a.setSimulation(this);
    }
    public void start() {
        clock = 0;
        populate();
        startTimer();
        for(Agent a : agents) {
            Thread thread = new Thread(a);
            thread.start();
        }
        this.state = 1; // running
    }

    public void suspend() {
        for(Agent a : agents) {
            a.suspend();
        }
        stopTimer();
        this.state = 2; // suspended
    }

    public void resume() {
        startTimer();
        for(Agent a : agents) {
            a.resume();
        }
        this.state = 1; // running
    }

    public synchronized void stop() {
        for(Agent a : agents) {
            a.stop();
        }
        stopTimer();
        this.state = 3; // stopped
    }
    public Agent getNeighbor(Agent agent, double radius) {
        int x1 = agent.xc;
        int y1 = agent.yc;
        Random random = new Random();
        int n = agents.size();
        int i = random.nextInt(n);
        int j = i;
        do {
            Agent newAgent = agents.get(j);
            if (newAgent == agent) {
                j = (j + 1) % n;
                continue;
            }
            int x2 = newAgent.xc;
            int y2 = newAgent.yc;
            double dis  = distance(x1, y1, x2, y2);
            if(dis<=radius) {
                return newAgent;
            }
            j = (j + 1) % n;
        } while (j != i);
        return null;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }

    public void populate() {
        //To be called from the subclass
    }

    public String[] getStats() {

        return new String[] {"Agents: " + agents.size(), "Clock: " + clock};
    }

    public synchronized void println(String msg) {
        console.stdout.println(msg);
    }
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }
    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public List<Agent> getAgents() {
        return agents;
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }
}
