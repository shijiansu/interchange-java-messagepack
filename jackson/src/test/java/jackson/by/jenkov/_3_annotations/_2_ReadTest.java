package jackson.by.jenkov._3_annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.by.jenkov._3_annotations.read.Bag;
import jackson.by.jenkov._3_annotations.read.Person;
import jackson.by.jenkov._3_annotations.read.PersonDeserialize;
import jackson.by.jenkov._3_annotations.read.PersonImmutable;
import jackson.by.jenkov._3_annotations.read.PersonInject;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _2_ReadTest {
  @Test
  public void jsonSetter() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{\n" + "  \"id\"   : 1234,\n" + "  \"name\" : \"John\"\n" + "}";
    Person person = objectMapper.readValue(json, Person.class);

    System.out.print(person);
    assertEquals(1234, person.getPersonId());
    assertEquals("John", person.getName());
  }

  @Test
  public void jsonAnySetter() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{\n" + "  \"id\"   : 1234,\n" + "  \"name\" : \"John\"\n" + "}";
    Bag bag = objectMapper.readValue(json, Bag.class);

    Assertions.assertNotNull(bag);
    assertEquals(1234, bag.get("id"));
    assertEquals("John", bag.get("name"));
  }

  @Test
  public void jsonCreator() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "{\n" + "  \"id\"   : 1234,\n" + "  \"name\" : \"John\"\n" + "}";
    PersonImmutable person = objectMapper.readValue(json, PersonImmutable.class);

    System.out.print(person);
    assertEquals(1234, person.getId());
    assertEquals("John", person.getName());
  }

  @Test
  public void jacksonInject() throws IOException {
    InjectableValues inject = new InjectableValues.Std().addValue(String.class, "jenkov.com");
    ObjectMapper objectMapper = new ObjectMapper();
    PersonInject person =
        objectMapper
            .reader(inject)
            .forType(PersonInject.class)
            .readValue(new File("data/person.json"));

    System.out.print(person);
    assertEquals(1234, person.id);
    assertEquals("John", person.name);
    assertEquals("jenkov.com", person.source);
  }

  @Test
  public void jsonDeserialize() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    PersonDeserialize person = objectMapper
        .reader(PersonDeserialize.class)
        .readValue(new File("data/person-optimized-boolean.json"));

    System.out.print(person);
    assertEquals(1234, person.id);
    assertEquals("John", person.name);
    assertEquals(true, person.enabled);
  }
}
