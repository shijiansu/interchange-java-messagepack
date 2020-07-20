package jackson.by.jenkov;

import com.google.gson.annotations.Since;

public class Person {
  @Since(1.0)
  public String firstName;

  @Since(1.0)
  public String lastName;

  @Since(2.0)
  public String middleName;

  @Since(3.0)
  public String email;

  @Override
  public String toString() {
    return "Person{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
