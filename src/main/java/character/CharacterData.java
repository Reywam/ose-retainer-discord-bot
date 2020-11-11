package character;

import character.classes.CharacterClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import generator.DiceRoller;
import generator.definer.ClassDefiner;
import generator.Generator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class CharacterData {
    @JsonIgnore
    private DiceRoller roller = new DiceRoller();

    private CharacterClass characterClass =  ClassDefiner.define();
    private Characteristics characteristics = new Generator().getCharacteristics();
    private HashMap<String, List<EquipmentDataParser.Item>> adventureEquipment = new HashMap<>();
    private int hitPoints = roller.dN(characterClass.getHitDice()) + characteristics.getConstitutionModifier();

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.valueToTree(this).toPrettyString();
    }
}