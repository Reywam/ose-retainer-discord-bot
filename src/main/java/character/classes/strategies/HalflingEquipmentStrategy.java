package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HalflingEquipmentStrategy implements EquipmentStrategy {
    @Override
    public ObjectNode getEquipment(JsonNode equipment) {
        // TODO I dont know
        System.out.println("Halfling equipment strategy");
        return null;
    }
}
