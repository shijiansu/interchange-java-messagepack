package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PersonSerializer {
  public long   personId = 0;
  public String name     = "John";

  @JsonSerialize(using = OptimizedBooleanSerializer.class)
  public boolean enabled = false;
}
