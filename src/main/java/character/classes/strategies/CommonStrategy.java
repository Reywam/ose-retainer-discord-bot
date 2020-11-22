package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;

public class CommonStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        // TODO get common items
        return null;
    }
}
