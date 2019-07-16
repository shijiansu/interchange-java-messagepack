package json.jackson.based.on.jenkov._1_basics._2_custom_deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import json.jackson.based.on.jenkov._1_basics._1_read_json.Car;

public class CarDeserializer extends StdDeserializer<Car> {

  CarDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Car deserialize(JsonParser parser, DeserializationContext deserializer)
      throws IOException {
    Car car = new Car();
    while (!parser.isClosed()) {
      JsonToken jsonToken = parser.nextToken();
      System.out.println(jsonToken);

      if (JsonToken.FIELD_NAME.equals(jsonToken)) {
        String fieldName = parser.getCurrentName();
        System.out.println(fieldName);

        jsonToken = parser.nextToken(); // just to skip the value field
        System.out.println(jsonToken);

        if ("brand".equals(fieldName)) {
          car.setBrand(parser.getValueAsString());
        } else if ("doors".equals(fieldName)) {
          car.setDoors(parser.getValueAsInt());
        }
      }
    }
    return car;
  }
}
