package flocking;

import mvc.Utilities;
import simstation.Agent;

public class Bird extends Agent {
    private static final long serialVersionUID = 2828987289687826798L;
    private int speed;

    public Bird(String name) {
        super(name);
        this.speed = Utilities.rng.nextInt(10) + 1;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getWidth() {
        if (world != null) {
            return ((FlockingSimulation) world).getWidth();
        }
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        if (world != null) {
            return ((FlockingSimulation) world).getHeight();
        }
        return super.getHeight();
    }

    @Override
    public void update() {
        Bird partner = (Bird)world.getNeighbor(this, 50);
        if (partner != null) {
            this.speed = partner.speed;
            this.heading = partner.heading;
        }
        move(speed);
    }
}
