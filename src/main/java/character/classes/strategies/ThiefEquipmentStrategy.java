package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThiefEquipmentStrategy extends CommonEquipmentStrategy {
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        ArrayNode weapons = worker.getSimpleWeapons();
        JsonNode armor = worker.getLightArmor();
        JsonNode common = super.getEquipment(worker);
        JsonNode classItems = worker.getClassItems("thief");
        return worker.aggregateEquipment(weapons, armor, common, classItems);
    }
}
