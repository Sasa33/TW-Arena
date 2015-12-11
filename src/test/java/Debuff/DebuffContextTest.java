package Debuff;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DebuffContextTest {
    private DebuffContext debuffContext;


    @Test
    public void shouldSuccessfullyCreateAPoisonDebuffWhenInputIsPosionEffect ()
    {
        Debuff debuff = new PoisonDebuff("毒性伤害", 2);

        debuffContext = new DebuffContext("毒性伤害");

        assertThat(debuff.getDebuffName(), is(debuffContext.getDebuff().getDebuffName()));
        assertThat(debuff.getDebuffDamage(), is(debuffContext.getDebuff().getDebuffDamage()));
        assertThat(debuff.getDebuffRounds(), is(debuffContext.getDebuff().getDebuffRounds()));
    }

    @Test
    public void shouldSuccessfullyCreateADizzyDebuffWhenInputIsDizzyEffect ()
    {
        Debuff debuff = new DizzyDebuff("击晕伤害");

        debuffContext = new DebuffContext("击晕伤害");

        assertThat(debuff.getDebuffName(), is(debuffContext.getDebuff().getDebuffName()));
        assertThat(debuff.getDebuffDamage(), is(debuffContext.getDebuff().getDebuffDamage()));
        assertThat(debuff.getDebuffRounds(), is(debuffContext.getDebuff().getDebuffRounds()));
    }

}