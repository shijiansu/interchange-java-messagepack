package jackson.by.jenkov._2_jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import jackson.by.jenkov.Car;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class _2_JsonParserTest {

  @Test
  public void parsingJsonForToken() throws IOException {
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    JsonFactory factory = new JsonFactory();
    JsonParser parser = factory.createParser(carJson);

    // As long as the isClosed() method of the JsonParser returns false,
    // there are still more tokens in the JSON source.
    while (!parser.isClosed()) {
      JsonToken jsonToken = parser.nextToken();
      System.out.println("jsonToken = " + jsonToken);
    }
  }

  @Test
  public void parsingJson() throws IOException {
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

    JsonFactory factory = new JsonFactory();
    JsonParser parser = factory.createParser(carJson);

    Car car = new Car();
    while (!parser.isClosed()) {
      JsonToken jsonToken = parser.nextToken();

      if (JsonToken.FIELD_NAME.equals(jsonToken)) {
        String fieldName = parser.getCurrentName();
        System.out.println(fieldName);

        jsonToken = parser.nextToken();
        if ("brand".equals(fieldName)) {
          car.setBrand(parser.getValueAsString());
        } else if ("doors".equals(fieldName)) {
          car.setDoors(parser.getValueAsInt());
        }
      }
    }
    System.out.println("car.brand = " + car.getBrand());
    System.out.println("car.doors = " + car.getDoors());
  }
}
