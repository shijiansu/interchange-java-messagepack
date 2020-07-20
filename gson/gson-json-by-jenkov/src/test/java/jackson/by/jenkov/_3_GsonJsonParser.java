package jackson.by.jenkov;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

public class _3_GsonJsonParser {
  @Test
  public void jsonParser() {
    String json = "{ \"f1\":\"Hello\",\"f2\":{\"f3:\":\"World\"}}";

    JsonElement jsonTree = JsonParser.parseString(json);
    if (jsonTree.isJsonObject()) {
      JsonObject jsonObject = jsonTree.getAsJsonObject();

      JsonElement f1 = jsonObject.get("f1");
      JsonElement f2 = jsonObject.get("f2");

      if (f2.isJsonObject()) {
        JsonObject f2Obj = f2.getAsJsonObject();
        JsonElement f3 = f2Obj.get("f3");
      }
    }
  }
}
