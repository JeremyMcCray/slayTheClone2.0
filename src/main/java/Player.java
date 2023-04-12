import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

@Data
public class Player {
    int health;
    int armor;
    int mana;
    ArrayList<Card> hand;
    ArrayList<Card> deck;

    public Player(int health) {
        this.health = health;

        this.armor = 0;
        this.mana = 3;
        this.hand = new ArrayList<Card>();
        this.deck = new ArrayList<Card>();
    }

    public void drawCards(int numCards) {
        if (this.deck.size() < numCards) {
            return;
        }

        for (int i = 0; i < numCards; i++) {
            Card card = deck.remove(0);

            this.hand.add(card);
        }
    }

    public void takeDamage(int x) {
        x -= this.armor;
        this.health -= x;
    }

    public int getHandTotalMana() {
        int totalHandMana = 0;
        for (Card card : hand) {
            totalHandMana += card.cost;
        }

        return totalHandMana;
    }

    public void startTurn() {
        this.drawCards(1);
        this.armor = 0;
        this.mana = 3;
    }

    public void startGame() {
        Collections.shuffle(this.deck);
        this.drawCards(3);
    }

    public Card getNextPlayableCard() {
        for (Card card: this.hand) {
            if (card.cost <= this.mana) {
                return card;
            }
        }

        return null;
    }


    public void autoRunTurn(Player opponent) {
        Card cardToCast = this.getNextPlayableCard();

        while (cardToCast != null) {
            this.playCard(cardToCast, opponent);
        }
    }

    public void playCard(Card card, Player opponent) {
        this.mana -= card.cost;

        if (card.damage > 0) {
            card.cast(opponent);
        } else {
            card.cast(this);
        }
    }
}
