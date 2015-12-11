import Debuff.DebuffContext;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        Player player1 = new Player("张三", 10, 8);
//        Player player2 = new Player("李四", 20, 9);
        Soldier soldier = new Soldier("张三", 20, 4);
        Player normal = new Normal("李四", 18, 8);

        Weapon weapon1 = new Weapon("优质毒箭", 4, "中");

        DebuffContext debuffContext = new DebuffContext("毒性伤害");

        weapon1.setWeaponDebuff(debuffContext.getDebuff(), 80);

        try {
            weapon1.setCharactor(soldier);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConsolePrinter printer = new ConsolePrinter();
        Game game = new Game(printer);

//        game.fight(player1, player2);
        game.fight(weapon1, normal);
    }
}
