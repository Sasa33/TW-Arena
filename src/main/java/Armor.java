public class Armor extends EquipLoader {
    private final String armorName;
    private final int reducedDamage;

    public Armor(String name, int reducedDamage) {
        this.armorName = name;
        this.reducedDamage = reducedDamage;
    }

    public String getArmorName() {
        return armorName;
    }

    @Override
    public void setCharactor(Charactor charactor) throws Exception {
        super.setCharactor(charactor);
        getReducedDamage = reducedDamage;
    }
}
