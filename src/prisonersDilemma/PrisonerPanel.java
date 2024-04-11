package prisonersDilemma;

import mvc.AppFactory;
import simstation.SimulationPanel;

public class PrisonerPanel extends SimulationPanel {
    public PrisonerPanel(AppFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        AppFactory factory = new PrisonerFactory();
        PrisonerPanel panel = new PrisonerPanel(factory);
        panel.display();
    }

}
