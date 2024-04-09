package prisonersDilemma;

public abstract class Strategy {
    Prisoner myPrisoner;
    public abstract void cooperate();
}

class Cooperate extends Strategy {
    public void cooperate() {}
}

class Cheat extends Strategy {
    public void cooperate() {}
}

class Tit4Tat extends Strategy {
    public void cooperate() {}
}

class RandomlyCooperate extends Strategy {
    public void cooperate() {}
}
