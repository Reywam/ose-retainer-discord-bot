package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface EquipmentStrategy {
    JsonNode getEquipment(EquipmentDataWorker worker);
}
