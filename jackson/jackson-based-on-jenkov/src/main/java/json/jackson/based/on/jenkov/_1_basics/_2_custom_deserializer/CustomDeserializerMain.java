package json.jackson.based.on.jenkov._1_basics._2_custom_deserializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import json.jackson.based.on.jenkov._1_basics._1_read_json.Car;


public class CustomDeserializerMain {

  public static void main(String[] args) throws IOException {
    String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 }";

    SimpleModule module = new SimpleModule("CarDeserializer",
        new Version(3, 1, 8, null, null, null));
    module.addDeserializer(Car.class, new CarDeserializer(Car.class));

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(module);

    Car car = mapper.readValue(json, Car.class);
    System.out.println(car);
  }
}
