package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DwarfEquipmentStrategy implements EquipmentStrategy {
    @Override
    public ObjectNode getEquipment(JsonNode equipment) {
        // TODO Something like a fighter
        System.out.println("Dwarf equipment strategy");
        return null;
    }
}
