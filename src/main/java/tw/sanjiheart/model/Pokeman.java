package tw.sanjiheart.model;

public class Pokeman {

  private String name;
  private String gender;
  private Status status;

  public Pokeman() {}

  public Pokeman(String name, String gender, Status status) {
    super();
    this.name = name;
    this.gender = gender;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Person [name=" + name + ", gender=" + gender + ", status=" + status + "]";
  }

}
