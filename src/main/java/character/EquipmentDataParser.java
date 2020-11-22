package character;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class EquipmentDataParser {
    private final ObjectMapper mapper = new ObjectMapper();
    public JsonNode parse() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("equipment.json");
        JsonNode equipment = mapper.readTree(is);
        return equipment;
    }
}
