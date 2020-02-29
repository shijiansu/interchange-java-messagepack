package jackson.by.jenkov._3_annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.by.jenkov._3_annotations.write.PersonAnyGetter;
import jackson.by.jenkov._3_annotations.write.PersonGetter;
import jackson.by.jenkov._3_annotations.write.PersonInclude;
import jackson.by.jenkov._3_annotations.write.PersonNoneRawValue;
import jackson.by.jenkov._3_annotations.write.PersonPropertyOrder;
import jackson.by.jenkov._3_annotations.write.PersonRawValue;
import jackson.by.jenkov._3_annotations.write.PersonRealLifeRawValue;
import jackson.by.jenkov._3_annotations.write.PersonSerializer;
import jackson.by.jenkov._3_annotations.write.PersonValue;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class _3_WriteTest {
  @Test
  public void jsonInclude() throws IOException {
    PersonInclude person = new PersonInclude();
    person.personId = 99;

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"personId\":99}", json);
  }

  @Test
  public void jsonGetter() throws IOException {
    PersonGetter person = new PersonGetter();
    person.personId(99);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"id\":99}", json);
  }

  @Test
  public void jsonAnyGetter() throws IOException {
    PersonAnyGetter person = new PersonAnyGetter();
    person.properties().put("personId", 99);
    person.properties().put("name", "Mike");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"name\":\"Mike\",\"personId\":99}", json);
  }

  @Test
  public void jsonPropertyOrder() throws IOException {
    PersonPropertyOrder person = new PersonPropertyOrder();
    person.personId = 99;
    person.name = "Mike";

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"name\":\"Mike\",\"personId\":99}", json);
  }

  @Test
  public void jsonRawValue() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    PersonNoneRawValue person = new PersonNoneRawValue();
    String json = objectMapper.writeValueAsString(person);
    System.out.println(json);
    assertEquals("{\"personId\":0,\"address\":\"$#\"}", json);

    PersonRawValue person2 = new PersonRawValue();
    String json2 = objectMapper.writeValueAsString(person2);
    System.out.println(json2);
    assertEquals("{\"personId\":0,\"address\":$#}", json2);

    PersonRealLifeRawValue person3 = new PersonRealLifeRawValue();
    String json3 = objectMapper.writeValueAsString(person3);
    System.out.println(json3);
    // 这样内嵌的JSON没有外围的""
    assertEquals("{\"personId\":0,\"address\":{ \"street\" : \"Wall Street\", \"no\":1}}", json3);
  }

  @Test
  public void jsonValue() throws IOException {
    PersonValue person = new PersonValue();

    ObjectMapper objectMapper = new ObjectMapper();
    String text = objectMapper.writeValueAsString(person);

    System.out.print(text);
    assertEquals("0,null", text);
  }

  @Test
  public void jsonSerialize() throws IOException {
    PersonSerializer person = new PersonSerializer();
    person.personId = 99;
    person.name = "Mike";
    person.enabled = true;

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(person);

    System.out.print(json);
    assertEquals("{\"personId\":99,\"name\":\"Mike\",\"enabled\":1}", json);
  }
}
