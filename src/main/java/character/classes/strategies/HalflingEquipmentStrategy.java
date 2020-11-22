package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HalflingEquipmentStrategy extends CommonEquipmentStrategy{
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        // TODO I dont know
        ArrayNode weapons = worker.getSimpleWeapons();
        JsonNode armor = worker.getSomeArmor();
        JsonNode common = super.getEquipment(worker);
        JsonNode classItems = worker.getClassItems("halfling");
        return worker.aggregateEquipment(weapons, armor, common, classItems);
    }
}
