package jackson.by.jenkov._1_basic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jackson.by.jenkov.CarDeserializer;
import java.io.IOException;
import jackson.by.jenkov.Car;
import org.junit.jupiter.api.Test;

public class _2_CustomDeserializerTest {

  @Test
  public void customDeserializer() throws IOException {
    String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 }";

    SimpleModule module =
        new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
    module.addDeserializer(Car.class, new CarDeserializer(Car.class));

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(module);

    Car car = mapper.readValue(json, Car.class);
    System.out.println(car);
    assertNotNull(car, "car is not null");
  }
}
