package jackson.by.jenkov.extension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import jackson.by.jenkov.Employee;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

// CBOR - Concise Binary Object Representation
public class CborJacksonTest {

  @Test
  public void objectToBytes() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());
    Employee employee = new Employee("John Doe", "john@doe.com");
    byte[] cborBytes = objectMapper.writeValueAsBytes(employee);

    System.out.println(Arrays.toString(cborBytes));
    System.out.println(new String(cborBytes));
  }

  @Test
  public void bytesToObject() throws IOException {
    // faster to read and write
    ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());
    Employee employee = new Employee("John Doe", "john@doe.com");
    byte[] cborBytes = objectMapper.writeValueAsBytes(employee);
    Employee employee2 = objectMapper.readValue(cborBytes, Employee.class);

    System.out.print(employee2);
    assertEquals(employee, employee2);
    assertNotSame(employee, employee2);
  }
}
