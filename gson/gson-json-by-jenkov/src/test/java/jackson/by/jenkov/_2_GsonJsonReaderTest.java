package jackson.by.jenkov;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class _2_GsonJsonReaderTest {

  @Test
  public void jsonReader(){
    String json = "{\"brand\" : \"Toyota\", \"doors\" : 5}";

    JsonReader jsonReader = new JsonReader(new StringReader(json));

    try {
      while(jsonReader.hasNext()){
        JsonToken nextToken = jsonReader.peek();
        System.out.println(nextToken);
        if(JsonToken.BEGIN_OBJECT.equals(nextToken)){
          jsonReader.beginObject();
        } else if(JsonToken.NAME.equals(nextToken)){
          String name  =  jsonReader.nextName();
          System.out.println(name);
        } else if(JsonToken.STRING.equals(nextToken)){
          String value =  jsonReader.nextString();
          System.out.println(value);
        } else if(JsonToken.NUMBER.equals(nextToken)){
          long value =  jsonReader.nextLong();
          System.out.println(value);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
