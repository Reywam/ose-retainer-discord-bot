package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class ClericEquipmentStrategy implements EquipmentStrategy {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        ArrayNode meleeWeapon = EquipmentDataWorker.getNWeaponsOfType(1, "blunt", equipment);
        // TODO Get one blunt missile weapon
            // TODO Get ammo if missile needs ammo
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("heavy", "medium"), equipment);
        // TODO Get holy symbol
        return EquipmentDataWorker.aggregateEquipment(meleeWeapon, armor);
    }
}
