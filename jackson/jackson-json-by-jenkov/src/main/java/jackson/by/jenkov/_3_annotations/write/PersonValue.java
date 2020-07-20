package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class PersonValue {

  public long personId = 0;
  public String name = null;

  @JsonRawValue
  @JsonValue // tells Jackson that Jackson should not attempt
  // to serialize the object itself
  public String toJson() {
    return this.personId + "," + this.name;
  }
}
