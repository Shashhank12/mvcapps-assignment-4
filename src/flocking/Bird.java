package flocking;

import mvc.Utilities;
import simstation.Agent;

public class Bird extends Agent {
    private int speed;

    public Bird(String name) {
        super(name);
        this.speed = Utilities.rng.nextInt( 40) + 1;
    }

    @Override
    public void update() {
        Bird partner = (Bird)world.getNeighbor(this, 100);
        if (partner != null) {
            this.speed = partner.speed;
            this.heading = partner.heading;
        }
        move(speed);
    }
}
