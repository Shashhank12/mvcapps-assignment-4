package simstation;

import mvc.Utilities;

public class Heading {
    public static double random() {
        return Utilities.rng.nextDouble() * 360;
    }
}
