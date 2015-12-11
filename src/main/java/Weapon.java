import Debuff.Debuff;
import Debuff.NoDebuff;

import java.util.Random;

public class Weapon extends EquipLoader {
    private final String weaponName;
    private final int weaponDamage;
    private String weaponType;
    private Debuff weaponDebuff;
    private int triggerThreshold;
    private Random random;

    public Weapon(String name, int damage, String type) {
        this.weaponName = name;
        this.weaponDamage = damage;
        this.weaponType = type;
        this.weaponDebuff = null;
        this.triggerThreshold = 100;
        this.charactor = null;
        this.random = null;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public String getWeaponType() {
        return weaponType;
    }

    @Override
    public void setCharactor(Charactor charactor) throws Exception {
        if (isWeaponSuitable(charactor)) {
            super.setCharactor(charactor);
        } else {
            throw new Exception("玩家职业和该武器不匹配！");
        }
    }

    public void setWeaponDebuff(Debuff debuff, int threshold) {
        this.weaponDebuff = debuff;
        this.triggerThreshold = threshold;
    }

    public void setWeaponDebuff(Debuff debuff, int threshold, Random random) {
        this.weaponDebuff = debuff;
        this.triggerThreshold = threshold;
        this.random = random;
    }

    @Override
    public String getAttackInfo() {
        return String.format("%s用%s", super.getAttackInfo(), getWeaponName());
    }

    public String attack(Player victim) {
        if (charactor.debuff != null && (charactor.debuff.getDebuffName().equals("冰冻伤害") || charactor.debuff.getDebuffName().equals("击晕伤害")) && charactor.debuff.getDebuffRounds() > 0) {
            charactor.debuff.setDebuffRounds(charactor.debuff.getDebuffRounds() - 1);
            return "";
        } else {
            if (charactor != null) {
                if (this.random == null) {
                    random = new Random();
                }
                int damageValue = charactor.getDamage() + weaponDamage;
                if (weaponDebuff != null && triggerDebuff(random) && isDebuffSuitable(charactor)) { // damage: player + weapon + debuff
                    DamageWithDebuff damageWithDebuff = new DamageWithDebuff(damageValue, this.weaponDebuff);
                    damageWithDebuff.setInfo(charactor.name);
                    return String.format("%s攻击了%s，%s", getAttackInfo(), victim.getBeAttackedInfo(), victim.beAttacked(damageWithDebuff));
                } else if(victim.debuff != null) {
                    DamageWithDebuff damageWithDebuff = new DamageWithDebuff(damageValue, NoDebuff.getInstance());
                    return String.format("%s攻击了%s，%s", getAttackInfo(), victim.getBeAttackedInfo(), victim.beAttacked(damageWithDebuff));
                } else { // damage: player + weapon
                    return String.format("%s攻击了%s，%s", getAttackInfo(), victim.getBeAttackedInfo(), victim.beAttacked(damageValue));
                }
            } else { // damage: player
                return charactor.attack(victim);
            }
        }
    }

    public boolean triggerDebuff(Random random) {
        if (((random.nextInt(99) + 1)) >= triggerThreshold) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWeaponSuitable(Charactor charactor) {
        if ((charactor.getType() == "战士" && this.weaponType == "中") || (charactor.getType() == "刺客" && (this.weaponType == "中" || this.weaponType == "短")) || (charactor.getType() == "骑士" && (this.weaponType == "中" || this.weaponType == "长"))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDebuffSuitable(Charactor charactor) {
        if ((charactor.getType() == "战士" && this.weaponType == "中") || (charactor.getType() == "刺客" && this.weaponType == "短") || (charactor.getType() == "骑士" && this.weaponType == "长")) {
            return true;
        } else {
            return false;
        }
    }
}
