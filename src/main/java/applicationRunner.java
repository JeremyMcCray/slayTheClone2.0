import java.util.ArrayList;
import java.util.Scanner;

public class applicationRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Gameboard gameboard = new Gameboard();
        gameboard.userPlayer = new Player();
        gameboard.enemyPlayer = new Player();

        while (gameboard.isPlayerAlive() && gameboard.isEnemyAlive()) {
            /* GAME LOOP */
            // player's turn
            System.out.println("PLAYER'S TURN");

            // check for victory
            if (!gameboard.hasPlayerWon()) {
                // enemy's turn
                System.out.println("ENEMY'S TURN");
            }
        }

        System.out.println("GAME OVER!");
        if (gameboard.isPlayerAlive()) {
            System.out.println("YOU WIN!");
        } else {
            System.out.println("YOU LOSE!");
        }
    }

    public void displayHand(ArrayList<Card> hand) {
        for (Card card: hand) {
        }
    }
}
