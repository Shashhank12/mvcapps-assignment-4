package prisonersDilemma;

import mvc.Utilities;

public abstract class Strategy {
    Prisoner myPrisoner;
    public abstract boolean cooperate();
}

class Cooperate extends Strategy {
    public boolean cooperate() {
        return true;
    }
}

class Cheat extends Strategy {
    public boolean cooperate() {
        return false;
    }
}

class Tit4Tat extends Strategy {
    public boolean cooperate() {
        return myPrisoner.cheated;
    }
}

class RandomlyCooperate extends Strategy {
    public boolean cooperate() {
        return Utilities.rng.nextDouble() < 0.5;
    }
}
