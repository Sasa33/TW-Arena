import Debuff.Debuff;

public class DamageWithDebuff {
    private final int damageValue;
    private final Debuff debuff;
    private String info = "";

    public DamageWithDebuff(int damageValue, Debuff debuff) {
        this.damageValue = damageValue;
        this.debuff = debuff;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public void setInfo(String attackerName) {
        if(this.debuff != null && this.debuff.getDebuffName().equals("全力一击")){
            this.info = attackerName + "发动了全力一击";
        } else {
            this.info = "";
        }
    }

    public String getInfo() {
        return this.info;
    }
}
