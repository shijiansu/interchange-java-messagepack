package jackson.by.jenkov._1_basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.by.jenkov.Transaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class _5_DateFormatTest {

  @Test
  public void dateToLong() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Transaction transaction = new Transaction("transfer", new Date(1582646490471L));
    String output = objectMapper.writeValueAsString(transaction);
    System.out.print(output);
    assertEquals("{\"type\":\"transfer\",\"date\":1582646490471}", output);
  }

  @Test
  public void dateToString() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Transaction transaction = new Transaction("transfer", new Date(1582646490471L));
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
    objectMapper.setDateFormat(dateFormat);
    String output = objectMapper.writeValueAsString(transaction);
    System.out.print(output);
    assertEquals("{\"type\":\"transfer\",\"date\":\"2020-02-26 00\"}", output);
  }
}
