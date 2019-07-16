package json.jackson.based.on.jenkov._1_basics._1_read_json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
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

public class ReadJsonMain {

  private static void readObjectFromJsonString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson =
        "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    Car car = objectMapper.readValue(carJson, Car.class);
    System.out.printf("%s%s", car, System.lineSeparator());
  }

  private static void readObjectFromJsonReader() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
    Reader reader = new StringReader(carJson);
    Car car = objectMapper.readValue(reader, Car.class);
    System.out.printf("%s%s", car, System.lineSeparator());
  }

  private static void readObjectFromJsonFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("data/car.json");
    Car car = objectMapper.readValue(file, Car.class);
    System.out.printf("%s%s", car, System.lineSeparator());
  }

  private static void readObjectFromJsonViaUrl() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    URL url = new URL("file:data/car.json");
    Car car = objectMapper.readValue(url, Car.class);
    System.out.printf("%s%s", car, System.lineSeparator());
  }

  private static void readObjectFromJsonInputStream() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    InputStream input = new FileInputStream("data/car.json");
    Car car = objectMapper.readValue(input, Car.class);
    System.out.printf("%s%s", car, System.lineSeparator());
  }

  private static void readObjectFromJsonByteArray() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
    byte[] bytes = carJson.getBytes(StandardCharsets.UTF_8);
    Car car = objectMapper.readValue(bytes, Car.class);
    System.out.printf("%s%s", car, System.lineSeparator());
  }

  private static void readObjectArrayFromJsonArrayString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    Car[] cars = objectMapper.readValue(jsonArray, Car[].class);
    System.out.printf("%s%s", Arrays.asList(cars), System.lineSeparator());
  }

  private static void readObjectListFromJsonArrayString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    List<Car> cars = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
    });
    System.out.printf("%s%s", cars, System.lineSeparator());
  }

  private static void readMapFromJsonString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
    Map<String, Object> cars = objectMapper
        .readValue(jsonObject, new TypeReference<Map<String, Object>>() {
        });
    System.out.printf("%s%s", cars, System.lineSeparator());
  }

  private static void ignoreUnkownJsonFields() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private static void failOnNullJsonValuesForPrimitiveTypes() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

    String carJson = "{ \"brand\":\"Toyota\", \"doors\":null }";
    Car car = null;
    try {
      car = objectMapper.readValue(carJson, Car.class);
    } catch (MismatchedInputException e) {
      e.printStackTrace();
    }
    System.out.printf("%s%s", car, System.lineSeparator());
  }


  public static void main(String[] args) throws IOException {
    readObjectFromJsonString();
    readObjectFromJsonReader();
    readObjectFromJsonFile();
    readObjectFromJsonViaUrl();
    readObjectFromJsonInputStream();
    readObjectFromJsonByteArray();
    readObjectArrayFromJsonArrayString();
    readObjectListFromJsonArrayString();
    readMapFromJsonString();
    failOnNullJsonValuesForPrimitiveTypes();
  }
}
