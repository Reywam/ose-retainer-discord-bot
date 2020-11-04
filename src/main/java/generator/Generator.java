package generator;

import character.Characteristics;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class Generator {
    private DiceRoller roller = new DiceRoller();

    public Characteristics getCharacteristics() {
        Characteristics characteristics = new Characteristics();

        characteristics.setStrength(getCharacteristicValue());
        characteristics.setIntelligence(getCharacteristicValue());
        characteristics.setWisdom(getCharacteristicValue());
        characteristics.setDexterity(getCharacteristicValue());
        characteristics.setConstitution(getCharacteristicValue());
        characteristics.setCharisma(getCharacteristicValue());

        return characteristics;
    }

    private int getCharacteristicValue() {
        return roller.roll3d6();
    }

}
