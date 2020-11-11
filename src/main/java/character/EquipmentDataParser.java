package character;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public class EquipmentDataParser {
    private ObjectMapper mapper = new ObjectMapper();
    public JsonNode parse() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("equipment.json");
        JsonNode rootNode = mapper.readTree(is);
        return rootNode;
    }


}
