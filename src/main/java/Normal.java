public class Normal extends Player {
    private String type = "普通人";

    public Normal(String name, int blood, int damage) {
        super.name = name;
        super.blood = blood;
        super.damage = damage;
    }

    public int getBlood() {
        return blood;
    }

    public int getDamage() {
        return damage;
    }

    public String getArmorName() {
        return name;
    }

    public boolean isAlive() {
        return blood >= 0;
    }

    public String getType() {
        return type;
    }

    public String attack(Player victim) {
        if (debuff != null && (debuff.getDebuffName().equals("冰冻伤害") || debuff.getDebuffName().equals("击晕伤害")) && debuff.getDebuffRounds() > 0) {
            debuff.setDebuffRounds(debuff.getDebuffRounds() - 1);
            return "";
        } else {
            return String.format("%s攻击了%s，%s", getAttackInfo(), victim.getBeAttackedInfo(), victim.beAttacked(damage));
        }
    }

    protected int bleed(int damage) {
        return damage;
    }

}
