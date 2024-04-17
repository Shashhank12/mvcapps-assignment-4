package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Iterator;

public class SimulationPanel extends AppPanel implements ComponentListener {
    JButton startButton = new JButton("Start");
    JButton suspendButton = new JButton("Suspend");
    JButton resumeButton = new JButton("Resume");
    JButton stopButton = new JButton("Stop");
    JButton statsButton = new JButton("Stats");
    public SimulationPanel(AppFactory factory) {
        super(factory);
        JButton[] buttons = {startButton, suspendButton, resumeButton, stopButton, statsButton};
        JPanel p;
        controlPanel.setLayout(new GridLayout(5, 1));
        for (JButton button : buttons) {
            p = new JPanel();
            button.addActionListener(this);
            p.add(button);
            controlPanel.add(p);
        }
        this.view.addComponentListener(this);
    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Component component = e.getComponent();
        Rectangle window = component.getBounds();
        Simulation simulation = (Simulation) getModel();
        simulation.setWidth(window.width);
        simulation.setHeight(window.height);
    }

    @Override
    public void setModel(Model m) {
        super.setModel(m); // calling AppPanel.setModel(m)
        Simulation s = (Simulation)m;
        Iterator<Agent> it = s.iterator();
        while(it.hasNext()) {
            Thread t = new Thread(it.next());
            t.start(); // this will call Agent.run (see below)
        }
        componentResized(new ComponentEvent(this, ComponentEvent.COMPONENT_RESIZED));
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}
