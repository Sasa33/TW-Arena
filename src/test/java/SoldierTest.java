import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SoldierTest {
    private Armor armor;
    private Weapon weapon;
    private Soldier soldier1;
    private Soldier soldier2;
    private Player person;


    @Before
    public void setUp() {
        soldier1 = new Soldier("张三", 100, 10);
        soldier2 = new Soldier("李四", 100, 20);
        person = new Normal("李四", 100, 10);

        armor = new Armor("金丝软甲", 5);
        weapon = new Weapon("屠龙刀", 10, "中");
    }

    @Test
    public void canSoldierAttackPersonWithoutAnyWeaponOrArmor() {
        assertThat(soldier1.attack(person), is("战士张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：90"));
    }

    @Test
    public void shouldPrintSoldierUseWeaponToAttackPersonWhenSoldierEquipWeapon() throws Exception{
        weapon.setCharactor(soldier1);

        assertThat(weapon.attack(person), is("战士张三用屠龙刀攻击了普通人李四，李四受到了20点伤害，李四剩余生命：80"));
    }

    @Test
    public void canSoldierAttackSoldierWithoutAnyWeaponOrArmor() {
        assertThat(soldier1.attack(soldier2), is("战士张三攻击了战士李四，李四受到了10点伤害，李四剩余生命：90"));
    }

    @Test
    public void shouldPrintSoldierUseWeaponToAttackSoldierWhenSoldierEquipWeapon() throws Exception{
        weapon.setCharactor(soldier1);

        assertThat(weapon.attack(soldier2), is("战士张三用屠龙刀攻击了战士李四，李四受到了20点伤害，李四剩余生命：80"));
    }

    @Test
    public void shouldThrowsExceptionWhenSoldierEquipShortWeapon(){
        Weapon weapon = new Weapon("峨嵋刺", 4, "短");

        try {
            weapon.setCharactor(soldier1);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("玩家职业和该武器不匹配！"));
        }

    }

    @Test
    public void shouldThrowsExceptionWhenSoldierEquipLongWeapon() {
        Weapon weapon = new Weapon("长枪", 5, "长");

        try {
            weapon.setCharactor(soldier1);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("玩家职业和该武器不匹配！"));
        }
    }

    @Test
    public void shouldDebuffNotTrigerWhenSoldierUsingALongWeapon ()
    {

    }
}
