import lombok.Data;

@Data
public class Card {
    int cost;
    int damage;
    int armor;

    public Card(int cost, int damage, int armor) {
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

    public void cast(Player target) {
        target.armor += this.armor;
        target.takeDamage(this.damage);
    }
}
