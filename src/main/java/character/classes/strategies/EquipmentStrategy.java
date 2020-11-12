package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface EquipmentStrategy {
    JsonNode getEquipment(JsonNode equipment);
}
