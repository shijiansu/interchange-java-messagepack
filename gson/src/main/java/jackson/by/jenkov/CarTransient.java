package jackson.by.jenkov;

public class CarTransient {
  // GSON will ignore
  public transient String brand = null;
  public int doors = 0;
}
