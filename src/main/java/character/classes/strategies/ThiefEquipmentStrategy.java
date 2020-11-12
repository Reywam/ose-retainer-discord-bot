package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThiefEquipmentStrategy implements EquipmentStrategy {
    @Override
    public ObjectNode getEquipment(JsonNode equipment) {
        // TODO Get one one-handed melee weapon
        // TODO Get medium armor
        // TODO Get thief tools
        System.out.println("Thief equipment strategy");
        return null;
    }
}
