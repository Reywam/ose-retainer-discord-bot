package character;

import generator.definer.ClassDefiner;
import generator.Generator;
import generator.definer.HitPointsDefiner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterData {
    private Class characterClass = ClassDefiner.define();
    private Characteristics characteristics = new Generator().getCharacteristics();
    private int hitPoints = HitPointsDefiner.defineHP(characterClass, characteristics);
}