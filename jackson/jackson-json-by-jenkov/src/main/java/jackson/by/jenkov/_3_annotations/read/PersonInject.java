package jackson.by.jenkov._3_annotations.read;

import com.fasterxml.jackson.annotation.JacksonInject;

public class PersonInject {
  public long id = 0;
  public String name = null;

  // 使用情景, 例如时间戳, 审计字段之类
  @JacksonInject public String source = null;

  @Override
  public String toString() {
    return "PersonInject{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", source='"
        + source
        + '\''
        + '}';
  }
}
