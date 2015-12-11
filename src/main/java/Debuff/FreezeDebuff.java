package Debuff;

public class FreezeDebuff extends Debuff {

    public FreezeDebuff(String debuffName) {
        super(debuffName, 0, 1);
    }

    public String debuff() {
        return String.format("%s", "冻僵了");
    }

}
