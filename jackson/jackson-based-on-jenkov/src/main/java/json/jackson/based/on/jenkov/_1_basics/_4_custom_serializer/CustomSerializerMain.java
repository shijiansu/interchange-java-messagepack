package json.jackson.based.on.jenkov._1_basics._4_custom_serializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import json.jackson.based.on.jenkov._1_basics._1_read_json.Car;

public class CustomSerializerMain {

  public static void main(String[] args) throws IOException {
    SimpleModule module =
        new SimpleModule("CarSerializer", new Version(2, 1, 3, null, null, null));

    ObjectMapper objectMapper = new ObjectMapper();
    module.addSerializer(Car.class, new CarSerializer(Car.class));

    objectMapper.registerModule(module);

    Car car = new Car();
    car.setBrand("Mercedes");
    car.setDoors(5);

    String carJson = objectMapper.writeValueAsString(car);
    System.out.println(carJson);
  }
}
