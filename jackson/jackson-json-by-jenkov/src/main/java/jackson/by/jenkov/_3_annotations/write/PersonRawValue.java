package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class PersonRawValue {
  public long   personId = 0;

  @JsonRawValue
  public String address  = "$#";
}
