package json.jackson.based.on.jenkov._1_basics._1_read_json;

public class Car {
  private String brand = null;
  private int doors = 0;

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getDoors() {
    return this.doors;
  }

  public void setDoors(int doors) {
    this.doors = doors;
  }

  @Override
  public String toString() {
    return "Car{" +
        "brand='" + brand + '\'' +
        ", doors=" + doors +
        '}';
  }
}
