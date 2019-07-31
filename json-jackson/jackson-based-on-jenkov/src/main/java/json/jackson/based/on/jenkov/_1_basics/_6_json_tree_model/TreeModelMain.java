package json.jackson.based.on.jenkov._1_basics._6_json_tree_model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class TreeModelMain {

  private static void simpleExample() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);
    System.out.println(jsonNode);
  }

  // special readTree() method which always returns a JsonNode
  private static void readTree() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    JsonNode jsonNode = objectMapper.readTree(carJson);
    System.out.println(jsonNode);
  }

  private static void navigateJson() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
        "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
        "  \"nestedObject\" : { \"field\" : \"value$$$\" } }";

    JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

    JsonNode brandNode = jsonNode.get("brand");
    String brand = brandNode.asText();
    System.out.println("brand = " + brand);

    JsonNode doorsNode = jsonNode.get("doors");
    int doors = doorsNode.asInt();
    System.out.println("doors = " + doors);

    JsonNode array = jsonNode.get("owners");
    JsonNode ownerNode = array.get(0);
    String john = ownerNode.asText();
    System.out.println("john  = " + john);

    JsonNode nestedObjectNode = jsonNode.get("nestedObject");
    JsonNode nestedObjectNodeField = nestedObjectNode.get("field");
    String field = nestedObjectNodeField.asText();
    System.out.println("field = " + field);
  }

  public static void main(String[] args) throws IOException {
    simpleExample();
    readTree();
    navigateJson();
  }
}
