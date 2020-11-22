package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;

public class CommonEquipmentStrategy implements EquipmentStrategy {

    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        JsonNode money = worker.getMoney();
        return worker.getBasicItems().add(money);
    }
}
