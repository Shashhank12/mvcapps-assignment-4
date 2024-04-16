package prisonersDilemma;

import mvc.Model;
import simstation.SimStationFactory;

public class PrisonerFactory extends SimStationFactory {
    public PrisonerFactory() {
        super();
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma";
    }

    @Override
    public Model makeModel() {
        return new PrisonerSimulation();
    }
}
