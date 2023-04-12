import lombok.Data;

import java.util.ArrayList;

@Data
public class Player {

    int health;

    int mana;

    ArrayList<Card> handOfCards;

    ArrayList<Card> deckOfCards;


    public int getHandTotalMana() {
        int totalHandMana = 0;
        for (Card card : handOfCards
        ) {
            totalHandMana += card.castCost;
        }

        return totalHandMana;
    }

    // shouldn't need to check for player hand size?, maybe it should return a reference to the card instead of the mana?
    public int getCheapestCardCost(ArrayList<Card> checkedCards) {
        int cheapestCardsCost = Integer.MIN_VALUE;
        // why is this the default return?

        if (checkedCards.size() > 0) {
            cheapestCardsCost = Integer.MAX_VALUE;
            for (Card card : checkedCards) {
                if (card.castCost < cheapestCardsCost) {
                    cheapestCardsCost = card.castCost;
                }
            }
        }

        return cheapestCardsCost;
    }


    public void runEnemyTurn() {

        int currentCheapestCard = getCheapestCardCost(handOfCards);

        // while (player.handSize > 0 && currentCheapestCard <= player.mana) {
        while (mana > 0 && currentCheapestCard > Integer.MIN_VALUE) {
            for (Card card : handOfCards) {
                if (mana > card.castCost) {
                    card.cardEffect();
                    mana -= card.castCost;
                    currentCheapestCard = getCheapestCardCost(handOfCards);
                }
            }
        }
    }
}
