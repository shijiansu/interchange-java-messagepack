package json.jackson.based.on.jenkov._2_extension._1_cbor;

public class Employee {

  public Employee() {
  }

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
    return "Employee{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
