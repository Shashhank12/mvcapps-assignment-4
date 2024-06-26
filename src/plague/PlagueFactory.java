package plague;

import mvc.Model;
import mvc.View;
import simstation.SimStationFactory;

public class PlagueFactory extends SimStationFactory {
    public PlagueFactory() {
        super();
    }

    @Override
    public String getTitle() {
        return "Plague";
    }

    @Override
    public Model makeModel() {
        return new PlagueSimulation();
    }

    @Override
    public View makeView(Model model) {
        return new PlagueView((PlagueSimulation) model);
    }
}
