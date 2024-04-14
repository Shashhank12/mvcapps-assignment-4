package flocking;

import mvc.Model;
import simstation.SimStationFactory;

public class FlockingFactory extends SimStationFactory {

    public Model makeModel() {
        return new FlockingSimulation();
    }
    public String getTitle() {
        return "Flocking Simulator";
    }

}
