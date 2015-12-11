package Debuff;

public class PoisonDebuff extends Debuff {

    public PoisonDebuff(String debuffName, int debuffDamage) {
        super(debuffName, debuffDamage, 1);
    }

    public String debuff() {
        return String.format("%s", "中毒了");
    }

}
