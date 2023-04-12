import lombok.Data;

import java.util.ArrayList;

@Data
public class Player {
    int health;
    int armor;
    int mana;
    ArrayList<Card> handOfCards;
    ArrayList<Card> deckOfCards;

    public Player(int health) {
        this.health = health;

        this.armor = 0;
        this.mana = 3;
        this.handOfCards = new ArrayList<Card>();
        this.deckOfCards = new ArrayList<Card>();
    }

    public void drawCards(int numCards) {
        if (this.deckOfCards.size() < numCards) {
            return;
        }

        for (int i = 0; i < numCards; i++) {
            Card card = deckOfCards.remove(0);

            this.handOfCards.add(card);
        }
    }

    public void takeDamage(int x) {
        x -= this.armor;
        this.health -= x;
    }

    public int getHandTotalMana() {
        int totalHandMana = 0;
        for (Card card : handOfCards) {
            totalHandMana += card.cost;
        }

        return totalHandMana;
    }

    public void startTurn() {
        this.drawCards(1);
        this.armor = 0;
        this.mana = 3;
    }

    public Card getNextPlayableCard() {
        for (Card card: this.handOfCards) {
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
