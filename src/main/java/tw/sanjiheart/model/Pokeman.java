package tw.sanjiheart.model;

public class Pokeman {

  private String name;
  private String gender;
  private int lv;
  private Status status;

  public Pokeman() {}

  public Pokeman(String name, String gender, int lv, Status status) {
    super();
    this.name = name;
    this.gender = gender;
    this.lv = lv;
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

  public int getLv() {
    return lv;
  }

  public void setLv(int lv) {
    this.lv = lv;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Pokeman [name=" + name + ", gender=" + gender + ", lv=" + lv + ", status=" + status + "]";
  }

}
