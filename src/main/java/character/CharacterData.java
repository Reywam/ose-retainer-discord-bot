package character;

import character.classes.CharacterClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import generator.definer.ClassDefiner;
import generator.Generator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterData {
    private CharacterClass characterClass =  ClassDefiner.define();
    private Characteristics characteristics = new Generator().getCharacteristics();
    private Equipment adventureEquipment = new Equipment();

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.valueToTree(this).toPrettyString();
    }
}