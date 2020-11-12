package character;

import character.classes.*;
import character.classes.strategies.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static java.util.Objects.isNull;

public class EquipmentDataParser {

    private final Map<Class, EquipmentStrategy> strategies = new HashMap<Class, EquipmentStrategy>() {
        {
            put(Fighter.class, new FighterEquipmentStrategy());
            put(Cleric.class, new ClericEquipmentStrategy());
            put(MagicUser.class, new MagicUserEquipmentStrategy());
            put(Thief.class, new ThiefEquipmentStrategy());
            put(Halfling.class, new HalflingEquipmentStrategy());
            put(Dwarf.class, new DwarfEquipmentStrategy());
            put(Elf.class, new ElfEquipmentStrategy());
        }
    };

    private final ObjectMapper mapper = new ObjectMapper();
    private JsonNode parse() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("equipment.json");
        JsonNode equipment = mapper.readTree(is);
        return equipment;
    }

    private Map<String, String> equipmentToStringMap(JsonNode equipment) {
        JsonNode weapons = equipment.get("Weapons");
        JsonNode armor = equipment.get("Armor");

        HashMap<String, String> data = new HashMap<>();
        data.put("Weapons", join(weapons));
        data.put("Armor", join(armor));

        return data;
    }

    private String join(JsonNode equipment) {
        if(isNull(equipment)) return "Has no item";
        StringJoiner joiner = new StringJoiner(" ");
        if(equipment.isArray()) {
            equipment.forEach(node -> joiner.add(node.get("name").asText()));
        }
        else {
            joiner.add(equipment.get("name").asText());
        }

        return joiner.toString();
    }

    public Map<String, String> generateEquipment(CharacterData data) throws IOException {
        JsonNode equipment = parse();
        EquipmentStrategy strategy = strategies.get(data.getCharacterClass().getClass());
        JsonNode characterEquipment = strategy.getEquipment(equipment);
        return equipmentToStringMap(characterEquipment);
    }



}
