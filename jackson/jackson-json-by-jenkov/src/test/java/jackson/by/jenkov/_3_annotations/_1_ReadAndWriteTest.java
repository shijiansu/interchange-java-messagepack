package jackson.by.jenkov._3_annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.by.jenkov._3_annotations.readAndWrite.PersonAutoDetect;
import jackson.by.jenkov._3_annotations.readAndWrite.PersonIgnore;
import jackson.by.jenkov._3_annotations.readAndWrite.PersonIgnoreProperties;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class _1_ReadAndWriteTest {
  @Test
  public void jsonIgnoreRead() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{\"personId\": 99, \"name\": \"Mike\"}";
    PersonIgnore person = objectMapper.readValue(json, PersonIgnore.class);

    System.out.print(person);
    assertEquals(0, person.personId); // been ignored
    assertEquals("Mike", person.name);
  }

  @Test
  public void jsonIgnoreWrite() throws IOException {
    PersonIgnore person = new PersonIgnore();
    person.personId = 999;
    person.name = "Mike";

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"name\":\"Mike\"}", json);
  }

  @Test
  public void jsonIgnorePropertiesRead() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{\"personId\": 99, \"firstName\": \"firstName\", \"firstName\": \"firstName\"}";
    PersonIgnoreProperties person = objectMapper.readValue(json, PersonIgnoreProperties.class);

    System.out.print(person);
    assertEquals(99, person.personId);
    assertEquals(null, person.firstName);
    assertEquals(null, person.lastName);
  }

  @Test
  public void jsonIgnorePropertiesWrite() throws IOException {
    PersonIgnoreProperties person = new PersonIgnoreProperties();
    person.personId = 99;
    person.firstName = "first name";
    person.lastName = "last name";

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"personId\":99}", json);
  }

  @Test
  public void jsonAutoDetect() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{\"personId\": 99, \"name\": \"Mike\"}";
    PersonAutoDetect person = objectMapper.readValue(json, PersonAutoDetect.class);

    System.out.print(person);
    assertEquals("Mike", person.name);
    assertEquals("PersonAutoDetect{personId=99, name='Mike'}", person.toString());

    PersonAutoDetect person2 = new PersonAutoDetect();
    json = objectMapper.writeValueAsString(person2);
    assertEquals("{\"personId\":123,\"name\":null}", json);
  }
}
