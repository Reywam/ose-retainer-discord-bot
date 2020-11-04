package character;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Characteristics {
    private int strength;
    private int strengthModifier;

    private int intelligence;
    private int intelligenceModifier;

    private int wisdom;
    private int wisdomModifier;

    private int dexterity;
    private int dexterityModifier;

    private int constitution;
    private int constitutionModifier;

    private int charisma;
    private int charismaModifier;
}
