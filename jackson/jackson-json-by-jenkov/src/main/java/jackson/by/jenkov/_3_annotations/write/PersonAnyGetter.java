package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.HashMap;
import java.util.Map;

public class PersonAnyGetter {
  private Map<String, Object> properties = new HashMap<>();

  @JsonAnyGetter // for map - serializer
  public Map<String, Object> properties() {
    return properties;
  }

  @Override
  public String toString() {
    return "PersonAnyGetter{" + "properties=" + properties + '}';
  }
}
