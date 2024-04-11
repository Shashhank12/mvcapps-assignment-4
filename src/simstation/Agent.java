package simstation;

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

    public Agent(String name) {
        this.name = name;
        this.suspended = false;
        this.stopped = false;
        this.myThread = null;
        this.heading = Heading.random();
        xc = Utilities.rng.nextInt(400) + 1;
        yc = Utilities.rng.nextInt(400) + 1;
    }

    public Agent() {
        this("Agent");
    }

    public void setSimulation(Simulation sim) {
        this.world = sim;
    }

    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }
    public String getName() { return name; }
    public synchronized void onStart() {
    }
    public synchronized void onInterrupted() {
    }
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
                this.world.println(e.getMessage());
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
                this.yc = (this.yc + 1) % 250;
            }
            else if (heading == Heading.SOUTH) {
                this.yc = (this.yc - 1) % 250;
            }
            else if (heading == Heading.WEST) {
                this.xc = (this.xc - 1) % 250;
            }
            else {
                this.xc = (this.xc + 1) % 250;
            }
            world.changed();
        }
    }
}
