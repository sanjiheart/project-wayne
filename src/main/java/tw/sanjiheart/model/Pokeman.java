package tw.sanjiheart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pokeman {

  @Id
  private int no;
  private String name;
  private String gender;
  private int lv;
  private Status status;
  private String photoUrl;

  public Pokeman() {}

  public Pokeman(String name, String gender, int lv, Status status, String photoUrl) {
    this.name = name;
    this.gender = gender;
    this.lv = lv;
    this.status = status;
    this.photoUrl = photoUrl;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  @Override
  public String toString() {
    return "Pokeman [no=" + no + ", name=" + name + ", gender=" + gender + ", lv=" + lv + ", status=" + status
        + ", photoUrl=" + photoUrl + "]";
  }

}
