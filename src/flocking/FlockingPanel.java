package flocking;
import mvc.AppFactory;
import simstation.Simulation;
import simstation.SimulationPanel;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class FlockingPanel extends SimulationPanel implements ComponentListener {
    public FlockingPanel(AppFactory factory) {
        super(factory);
        this.view.addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Component component = e.getComponent();
        Rectangle window = component.getBounds();
        FlockingSimulation simulation = (FlockingSimulation) getModel();
        simulation.setWidth(window.width);
        simulation.setHeight(window.height);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    public static void main(String[] args) {
        AppFactory factory = new FlockingFactory();
        FlockingPanel panel = new FlockingPanel(factory);
        panel.display();
    }
}
