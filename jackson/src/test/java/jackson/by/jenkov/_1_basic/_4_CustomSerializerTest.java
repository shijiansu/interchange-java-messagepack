package jackson.by.jenkov._1_basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jackson.by.jenkov.CarSerializer;
import java.io.IOException;
import jackson.by.jenkov.Car;
import org.junit.jupiter.api.Test;

public class _4_CustomSerializerTest {

  @Test
  public void customSerializer() throws IOException {
    SimpleModule module = new SimpleModule("CarSerializer", new Version(2, 1, 3, null, null, null));

    ObjectMapper objectMapper = new ObjectMapper();
    module.addSerializer(Car.class, new CarSerializer(Car.class));

    objectMapper.registerModule(module);

    Car car = new Car();
    car.setBrand("Mercedes");
    car.setDoors(5);

    String json = objectMapper.writeValueAsString(car);
    System.out.print(json);
    assertEquals("{\"producer\":\"Mercedes\",\"doorCount\":5}", json);
  }
}
