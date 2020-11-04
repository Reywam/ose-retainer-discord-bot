package generator;

import character.Characteristics;
import generator.definer.ModifierDefiner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Generator {
    private DiceRoller roller = new DiceRoller();

    public Characteristics getCharacteristics() {
        Characteristics characteristics = new Characteristics();

        characteristics.setStrength(getCharacteristicValue());
        characteristics.setStrengthModifier(ModifierDefiner.define(characteristics.getStrength()));

        characteristics.setIntelligence(getCharacteristicValue());
        characteristics.setIntelligenceModifier(ModifierDefiner.define(characteristics.getIntelligence()));

        characteristics.setWisdom(getCharacteristicValue());
        characteristics.setWisdomModifier(ModifierDefiner.define(characteristics.getWisdom()));

        characteristics.setDexterity(getCharacteristicValue());
        characteristics.setDexterityModifier(ModifierDefiner.define(characteristics.getDexterity()));

        characteristics.setConstitution(getCharacteristicValue());
        characteristics.setConstitutionModifier(ModifierDefiner.define(characteristics.getConstitution()));

        characteristics.setCharisma(getCharacteristicValue());
        characteristics.setCharismaModifier(ModifierDefiner.define(characteristics.getCharisma()));

        return characteristics;
    }

    private int getCharacteristicValue() {
        return roller.roll3d6();
    }

}
