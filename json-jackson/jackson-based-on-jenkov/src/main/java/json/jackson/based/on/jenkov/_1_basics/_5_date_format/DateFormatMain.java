package json.jackson.based.on.jenkov._1_basics._5_date_format;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatMain {

  private static void dateToLong() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Transaction transaction = new Transaction("transfer", new Date());
    String output = objectMapper.writeValueAsString(transaction);
    System.out.println(output);
  }

  private static void dateToString() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Transaction transaction = new Transaction("transfer", new Date());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    objectMapper.setDateFormat(dateFormat);
    String output = objectMapper.writeValueAsString(transaction);
    System.out.println(output);
  }

  public static void main(String[] args) throws JsonProcessingException {
    dateToLong();
    dateToString();
  }
}
