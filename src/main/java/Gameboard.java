public class Gameboard {
    Player user;
    Player enemy;

    public boolean isPlayerTurnStillActive(Player player) {
        return player.mana > player.getHandTotalMana();
    }

    public boolean isPlayerAlive() {
        return user.health > 0;
    }

    public boolean isEnemyAlive() {
        return enemy.health > 0;
    }

    public boolean hasPlayerWon() {
        return (enemy.health < 0 && user.health > 0);
    }


    /*is Player turn still active?(They have mana that is more than the cost of their cheapest card)-
    * Enemy does all of their attacks
    * Is player dead?-
    * Has the player won?-
    * Player starts their turn */

}
