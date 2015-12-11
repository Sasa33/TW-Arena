public abstract class EquipLoader extends Charactor {
    protected Charactor charactor;

    public void setCharactor(Charactor charactor) throws Exception {
        this.charactor = charactor;
        name = charactor.getName();
        blood = charactor.getBlood();
        damage = charactor.getDamage();
        debuff = charactor.getDebuff();
        getReducedDamage = charactor.getReducedDamage;
    }

    public String getType() {
        if (charactor != null) {
            return charactor.getType();
        } else {
            return "";
        }
    }
}
