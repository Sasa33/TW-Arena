package Debuff;

public class DizzyDebuff extends Debuff {

    public DizzyDebuff(String debuffName) {
        super(debuffName, 0, 2);
    }

    public String debuff() {
        return String.format("%s", "晕倒了");
    }
}
