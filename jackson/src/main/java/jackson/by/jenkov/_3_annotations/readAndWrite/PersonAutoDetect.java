package jackson.by.jenkov._3_annotations.readAndWrite;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonAutoDetect {

  private long personId = 123;
  public String name = null;

  @Override
  public String toString() {
    return "PersonAutoDetect{" +
        "personId=" + personId +
        ", name='" + name + '\'' +
        '}';
  }
}
