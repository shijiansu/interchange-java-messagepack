package json.jackson.based.on.jenkov._2_extension._1_cbor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import java.io.IOException;
import java.util.Arrays;

// CBOR - Concise Binary Object Representation
public class CborJacksonExample {

  private static void objectToBytes() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());
    Employee employee = new Employee("John Doe", "john@doe.com");
    byte[] cborBytes = objectMapper.writeValueAsBytes(employee);
    System.out.println(Arrays.toString(cborBytes));
    System.out.println(new String(cborBytes));
  }

  // faster to read and write
  private static void bytesToObject() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper(new CBORFactory());
    Employee employee = new Employee("John Doe", "john@doe.com");
    byte[] cborBytes = null;
    cborBytes = objectMapper.writeValueAsBytes(employee);

    Employee employee2 = null;
    employee2 = objectMapper.readValue(cborBytes, Employee.class);
    System.out.println(employee2);
  }

  public static void main(String[] args) throws IOException {
    objectToBytes();
    bytesToObject();
  }
}
