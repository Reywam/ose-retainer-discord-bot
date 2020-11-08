package character;

import com.sun.javafx.binding.StringFormatter;
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


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        String templateString = "%s: %d[%d]";

        return
                builder.append(String.format(templateString, "Str", strength, strengthModifier)).append('\n')
                .append(String.format(templateString, "Int", intelligence, intelligenceModifier)).append('\n')
                .append(String.format(templateString, "Wis", wisdom, wisdomModifier)).append('\n')
                .append(String.format(templateString, "Dex", dexterity, dexterityModifier)).append('\n')
                .append(String.format(templateString, "Con", constitution, constitutionModifier)).append('\n')
                .append(String.format(templateString, "Cha", charisma, charismaModifier)).append('\n')
                .toString();

    }
}
