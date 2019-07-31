package json.jackson.based.on.jenkov._1_basics._4_custom_serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import json.jackson.based.on.jenkov._1_basics._1_read_json.Car;

public class CarSerializer extends StdSerializer<Car> {
  CarSerializer(Class<Car> t) {
    super(t);
  }

  @Override
  public void serialize(Car car, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("producer", car.getBrand());
    jsonGenerator.writeNumberField("doorCount", car.getDoors());
    jsonGenerator.writeEndObject();
  }
}
