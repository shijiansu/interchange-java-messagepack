package jackson.by.jenkov._1_basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.IOException;
import jackson.by.jenkov.Car;
import org.junit.jupiter.api.Test;

public class _3_WriteJsonTest {

  @Test
  public void writeValueIntoFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    Car car = new Car();
    car.setBrand("BMW");
    car.setDoors(4);

    objectMapper.writeValue(new FileOutputStream("data/car-output.json"), car);
  }

  @Test
  public void writeValueAsString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    Car car = new Car();
    car.setBrand("BMW");
    car.setDoors(4);

    String json = objectMapper.writeValueAsString(car);
    System.out.print(json);
    assertEquals("{\"brand\":\"BMW\",\"doors\":4}", json);
  }
}
