public class Gameboard {

    Player userPlayer;

    Player enemyPlayer;


    public boolean isPlayerTurnStillActive(Player player) {
        return player.mana > player.getHandTotalMana();
    }

    public boolean isPlayerAlive() {
       return userPlayer.health > 0;
    }

    public boolean isEnemyAlive() {
        return enemyPlayer.health > 0;
    }

    public boolean hasPlayerWon() {
       return (enemyPlayer.health < 0 && userPlayer.health > 0);
    }


    /*is Player turn still active?(They have mana that is more than the cost of their cheapest card)-
    * Enemy does all of their attacks
    * Is player dead?-
    * Has the player won?-
    * Player starts their turn */

}
