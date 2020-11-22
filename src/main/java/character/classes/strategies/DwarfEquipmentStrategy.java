package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class DwarfEquipmentStrategy extends CommonEquipmentStrategy {
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        // TODO Something like a fighter but with restrictions
        ArrayNode weapons = worker.getSimpleWeapons();
        JsonNode armor = worker.getSomeArmor();
        JsonNode common = super.getEquipment(worker);
        JsonNode classItems = worker.getClassItems("dwarf");
        return worker.aggregateEquipment(weapons, armor, common, classItems);
    }
}
