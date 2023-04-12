import lombok.Data;

@Data
public class Card {
    int cost;
    int damage;
    int armor;
    String description;

    public Card(int cost, int damage, int armor, String description) {
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
        this.description = description;
    }

    public void cast(Player target) {
        target.armor += this.armor;
        target.takeDamage(this.damage);
    }
}
