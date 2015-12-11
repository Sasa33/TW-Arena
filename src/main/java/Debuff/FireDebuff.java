package Debuff;

public class FireDebuff extends Debuff {

    public FireDebuff(String debuffName, int debuffDamage) {
        super(debuffName, debuffDamage, 1);
    }

    public String debuff() {
        return String.format("%s", "烫伤了");
    }
}
