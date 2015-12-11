public abstract class Charactor extends Player {

    protected int getReducedDamage = 0;


    public String attack(Player victim) {
        if (debuff != null && (debuff.getDebuffName().equals("冰冻伤害") || debuff.getDebuffName().equals("晕倒了")) && debuff.getDebuffRounds() > 0) {
            return "";
        } else {
            return String.format("%s攻击了%s，%s", getAttackInfo(), victim.getBeAttackedInfo(), victim.beAttacked(damage));
        }
    }


    protected int bleed(int damage) {
        return (damage >= getReducedDamage) ? (damage - getReducedDamage) : 0;
    }

}
