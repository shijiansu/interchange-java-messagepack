package jackson.by.jenkov._3_annotations.readAndWrite;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PersonIgnore {
  @JsonIgnore public long personId = 0;
  public String name = null;

  @Override
  public String toString() {
    return "PersonIgnore{" +
        "personId=" + personId +
        ", name='" + name + '\'' +
        '}';
  }
}
