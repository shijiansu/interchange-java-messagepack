package jackson.by.jenkov._2_jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class _3_JsonGeneratorTest {
  @Test
  public void GeneratingJson() throws IOException {
    JsonFactory factory = new JsonFactory();
    JsonGenerator generator = factory.createGenerator(
        new File("data/car2-output.json"), JsonEncoding.UTF8);

    generator.writeStartObject();
    generator.writeStringField("brand", "Mercedes");
    generator.writeNumberField("doors", 5);
    generator.writeEndObject();

    // Closing the JsonGenerator
    generator.close();
  }
}
