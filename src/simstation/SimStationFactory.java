package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimStationFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model model) {
        return new SimulationView((Simulation) model);
    }

    @Override
    public String getTitle() {
        return "SimStation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Click 'Start' to start the simulation.",
                "Click 'Suspend' to suspend the simulation.",
                "Click 'Resume' to resume the simulation.",
                "Click 'Stop' to stop the simulation.",
                "Click 'Stats' to view the statistics of the simulation."
        };
    }

    @Override
    public String about() {
        return "SimStation";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        Command command = null;
        if (type.equals("Start")) {
            command = new StartCommand(model);
        } else if (type.equals("Suspend")) {
            command = new SuspendCommand(model);
        } else if (type.equals("Resume")) {
            command = new ResumeCommand(model);
        } else if (type.equals("Stop")) {
            command = new StopCommand(model);
        } else if (type.equals("Stats")) {
            command = new StatsCommand(model);
        }
        return command;
    }
}
