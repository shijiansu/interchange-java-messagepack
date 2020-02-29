package jackson.by.jenkov._2_jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

// JsonNode class immutable
public class _1_JsonNodeTest {
  @Test
  public void readJsonNodeFromJson() throws IOException {
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    String f1 = jsonNode.get("f1").asText();
    System.out.print(f1);
    assertEquals("v1", f1);
  }

  @Test
  public void writeJsonNodeToJson() throws IOException {
    String json = "{\"f1\":\"v1\"}";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    String json2 = objectMapper.writeValueAsString(jsonNode);
    System.out.print(json2);
    assertEquals(json, json2);
  }

  @Test
  public void getJsonNodeFieldAtPath() throws IOException {
    String json =
        "{" + "\"identification\":{" + "\"name\":\"James\"," + "\"ssn\": \"ABC123552\"" + "}" + "}";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    JsonNode nameNode = jsonNode.at("/identification/name");
    System.out.print(nameNode);
    assertEquals("James", nameNode.asText());
  }

  @Test
  public void convertWithDefaultValue() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{ \"f1\":\"Hello\", \"f2\":null }";
    JsonNode jsonNode = objectMapper.readTree(json);
    String f2Value = jsonNode.get("f2").asText("Default");
    System.out.print(f2Value);
    assertEquals("Default", f2Value);
  }

  @Test
  public void missingFieldNullPointerException() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{ \"f1\":\"Hello\" }";
    JsonNode jsonNode = objectMapper.readTree(json);
    // missing from the JSON.
    // jsonNode.get("fieldName") will return a Java null value.
    assertThrows(NullPointerException.class, () -> jsonNode.get("f2").asText("Default"));
  }

  @Test
  public void handlingNullFieldValues() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{ \"f1\":\"Hello\" }";
    JsonNode jsonNode = objectMapper.readTree(json);
    JsonNode fieldNode = jsonNode.get("f2");
    if (fieldNode == null || fieldNode.isNull()) {
      System.out.print(jsonNode);
    }
  }

  private static void traverse(JsonNode root) {
    if (root.isObject()) {
      Iterator<String> fieldNames = root.fieldNames();
      while (fieldNames.hasNext()) {
        String fieldName = fieldNames.next();
        JsonNode fieldValue = root.get(fieldName);
        traverse(fieldValue);
      }
    } else if (root.isArray()) {
      ArrayNode arrayNode = (ArrayNode) root;
      for (int i = 0; i < arrayNode.size(); i++) {
        JsonNode arrayElement = arrayNode.get(i);
        traverse(arrayElement);
      }
    } else {
      // JsonNode root represents a single value field - do something with it.
    }
  }

  @Test
  public void traverseJsonNodeGraph() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{ \"f1\":\"Hello\" }";
    JsonNode root = objectMapper.readTree(json);
    traverse(root);
  }

  @Test
  public void objectNode() throws IOException {
    // Create an ObjectNode
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode parentNode = objectMapper.createObjectNode();

    // Set ObjectNode Field
    String json = "{ \"f1\":\"Hello\" }";
    JsonNode childNode = objectMapper.readTree(json);
    parentNode.set("child1", childNode);

    // Put ObjectNode Field With Primitive Value
    parentNode.put("field1", "value1");
    parentNode.put("field2", 123);
    parentNode.put("field3", 999.999);

    // Remove Field
    parentNode.remove("fieldName");

    System.out.print(parentNode);

    assertEquals(
        "{\"child1\":{\"f1\":\"Hello\"},\"field1\":\"value1\",\"field2\":123,\"field3\":999.999}",
        parentNode.toString());
  }
}
