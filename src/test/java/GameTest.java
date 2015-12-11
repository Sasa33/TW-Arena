import Debuff.DebuffContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.Random;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class GameTest {
    private Game game;
    private ConsolePrinter consolePrinter;
    InOrder inOrder;


    @Before
    public void setUp() {
        consolePrinter = mock(ConsolePrinter.class);

        inOrder = inOrder(consolePrinter);

        game = new Game(consolePrinter);

    }

    @Test
    public void shouldTwoNormalPlayersFightWithEachOther() {
        Player normal1 = new Normal("张三", 10, 8);
        Player normal2 = new Normal("李四", 20, 9);

        game.fight(normal1, normal2);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了8点伤害，李四剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了普通人张三，张三受到了9点伤害，张三剩余生命：1\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了普通人李四，李四受到了8点伤害，李四剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了普通人张三，张三受到了9点伤害，张三剩余生命：-8\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldSoldierWinWhenSoldierIsMorePowerfulThanNormalPlayer() {
        Player normal = new Normal("张三", 9, 5);
        Player soldier = new Soldier("李四", 10, 10);

        game.fight(normal, soldier);

        inOrder.verify(consolePrinter, times(1)).print("普通人张三攻击了战士李四，李四受到了5点伤害，李四剩余生命：5\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四攻击了普通人张三，张三受到了10点伤害，张三剩余生命：-1\n"); // -1 should be English input
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldTwoSoldiersFightWithEachOther() {
        Player soldier1 = new Soldier("张三", 9, 5);
        Player soldier2 = new Soldier("李四", 10, 10);

        game.fight(soldier1, soldier2);

        inOrder.verify(consolePrinter, times(1)).print("战士张三攻击了战士李四，李四受到了5点伤害，李四剩余生命：5\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四攻击了战士张三，张三受到了10点伤害，张三剩余生命：-1\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldSoldierFightWithOtherPlayerUsingWeapon() {
        Player soldier1 = new Soldier("张三", 9, 5);
        Player soldier2 = new Normal("李四", 10, 10);

        game.fight(soldier1, soldier2);

        inOrder.verify(consolePrinter, times(1)).print("战士张三攻击了普通人李四，李四受到了5点伤害，李四剩余生命：5\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了战士张三，张三受到了10点伤害，张三剩余生命：-1\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldSoldierFightWithSoldierBothUsingWeapon() throws Exception {
        Soldier soldier1 = new Soldier("张三", 15, 5);
        Soldier soldier2 = new Soldier("李四", 20, 10);

        Weapon weapon1 = new Weapon("屠龙刀", 10, "中");
        Weapon weapon2 = new Weapon("倚天剑", 9, "中");
        weapon1.setCharactor(soldier1);
        weapon2.setCharactor(soldier2);

        game.fight(weapon1, weapon2);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用屠龙刀攻击了战士李四，李四受到了15点伤害，李四剩余生命：5\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四用倚天剑攻击了战士张三，张三受到了19点伤害，张三剩余生命：-4\n");
        inOrder.verify(consolePrinter, times(1)).print("张三被打败了");
    }

    @Test
    public void shouldSoldierFightWithSoldierBothUsingWeaponAndArmor() throws Exception {
        Soldier soldier1 = new Soldier("张三", 15, 5);
        Soldier soldier2 = new Soldier("李四", 20, 10);

        Weapon weapon1 = new Weapon("屠龙刀", 10, "中");
        Weapon weapon2 = new Weapon("倚天剑", 9, "中");
        Armor armor1 = new Armor("金丝软甲", 8);
        Armor armor2 = new Armor("普通软甲", 4);

        armor1.setCharactor(soldier1);
        weapon1.setCharactor(armor1);
        armor2.setCharactor(soldier2);
        weapon2.setCharactor(armor2);

        game.fight(weapon1, weapon2);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用屠龙刀攻击了战士李四，李四受到了11点伤害，李四剩余生命：9\n");
        inOrder.verify(consolePrinter, times(1)).print("战士李四用倚天剑攻击了战士张三，张三受到了11点伤害，张三剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用屠龙刀攻击了战士李四，李四受到了11点伤害，李四剩余生命：-2\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldSoldierFightWithPersonUsingWeaponWithToxicEffect() throws Exception {
        Soldier soldier = new Soldier("张三", 20, 4);
        Player person = new Normal("李四", 18, 8);


        Weapon weapon1 = new Weapon("优质毒剑", 4, "中");
        DebuffContext debuffContext = new DebuffContext("毒性伤害");

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(84);

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 80, random);
        weapon1.setCharactor(soldier);

        game.fight(weapon1, person);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用优质毒剑攻击了普通人李四，李四受到了8点伤害，李四中毒了，李四剩余生命：10\n" +
                "李四受到2点毒性伤害，李四剩余生命：8\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了战士张三，张三受到了8点伤害，张三剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用优质毒剑攻击了普通人李四，李四受到了8点伤害，李四中毒了，李四剩余生命：0\n" +
                "李四受到2点毒性伤害，李四剩余生命：-2\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldSoldierFightWithPersonUsingWeaponWithFireEffect() throws Exception {
        Soldier soldier = new Soldier("张三", 20, 4);
        Player person = new Normal("李四", 18, 8);


        Weapon weapon1 = new Weapon("火枪", 4, "中");
        DebuffContext debuffContext = new DebuffContext("火焰伤害");

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(88);

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 80, random);
        weapon1.setCharactor(soldier);

        game.fight(weapon1, person);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用火枪攻击了普通人李四，李四受到了8点伤害，李四烫伤了，李四剩余生命：10\n" +
                "李四受到3点火焰伤害，李四剩余生命：7\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了战士张三，张三受到了8点伤害，张三剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用火枪攻击了普通人李四，李四受到了8点伤害，李四烫伤了，李四剩余生命：-1\n" +
                "李四受到3点火焰伤害，李四剩余生命：-4\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldSoldierFightWithPersonUsingWeaponWithDizzyEffect() throws Exception {
        Soldier soldier = new Soldier("张三", 20, 4);
        Player person = new Normal("李四", 18, 8);

        Weapon weapon1 = new Weapon("晕锤", 4, "中");
        DebuffContext debuffContext = new DebuffContext("击晕伤害");

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(88, 70, 60);

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 85, random);
        weapon1.setCharactor(soldier);

        game.fight(weapon1, person);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了8点伤害，李四晕倒了，李四剩余生命：10\n" +
                "李四晕倒了，无法攻击，眩晕还剩：1轮\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了8点伤害，李四晕倒了，李四剩余生命：2\n" +
                "李四晕倒了，无法攻击，眩晕还剩：0轮\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了8点伤害，李四剩余生命：-6\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldSoldierFightWithPersonUsingWeaponWithFreezeEffect() throws Exception {
        Soldier soldier = new Soldier("张三", 20, 4);
        Player person = new Normal("李四", 18, 8);

        Weapon weapon1 = new Weapon("冰剑", 4, "中");
        DebuffContext debuffContext = new DebuffContext("冰冻伤害");

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(88).thenReturn(60).thenReturn(55);

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 85, random);
        weapon1.setCharactor(soldier);

        game.fight(weapon1, person);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用冰剑攻击了普通人李四，李四受到了8点伤害，李四冻僵了，李四剩余生命：10\n" +
                "李四冻僵了，无法攻击\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用冰剑攻击了普通人李四，李四受到了8点伤害，李四剩余生命：2\n");
        inOrder.verify(consolePrinter, times(1)).print("普通人李四攻击了战士张三，张三受到了8点伤害，张三剩余生命：12\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用冰剑攻击了普通人李四，李四受到了8点伤害，李四剩余生命：-6\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldSoldierFightWithPersonUsingWeaponWithAllStrengthEffect() throws Exception {
        Soldier soldier = new Soldier("张三", 20, 4);
        Player person = new Normal("李四", 18, 8);

        Weapon weapon1 = new Weapon("利剑", 4, "中");
        DebuffContext debuffContext = new DebuffContext("全力一击");

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(92);

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 90, random);
        weapon1.setCharactor(soldier);

        game.fight(weapon1, person);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用利剑攻击了普通人李四，张三发动了全力一击，李四受到了24点伤害，李四剩余生命：-6\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldSoldierFightWithPersonUsingWeaponWithCumulativeEffect() throws Exception {
        Soldier soldier = new Soldier("张三", 20, 4);
        Player person = new Normal("李四", 18, 8);

        Weapon weapon1 = new Weapon("晕锤", 4, "中");
        DebuffContext debuffContext = new DebuffContext("击晕伤害");

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(90, 88, 60);

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 85, random);
        weapon1.setCharactor(soldier);

        game.fight(weapon1, person);

        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了8点伤害，李四晕倒了，李四剩余生命：10\n" +
                "李四晕倒了，无法攻击，眩晕还剩：1轮\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了8点伤害，李四晕倒了，李四剩余生命：2\n" +
                "李四晕倒了，无法攻击，眩晕还剩：2轮\n");
        inOrder.verify(consolePrinter, times(1)).print("战士张三用晕锤攻击了普通人李四，李四受到了8点伤害，李四晕倒了，李四剩余生命：-6\n" +
                "李四晕倒了，无法攻击，眩晕还剩：1轮\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

    @Test
    public void shouldAssassinFightWithKnightUsingWeapon() throws Exception {
        Assassin person1 = new Assassin("张三", 12, 4);
        Knight person2 = new Knight("李四", 12, 5);

        Weapon weapon1 = new Weapon("峨嵋刺", 4, "短");
        Weapon weapon2 = new Weapon("长枪", 5, "长");

        weapon1.setCharactor(person1);
        weapon2.setCharactor(person2);

        game.fight(weapon1, weapon2);

        inOrder.verify(consolePrinter, times(1)).print("刺客张三用峨嵋刺攻击了骑士李四，李四受到了8点伤害，李四剩余生命：4\n");
        inOrder.verify(consolePrinter, times(1)).print("骑士李四用长枪攻击了刺客张三，张三受到了10点伤害，张三剩余生命：2\n");
        inOrder.verify(consolePrinter, times(1)).print("刺客张三用峨嵋刺攻击了骑士李四，李四受到了8点伤害，李四剩余生命：-4\n");
        inOrder.verify(consolePrinter, times(1)).print("李四被打败了");
    }

}
