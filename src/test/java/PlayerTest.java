import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    @Test
    public void shouldCreatePlayerSuccessfully()
    {
        Player player = new Normal("张三", 100, 10);

        assertThat(player.getName(), is("张三"));
        assertThat(player.getBlood(), is(100));
        assertThat(player.getDamage(), is(10));
    }

    @Test
    public void shouldAttackOtherPlayer() {
        Player attacker = new Normal("张三", 100, 10);
        Player victim = new Normal("李四", 100, 5);

        attacker.attack(victim);

        assertThat(victim.getBlood(), is(100 - attacker.getDamage()));
    }

    @Test
    public void shouldReturnWhoAttackVictimAndHowMuchVictimBleedAndHowMuchBloodLeft() {
        Player attacker = new Normal("张三", 100, 10);
        Player victim = new Normal("李四", 80, 20);

        assertThat(attacker.attack(victim), is("普通人张三攻击了普通人李四，李四受到了10点伤害，李四剩余生命：70"));
    }

    @Test
    public void shouldAliveWhenBloodIs0() {
        Player survivor = new Normal("张三", 0, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldBeAliveWhenBloodIsGreaterThanZero() {
        Player survivor = new Normal("张三", 1, 0);

        assertThat(survivor.isAlive(), is(true));
    }

    @Test
    public void shouldDieWhenBloodIsLessThanZero() {
        Player dead = new Normal("张三", -1, 0);

        assertThat(dead.isAlive(), is(false));
    }
}
