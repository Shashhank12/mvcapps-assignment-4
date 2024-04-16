package flocking;
import mvc.AppFactory;
// import simstation.Simulation;
import simstation.SimulationPanel;

// import java.awt.*;
// import java.awt.event.ComponentEvent;
// import java.awt.event.ComponentListener;

public class FlockingPanel extends SimulationPanel {
    public FlockingPanel(AppFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        AppFactory factory = new FlockingFactory();
        FlockingPanel panel = new FlockingPanel(factory);
        panel.display();
    }
}
