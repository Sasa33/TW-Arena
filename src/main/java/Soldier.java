import Debuff.Debuff;

public class Soldier extends Charactor {
    private String type = "战士";

    public Soldier(String name, int blood, int damage) {
        super.name = name;
        super.blood = blood;
        super.damage = damage;
//        super(name, blood, damage);
//        weapon = NoWeapon.getInstance();
//        armor = NoArmor.getInstance();
    }

//    public void equipWeapon(Weapon weapon) throws Exception {
//        if(weapon.getWeaponType().indexOf("短") != -1) {
//            throw new Exception("战士只能装备中武器！");
//        } else {
//            this.weapon = weapon;
//            this.damage += weapon.getWeaponDamage();
//        }
//    }
//
//    public void wearArmor(Debuff.Armor armor) {
//        this.armor = armor;
//    }

    public String getType() {
        return type;
    }

//    public String attack(Player victim) {
//        return String.format("%s攻击了%s，%s", getAttackInfo(), victim.getBeAttackedInfo(), victim.beAttacked(damage));
//    }
//
//    protected String beAttacked(int damage) {
//        int bleed = bleed(damage);
//        blood -= bleed;
//        return String.format("%s受到了%d点伤害，%s剩余生命：%d", name, bleed, name, blood);
//    }
//
//    protected String beAttacked(Debuff debuff) {
//        return "";
//    }

//    protected int bleed(int damage) {
//        return damage;
//    }
}
