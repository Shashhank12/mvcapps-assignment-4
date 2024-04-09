package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {

    protected AppFactory factory;
    protected Model model;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;
    public AppPanel(AppFactory factory) {
        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView(model);
        view.setBackground(Color.GRAY);
        this.controlPanel = new JPanel();
        controlPanel.setBackground(Color.PINK);
        this.add(controlPanel);
        setLayout(new GridLayout(1, 2));
        this.add(view);
        if (model != null) {
            model.subscribe(this);
        }

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() {
        frame.setVisible(true);
    }
    
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu = Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        this.model.subscribe(this);
        view.setModel(this.model);
        model.changed();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();

        if (cmmd.equals("Save")) {
            Utilities.save(model, false);
        } else if (cmmd.equals("SaveAs")) {
            Utilities.save(model, true);
        } else if (cmmd.equals("Open")) {
            Model newModel = Utilities.open(model);
            if (newModel != null) setModel(newModel);
        } else if (cmmd.equals("New")) {
            Utilities.saveChanges(model);
            setModel(factory.makeModel());
            model.setUnsavedChanges(false);
        } else if (cmmd.equals("Quit")) {
            Utilities.saveChanges(model);
            System.exit(1);
        } else if (cmmd.equals("About")) {
            Utilities.inform(factory.about());
        } else if (cmmd.equals("Help")) {
            Utilities.inform(factory.getHelp());
        } else {
            Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
            try {
                command.execute();
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    protected  void handleException(Exception e) {
        Utilities.error(e);
    }
    @Override
    public void update() {/* no op, override if needed*/}
}