package json.jackson.based.on.jenkov._1_basics._3_write_json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.IOException;
import json.jackson.based.on.jenkov._1_basics._1_read_json.Car;

public class WriteJsonMain {

  private static void writeValueIntoFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    Car car = new Car();
    car.setBrand("BMW");
    car.setDoors(4);

    objectMapper.writeValue(new FileOutputStream("data/3-output.json"), car);
  }

  private static void writeValueAsString() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    Car car = new Car();
    car.setBrand("BMW");
    car.setDoors(4);

    String json = objectMapper.writeValueAsString(car);
    System.out.println(json);
  }


  public static void main(String[] args) throws IOException {
    writeValueIntoFile();
    writeValueAsString();
  }
}
