import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class applicationRunner {
    private static Scanner input = new Scanner(System.in);
    private static Player user = new Player(30);
    private static Player enemy = new Player(20);

    public static void main(String[] args) {
        user = new Player(30);
        enemy = new Player(20);

        // populate user's deck
        for (int i = 0; i < 5; i++) {
            user.deck.add(new Card(2, 10, 0, "Deal 10 Damage"));
            user.deck.add(new Card(1, 6, 0, "Deal 6 Damage"));
            user.deck.add(new Card(1, 0, 5, "Add 5 Armor"));
        }

        // populate the enemy's deck with the same cards, but more
        // this way they will eventually win if you can't kill them in time
        for (int i = 0; i < 10; i++) {
            enemy.deck.add(new Card(2, 10, 0, "Deal 10 Damage"));
            enemy.deck.add(new Card(1, 6, 0, "Deal 6 Damage"));
            enemy.deck.add(new Card(1, 0, 5, "Add 5 Armor"));
        }

        boolean isGameOver = false;
        boolean isUserTurn = true;

        user.startGame();
        enemy.startGame();

        user.startTurn();

        while (!isGameOver) {
            if (isUserTurn) {
                interactiveTakeUserTurn();
                enemy.startTurn();
            } else {
                enemy.autoRunTurn(user);
                user.startTurn();
            }

            if (enemy.health <= 0) {
                System.out.println("You Win!");
                isGameOver = true;
            } else if (user.health <= 0) {
                System.out.println("You Lose!");
                isGameOver = true;
            }

            isUserTurn = !isUserTurn;
        }
    }

    public static void interactiveTakeUserTurn() {
        boolean isStillChoosing = true;

        while (isStillChoosing) {
            System.out.println("[1]: Display Hand\t"
                    + "[2]: Display Stats\t"
                    + "[3]: Play a Card\t"
                    + "[4]: End Turn");

            int option = Integer.parseInt(input.nextLine());

            if (option == 1) displayHand();
            else if (option == 2) displayStats();
            else if (option == 3) interactivePlayCard();
            else if (option == 4) isStillChoosing = false;
            else System.out.println("Invalid Input, try again");
        }
    }
    public static void interactivePlayCard() {
        boolean isStillChoosing = true;

        while (isStillChoosing) {
            displayHand();

            int i = user.hand.size() + 1;
            System.out.println("[" + i + "]: Go Back");

            int option = Integer.parseInt(input.nextLine());

            if (option >= 1 && option < i) {
                Card card = user.hand.get(option - 1);
                if (user.mana < card.cost) { System.out.println("Not enough mana: you only have " + user.mana); }
                else {
                    Player target = interactiveChooseTarget();
                    user.playCard(card, target);
                }
            }
            else if (option == i) isStillChoosing = false;
            else System.out.println("Invalid Input, try again");
        }
    }

    public static Player interactiveChooseTarget() {
        while (true) {
            System.out.println("Choose a Target");
            System.out.println("[1]: You\t"
                    + "[2]: Enemy");

            int option = Integer.parseInt(input.nextLine());

            if (option == 1) return user;
            else if (option == 2) return enemy;
            else System.out.println("Invalid Input, try again");
        }
    }

    public static void displayStats() {
        System.out.println("STATS:");

        System.out.println("You:");
        displayPlayerStats(user);
        System.out.println();

        System.out.println("Enemy:");
        displayPlayerStats(enemy);
        System.out.println();
    }

    public static void displayPlayerStats(Player player) {
        System.out.println("Health: " + player.health);
        System.out.println("Armor: " + player.armor);
        System.out.println("Mana: " + player.mana);
        System.out.println("Hand Size: " + player.hand.size());
    }

    public static void displayHand() {
        System.out.println("Your Hand:");

        int cardNum = 1;
        for (Card card: user.hand) {
            System.out.println("[" + cardNum + "]:");
            displayCard(card);
            System.out.println();

            cardNum++;
        }
    }

    public static void displayCard(Card card) {
        System.out.println("Cost: " + card.cost);
        System.out.println("Description: " + card.description);
    }
}
