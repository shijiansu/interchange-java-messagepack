package jackson.by.jenkov._3_annotations.write;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class PersonRealLifeRawValue {
  public long   personId = 0;

  // 假设这个JSON里面的值是一个JSON的字符串, 这里去除了外面的""
  @JsonRawValue
  public String address  =
      "{ \"street\" : \"Wall Street\", \"no\":1}";

}
