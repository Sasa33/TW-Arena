import static java.lang.String.format;

public class Game{
    private ConsolePrinter printer;

    public Game(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void fight(Player player1, Player player2) {
        Player attacker = player1;
        Player victim = player2;
        Player loser = attacker;

        String attackResult = "";
        while (attacker.isAlive()) {
            attackResult = attacker.attack(victim);
            if(!attackResult.equals("")) {
                printer.print(format("%s\n", attackResult));
            }
            loser = victim;
            victim = attacker;
            attacker = loser;
        }

        printer.print(format("%s被打败了", loser.getName()));
    }
}
