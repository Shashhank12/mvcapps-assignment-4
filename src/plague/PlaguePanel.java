package plague;

import mvc.AppFactory;
import simstation.SimulationPanel;

public class PlaguePanel extends SimulationPanel {
    public PlaguePanel(AppFactory factory) {
        super(factory);
    }

    public static void main(String[] args){
        AppFactory factory = new PlagueFactory();
        PlaguePanel panel = new PlaguePanel(factory);
        panel.display();
    }
}
