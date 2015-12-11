package Debuff;

public class NoDebuff extends Debuff {
    private static NoDebuff INSTANCE = new NoDebuff("", 0, 0);

    private NoDebuff(String debuffName, int debuffDamage, int debuffRounds) {
        super(debuffName, debuffDamage, debuffRounds);
    }

    public static NoDebuff getInstance() {
        return INSTANCE;
    }

    public String debuff() {
        return "";
    }
}
