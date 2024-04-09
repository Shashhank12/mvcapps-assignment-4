package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends AppPanel {
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

    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
