package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "personId"})
public class PersonPropertyOrder {

  public long personId = 0;
  public String name = null;
}
