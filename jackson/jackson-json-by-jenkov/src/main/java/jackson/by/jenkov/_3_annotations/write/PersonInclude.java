package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonInclude;

// This example will only include the name property
// if the value set for it is non-empty,
// meaning is not null and is not an empty string.
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonInclude {

  public long  personId = 0;
  public String name     = null;

}
