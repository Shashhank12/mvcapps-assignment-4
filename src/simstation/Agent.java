package simstation;

import mvc.AppPanel;
import mvc.Utilities;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    private static final long serialVersionUID = -5715552370025859766L;
    String name;
    protected Heading heading;
    int xc;
    int yc;
    boolean suspended;
    boolean stopped;
    transient protected Thread myThread;
    protected Simulation world;

    public int getWidth() {
        if (world != null) {
            return ((Simulation) world).getWidth();
        }
        return AppPanel.FRAME_WIDTH;
    }

    public int getHeight() {
        if (world != null) {
            return ((Simulation) world).getHeight();
        }
        return AppPanel.FRAME_HEIGHT;
    }

    public Agent(String name) {
        this.name = name;
        this.suspended = false;
        this.stopped = false;
        this.myThread = null;
        this.heading = Heading.random();
        computeLocation();
    }

    public void computeLocation() {
        xc = Utilities.rng.nextInt((int)getWidth() + 1);
        yc = Utilities.rng.nextInt((int)getHeight() + 1);
    }
    
    public Agent() {
        this("Agent");
    }

    public void setSimulation(Simulation sim) {
        this.world = sim;
        computeLocation();
    }

    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }
    public String getName() { return name; }
    
    public synchronized void onStart() {}
    public synchronized void onInterrupted() {}
    public synchronized void onExit() {
}
    public synchronized void stop() { stopped = true; }
    public synchronized boolean isStopped() { return stopped; }
    public synchronized void suspend() {
        suspended = true;
    }
    public synchronized boolean isSuspended() {
        return suspended;
    }
    public synchronized void resume() {
        notify();
    }

    public synchronized void start() {
        onStart();
    }

    // wait for me to die
    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch (InterruptedException e) {
            world.println(e.getMessage());
        }
    }

    // wait for notification (i.e. resume) if I'm not stopped and I am suspended
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                onInterrupted();
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            world.println(e.getMessage());
        }
    }
    public void run() {
        myThread = Thread.currentThread();// catch my thread
//        onInterrupted();
        start();
        while(!isStopped()) {
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (Exception e) {
                e.printStackTrace(this.world.console.stdout);
            }
        }
        onExit();
        this.world.println(name + "stopped");
    }

    public abstract void update();

    public int getXc() {
        return xc;
    }

    public int getYc() {
        return yc;
    }

    public void move(int steps) {
        for(int i=0; i<steps; i++) {
            if (heading == Heading.NORTH)
            {
                int height = getHeight();
                this.yc = (this.yc + 1 + height) % height;
            }
            else if (heading == Heading.SOUTH) {
                int height = getHeight();
                this.yc = (this.yc - 1 + height) % height;
            }
            else if (heading == Heading.WEST) {
                int width = getWidth();
                this.xc = (this.xc - 1 + width) % width;
            }
            else {
                int width = getWidth();
                this.xc = (this.xc + 1 + width) % width;
            }
            world.changed();
        }
    }
}
