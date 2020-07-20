package jackson.by.jenkov.extension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jackson.by.jenkov.Employee;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class YamlJacksonTest {

  @Test
  public void objectToYaml() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    Employee employee = new Employee("John Doe", "john@doe.com");
    String yamlString = objectMapper.writeValueAsString(employee);

    System.out.print(yamlString);
    assertEquals("---\n" + "name: \"John Doe\"\n" + "email: \"john@doe.com\"\n", yamlString);
  }

  @Test
  public void yamlToObject() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    Employee employee = new Employee("John Doe", "john@doe.com");
    String yamlString = objectMapper.writeValueAsString(employee);
    Employee employee2 = objectMapper.readValue(yamlString, Employee.class);

    System.out.print(employee2);
    assertEquals(employee, employee2);
    assertNotSame(employee, employee2);
  }
}
