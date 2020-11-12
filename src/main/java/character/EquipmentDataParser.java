package character;

import character.classes.*;
import character.classes.strategies.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import generator.DiceRoller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final DiceRoller roller = new DiceRoller();
    private JsonNode parse() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("equipment.json");
        JsonNode equipment = mapper.readTree(is);

        return equipment;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Item {
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

    private String test(String category, HashMap<String, List<Item>> items) {
        StringJoiner joiner = new StringJoiner(" ");
        items.get(category).forEach(item -> joiner.add(item.name));
        return joiner.toString();
    }

    public HashMap<String, String> generateEquipment(CharacterData data) throws IOException {
        JsonNode equipment = parse();
        JsonNode weapons = equipment.get("weapons");
        JsonNode armor = equipment.get("armor");
        JsonNode common = equipment.get("common");

        EquipmentStrategy strategy = strategies.get(data.getCharacterClass().getClass());
        JsonNode characterEquipment = strategy.getEquipment(equipment);

        HashMap<String, List<Item>> items = new HashMap<>();
        items.put("weapons", new ArrayList<>());
        items.put("armor", new ArrayList<>());
        items.put("common", new ArrayList<>());

        /*common.forEach(item -> items.get("common").add(new Item(item.get("name").asText())));
        characterEquipment.get("Weapons").get("items").forEach(item -> {
            items.get("weapons").add(new Item(item.get("name").asText()));
        });
        characterEquipment.get("Armor").get("items").forEach(item -> {
            items.get("armor").add(new Item(item.asText()));
        });*/
        String commonstr = test("common", items);
        String weaponstr = test("weapons", items);
        String armorstr = test("armor", items);
        HashMap<String, String> map = new HashMap<>();
        map.put("common", commonstr);
        map.put("weapons", weaponstr);
        map.put("armor", armorstr);
        return  map;
    }



}
