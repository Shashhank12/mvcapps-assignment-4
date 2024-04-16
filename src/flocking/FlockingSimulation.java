package flocking;

import simstation.Agent;
import simstation.Simulation;

import java.util.*;

public class FlockingSimulation extends Simulation {
    private static final long serialVersionUID = 1898762915297837586L;

    public FlockingSimulation() {
        super();
    }

    @Override
    public void populate() {
        for(int i = 0; i < 50; i++) {
            addAgent(new Bird("Bird "+i));
        }
    }

    @Override
    public String[] getStats() {
        Map<Integer, Integer> hm = new LinkedHashMap<>();
        for(Agent a : agents) {
            Bird b = (Bird)a;
            int sp = b.getSpeed();
            int count = hm.getOrDefault(sp, 0);
            hm.put(sp, count + 1);
        }
        List<String> stats = new ArrayList<>();
        for(int key : hm.keySet()) {
            stats.add("#birds @ Speed" + key + "=" + hm.get(key));
        }
        return stats.toArray(new String[0]);
    }
}
