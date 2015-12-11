import Debuff.Debuff;

public class Assassin extends Charactor {
    private String type = "刺客";

    public Assassin(String name, int blood, int damage) {
        super.name = name;
        super.blood = blood;
        super.damage = damage;
    }

    public String getType() {
        return type;
    }
}
