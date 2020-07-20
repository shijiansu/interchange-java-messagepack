package jackson.by.jenkov._1_basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jackson.by.jenkov.Car;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class _1_ReadJsonTest {

  @Test
  public void readObjectFromJsonString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    Car car = objectMapper.readValue(carJson, Car.class);
    System.out.print(car);
    assertNotNull(car, "car is not null");
  }

  @Test
  public void readObjectFromJsonReader() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
    Reader reader = new StringReader(carJson);
    Car car = objectMapper.readValue(reader, Car.class);
    System.out.print(car);
    assertNotNull(car, "car is not null");
  }

  @Test
  public void readObjectFromJsonFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("data/car.json");
    Car car = objectMapper.readValue(file, Car.class);
    System.out.print(car);
    assertNotNull(car, "car is not null");
  }

  @Test
  public void readObjectFromJsonViaUrl() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    URL url = new URL("file:data/car.json");
    Car car = objectMapper.readValue(url, Car.class);
    System.out.print(car);
    assertNotNull(car, "car is not null");
  }

  @Test
  public void readObjectFromJsonInputStream() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    InputStream input = new FileInputStream("data/car.json");
    Car car = objectMapper.readValue(input, Car.class);
    System.out.print(car);
    assertNotNull(car, "car is not null");
  }

  @Test
  public void readObjectFromJsonByteArray() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    byte[] bytes = carJson.getBytes(StandardCharsets.UTF_8);
    Car car = objectMapper.readValue(bytes, Car.class);
    System.out.print(car);
    assertNotNull(car, "car is not null");
  }

  @Test
  public void readObjectArrayFromJsonArrayString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    Car[] cars = objectMapper.readValue(jsonArray, Car[].class);
    System.out.print(Arrays.asList(cars));
    assertNotNull(cars, "cars is not null");
  }

  @Test
  public void readObjectListFromJsonArrayString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    List<Car> cars = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {});
    System.out.print(cars);
    assertNotNull(cars, "cars is not null");
  }

  @Test
  public void readMapFromJsonString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
    Map<String, Object> cars =
        objectMapper.readValue(jsonObject, new TypeReference<Map<String, Object>>() {});
    System.out.print(cars);
    assertNotNull(cars, "car is not null");
  }

  @Test
  public void ignoreUnkownJsonFields() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String carJson = "{\"brands\":\"ford\", \"door\":5}";
    byte[] bytes = carJson.getBytes(StandardCharsets.UTF_8);
    Car car = objectMapper.readValue(bytes, Car.class);
    System.out.print(car);
    // will not fail, but field values are as default
    assertNotNull(car, "car is not null");
    assertEquals(null, car.getBrand(), "car brand is null");
    assertEquals(0, car.getDoors(), "car door is 0");
  }

  @Test
  public void failOnNullJsonValuesForPrimitiveTypes() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
    String jsonObject = "{ \"brand\":\"Toyota\", \"doors\":null }";
    assertThrows(
        MismatchedInputException.class, () -> objectMapper.readValue(jsonObject, Car.class));
  }
}
