package jackson.by.jenkov._3_annotations.readAndWrite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"firstName", "lastName"})
public class PersonIgnoreProperties {
  public long personId = 0;

  public String firstName = null;
  public String lastName = null;

  @Override
  public String toString() {
    return "PersonIgnoreProperties{" +
        "personId=" + personId +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
