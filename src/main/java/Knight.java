import Debuff.Debuff;

public class Knight extends Charactor {
    private String type = "骑士";

    public Knight(String name, int blood, int damage) {
        super.name = name;
        super.blood = blood;
        super.damage = damage;
    }

    public String getType() {
        return type;
    }
}
