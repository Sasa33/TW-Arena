package Debuff;

public class DebuffContext {

    private Debuff debuff = null;

    public DebuffContext(String type) {
        switch (type) {
            case "毒性伤害":
                debuff = new PoisonDebuff("毒性伤害", 2);
                break;
            case "火焰伤害":
                debuff = new FireDebuff("火焰伤害", 3);
                break;
            case "冰冻伤害":
                debuff = new FreezeDebuff("冰冻伤害");
                break;
            case "击晕伤害":
                debuff = new DizzyDebuff("击晕伤害");
                break;
            case "全力一击":
                debuff = new AllStrenghtDebuff("全力一击");
        }
    }

    public Debuff getDebuff() {
        return debuff;
    }
}