package Debuff;

public abstract class Debuff {
    protected String debuffName;
    protected int debuffDamage;
    protected int debuffRounds;

    public Debuff(String debuffName, int debuffDamage, int debuffRounds) {
        this.debuffName = debuffName;
        this.debuffDamage = debuffDamage;
        this.debuffRounds = debuffRounds;
    }

    public String getDebuffName() {
        return debuffName;
    }

    public int getDebuffDamage() {
        return debuffDamage;
    }

    public int getDebuffRounds() {
        return debuffRounds;
    }

    public void setDebuffRounds(int debuffRounds) {
        this.debuffRounds = debuffRounds;
    }

    public abstract String debuff();

}
