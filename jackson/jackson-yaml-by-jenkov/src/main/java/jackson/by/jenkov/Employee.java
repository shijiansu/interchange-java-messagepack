package jackson.by.jenkov;

import java.util.Objects;

public class Employee {

  public Employee() {}

  public Employee(String name, String email) {
    this.name = name;
    this.email = email;
  }

  private String name;
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Employee{" + "name='" + name + '\'' + ", email='" + email + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(name, employee.name) && Objects.equals(email, employee.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email);
  }
}
