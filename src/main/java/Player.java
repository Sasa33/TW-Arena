import Debuff.Debuff;
import Debuff.DebuffContext;

public abstract class Player {

    protected String name;
    protected int blood;
    protected int damage;
    protected Debuff debuff = null;

    public int getBlood() {
        return blood;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public boolean isAlive() {
        return blood >= 0;
    }

    public abstract String getType();

    public String getAttackInfo() {
        return String.format("%s%s", getType(), getName());
    }

    public String getBeAttackedInfo() {
        return String.format("%s%s", getType(), getName());
    }

    public abstract String attack(Player victim);


    public void setDebuff(Debuff debuff) {
        if (this.debuff == null && debuff != null){
            DebuffContext debuffContext = new DebuffContext(debuff.getDebuffName());
            this.debuff = debuffContext.getDebuff();
        } else if (debuff.getDebuffName().equals(this.debuff.getDebuffName())) {
            this.debuff.setDebuffRounds(this.debuff.getDebuffRounds() + debuff.getDebuffRounds());
        } else;
    }

    protected void clearDebuff() {
        this.debuff = null;
    }

    protected abstract int bleed(int damage);

    protected String beAttacked(int damage) {
        int bleed = bleed(damage);
        blood -= bleed;
        return String.format("%s受到了%d点伤害，%s剩余生命：%d", name, bleed, name, blood);
    }

    protected String beAttacked(DamageWithDebuff damageWithDebuff) {
        setDebuff(damageWithDebuff.getDebuff());

        String result = "";
        String resultOfDebuff = "";

        if (debuff.getDebuffName().equals("全力一击")) {
            int bleed = bleed(damageWithDebuff.getDamageValue() * 3);
            blood -= bleed;
            result = String.format("%s，%s受到了%d点伤害，%s剩余生命：%d", damageWithDebuff.getInfo(), name, bleed, name, blood);
        } else if (debuff.getDebuffName().equals("毒性伤害") || debuff.getDebuffName().equals("火焰伤害")) {
            int bleed = bleed(damageWithDebuff.getDamageValue());
            blood -= bleed;
            result = String.format("%s受到了%d点伤害，%s%s，%s剩余生命：%d\n", name, bleed, name, debuff.debuff(), name, blood);
            blood -= debuff.getDebuffDamage();
            resultOfDebuff = String.format("%s受到%d点%s，%s剩余生命：%d", name, debuff.getDebuffDamage(), debuff.getDebuffName(), name, blood);
            result += resultOfDebuff;
        } else if (debuff.getDebuffName().equals("冰冻伤害") || debuff.getDebuffName().equals("击晕伤害")) {
            if (debuff.getDebuffRounds() > 0) {
                int bleed = bleed(damageWithDebuff.getDamageValue());
                blood -= bleed;
                result = String.format("%s受到了%d点伤害，%s%s，%s剩余生命：%d\n", name, bleed, name, debuff.debuff(), name, blood);

                if (debuff.getDebuffName().equals("冰冻伤害")) {
                    resultOfDebuff = String.format("%s%s，无法攻击", name, debuff.debuff());
                } else {
                    resultOfDebuff = String.format("%s%s，无法攻击，眩晕还剩：%d轮", name, debuff.debuff(), debuff.getDebuffRounds() - 1);
                }
                result += resultOfDebuff;
            } else {
                clearDebuff();
                result = beAttacked(damageWithDebuff.getDamageValue());
            }
        } else {
            result = beAttacked(damageWithDebuff.getDamageValue());
        }
        return result;
    }
}
