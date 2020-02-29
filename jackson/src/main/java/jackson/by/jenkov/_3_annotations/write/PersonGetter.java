package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonGetter;

public class PersonGetter {
  private long personId = 0;

  @JsonGetter("id")
  public long personId() {
    return this.personId;
  }

  public void personId(long personId) {
    this.personId = personId;
  }
}
