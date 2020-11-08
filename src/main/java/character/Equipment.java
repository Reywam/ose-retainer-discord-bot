package character;

import com.fasterxml.jackson.annotation.JsonIgnore;
import generator.DiceRoller;
import lombok.Data;

@Data
public class Equipment {
    @JsonIgnore
    private transient DiceRoller roller = new DiceRoller();

    private int money = roller.roll3d6() * 10;

    @JsonIgnore
    private WeaponsAndArmor weaponsAndArmor = new WeaponsAndArmor();

    public String toString() {
        return Integer.toString(money);
    }
}
