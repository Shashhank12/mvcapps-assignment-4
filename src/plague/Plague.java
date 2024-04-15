package plague;

import mvc.Utilities;
import simstation.Agent;

public class Plague extends Agent {
    boolean infected;
    boolean resistance;
    @Override
    public void update() {
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
        Plague other = (Plague) world.getNeighbor(this, 10);
        if (other != null && other.infected && !this.infected && !this.resistance) {
            int infected = Utilities.rng.nextInt(2);
            if (infected == 1)
            {
                int resisted = Utilities.rng.nextInt(100);
                this.infected = resisted != 0 && resisted != 1;
            }
        }
    }
}
