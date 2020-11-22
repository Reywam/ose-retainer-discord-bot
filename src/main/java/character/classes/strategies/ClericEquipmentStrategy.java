package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClericEquipmentStrategy extends CommonEquipmentStrategy {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        JsonNode basicItems = super.getEquipment(worker);
        JsonNode classItems = worker.getClassItems("cleric");
        ArrayNode meleeWeapon = worker.getNWeaponsOfType(1, "blunt");
        // TODO Get one blunt missile weapon
            // TODO Get ammo if missile needs ammo
        JsonNode armor = worker.getSomeArmor();
        return worker.aggregateEquipment(meleeWeapon, armor, basicItems, classItems);
    }
}
