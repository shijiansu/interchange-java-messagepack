package jackson.by.jenkov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

public class _1_GsonTest {

  @Test
  public void build() {
    // Gson gson = new Gson();
    // 另外一个方式
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    assertNotNull(gson);
  }

  @Test
  public void parsingJsonIntoJavaObjects() {
    String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
    Gson gson = new Gson();
    Car car = gson.fromJson(json, Car.class);

    System.out.print(car);
    assertEquals("Jeep", car.getBrand());
    assertEquals(3, car.getDoors());
  }

  @Test
  public void generatingJsonFromJavaObjects() {
    Car car = new Car();
    car.setBrand("Rover");
    car.setDoors(5);

    Gson gson = new Gson();
    String json = gson.toJson(car);

    System.out.print(json);
    assertEquals("{\"brand\":\"Rover\",\"doors\":5}", json);
  }

  @Test
  public void prettyPrinting() {
    Car car = new Car();
    car.setBrand("Rover");
    car.setDoors(5);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(car);

    System.out.print(json);
    assertEquals("{\n" + "  \"brand\": \"Rover\",\n" + "  \"doors\": 5\n" + "}", json);
  }

  @Test
  public void transientFields() {
    CarTransient car = new CarTransient();
    car.brand = "Rover";
    car.doors = 5;

    Gson gson = new Gson();
    String json = gson.toJson(car);

    System.out.print(json);
    assertEquals("{\"doors\":5}", json);
  }

  @Test
  public void expose() {
    CarExpose car = new CarExpose();
    car.brand = "Rover";
    car.doors = 5;

    GsonBuilder builder = new GsonBuilder();
    builder.excludeFieldsWithoutExposeAnnotation();
    Gson gson = builder.create();

    String json = gson.toJson(car);

    System.out.print(json);
    assertEquals("{\"doors\":5}", json);
  }

  @Test
  public void exclusionStrategies() {
    Car car = new Car();
    car.setBrand("Rover");
    car.setDoors(5);

    ExclusionStrategy exclusionStrategy =
        new ExclusionStrategy() {
          public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            if ("brand".equals(fieldAttributes.getName())) {
              return true;
            }
            return false;
          }

          public boolean shouldSkipClass(Class aClass) {
            return false;
          }
        };

    GsonBuilder builder = new GsonBuilder();
    builder.setExclusionStrategies(exclusionStrategy);
    Gson gson = builder.create();

    String json = gson.toJson(car);

    System.out.print(json);
    assertEquals("{\"doors\":5}", json);
  }

  @Test
  public void serializingNullFields() {
    Gson gson;
    Car car;
    String json;

    gson = new GsonBuilder().create();
    car = new Car();
    // By default the Gson object does not serialize fields with null values
    car.setBrand(null);
    car.setDoors(5);

    json = gson.toJson(car);
    System.out.println(json);
    assertEquals("{\"doors\":5}", json);

    gson = new GsonBuilder().serializeNulls().create();
    car = new Car();
    car.setBrand(null);
    car.setDoors(5);

    json = gson.toJson(car);
    System.out.println(json);
    assertEquals("{\"brand\":null,\"doors\":5}", json);
  }

  @Test
  public void creators() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(CarNoDefaultConstructor.class, new CarCreator());
    Gson gson = gsonBuilder.create();

    String carJson = "{ \"doors\" : 4 }";
    CarNoDefaultConstructor car = gson.fromJson(carJson, CarNoDefaultConstructor.class);

    System.out.println(car);
    assertEquals("Toyota", car.getBrand());
    assertEquals(4, car.getDoors());
  }

  @Test
  public void versionSupport() {
    Person person = new Person();
    person.firstName = "John";
    person.lastName = "Doe";
    person.middleName = "Blocks";
    person.email = "john@doe.com";

    GsonBuilder builder = new GsonBuilder();
    builder.setVersion(2.0);
    Gson gson = builder.create();
    String json = gson.toJson(person);

    System.out.println(json);
    assertEquals("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"middleName\":\"Blocks\"}", json);

    // email would not apply
    String personJson2 =
        "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"middleName\":\"Blocks\",\"email\":\"john@doe.com\"}";
    Person personRead = gson.fromJson(personJson2, Person.class);
    System.out.println(personRead);
    assertNull(personRead.email);
  }

  @Test
  public void customSerializer() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Boolean.class, new BooleanSerializer());
    Gson gson = builder.create();

    PojoWithBoolean pojo = new PojoWithBoolean();
    pojo.username = "abc";
    pojo.isSuperUser = false;

    String json = gson.toJson(pojo);
    System.out.print(json);
    assertEquals("{\"username\":\"abc\",\"isSuperUser\":0}", json);
  }

  @Test
  public void customDeserializer() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Boolean.class, new BooleanDeserializer());
    Gson gson = builder.create();

    String jsonSource = "{\"username\":\"abc\",\"isSuperUser\":1}";
    PojoWithBoolean pojo = gson.fromJson(jsonSource, PojoWithBoolean.class);

    System.out.print(pojo.isSuperUser);
    assertEquals(true, pojo.isSuperUser);
  }
}
