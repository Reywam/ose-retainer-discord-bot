package character.classes.strategies;

import character.Equipment;
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
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        // Holy symbol
        // , 1d20gp
        JsonNode common = worker.getBasicItems();
        JsonNode classItems = worker.getClassItems("cleric");
        // TODO Get money
        ArrayNode meleeWeapon = worker.getNWeaponsOfType(1, "blunt");
        // TODO Get one blunt missile weapon
            // TODO Get ammo if missile needs ammo
        JsonNode armor = worker.getSomeArmor();
        // TODO Get holy symbol
        return worker.aggregateEquipment(meleeWeapon, armor, common);
    }
}
