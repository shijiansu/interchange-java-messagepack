package jackson.by.jenkov._1_basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class _6_TreeModelTest {

  @Test
  public void simpleExample() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);
    System.out.print(jsonNode);
    assertEquals("Mercedes", jsonNode.get("brand").textValue());
    assertEquals(5, jsonNode.get("doors").intValue());
  }

  @Test
  public void readTree() throws IOException {
    // special readTree() method which always returns a JsonNode
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    JsonNode jsonNode = objectMapper.readTree(carJson);
    System.out.print(jsonNode);
    assertEquals("Mercedes", jsonNode.get("brand").textValue());
    assertEquals(5, jsonNode.get("doors").intValue());
  }

  @Test
  public void navigateJson() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson =
        "{ \"brand\" : \"Mercedes\", \"doors\" : 5,"
            + "  \"owners\" : [\"John\", \"Jack\", \"Jill\"],"
            + "  \"nestedObject\" : { \"field\" : \"value$$$\" } }";

    JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

    JsonNode brandNode = jsonNode.get("brand");
    String brand = brandNode.asText();
    System.out.println("brand = " + brand);
    assertEquals("Mercedes", brand);

    JsonNode doorsNode = jsonNode.get("doors");
    int doors = doorsNode.asInt();
    System.out.println("doors = " + doors);
    assertEquals(5, doors);

    JsonNode array = jsonNode.get("owners");
    JsonNode ownerNode = array.get(0);
    String john = ownerNode.asText();
    System.out.println("john  = " + john);
    assertEquals("John", john);

    JsonNode nestedObjectNode = jsonNode.get("nestedObject");
    JsonNode nestedObjectNodeField = nestedObjectNode.get("field");
    String field = nestedObjectNodeField.asText();
    System.out.println("field = " + field);
    assertEquals("value$$$", field);
  }
}
