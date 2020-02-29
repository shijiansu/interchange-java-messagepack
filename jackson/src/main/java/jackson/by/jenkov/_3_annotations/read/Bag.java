package jackson.by.jenkov._3_annotations.read;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

// Map需要使用@JsonAnySetter因为没有public fields or setter methods
public class Bag {

  private Map<String, Object> properties = new HashMap<>();

  @JsonAnySetter
  public void set(String fieldName, Object value){
    this.properties.put(fieldName, value);
  }

  public Object get(String fieldName){
    return this.properties.get(fieldName);
  }
}
