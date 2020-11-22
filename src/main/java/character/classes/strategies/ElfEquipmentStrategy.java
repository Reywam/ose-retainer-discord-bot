package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class ElfEquipmentStrategy extends CommonEquipmentStrategy {
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        // TODO Actually like a fighter strategy
        ArrayNode weapons = worker.getSimpleWeapons();
        JsonNode armor = worker.getArmorOfSomeType(Arrays.asList("heavy", "medium", "light"));
        JsonNode common = super.getEquipment(worker);
        JsonNode classItems = worker.getClassItems("elf");
        // TODO Get magic-user spell book as an additional class item
        return worker.aggregateEquipment(weapons, armor, common, classItems);
    }
}
