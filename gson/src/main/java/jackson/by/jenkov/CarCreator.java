package jackson.by.jenkov;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;

public class CarCreator implements InstanceCreator<CarNoDefaultConstructor> {

  // 这里也可以写逻辑
  @Override
  public CarNoDefaultConstructor createInstance(Type type) {
    return new CarNoDefaultConstructor("Toyota", 99);
  }
}
