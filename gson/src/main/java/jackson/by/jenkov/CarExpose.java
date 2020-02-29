package jackson.by.jenkov;

import com.google.gson.annotations.Expose;

public class CarExpose {
  @Expose(serialize = false, deserialize = false)
  public String brand = null;

  @Expose()
  public int doors = 0;
}
