package tw.sanjiheart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "noise")
public class Noise {

  @Id
  private String id;
  private double value;
  private long at;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public long getAt() {
    return at;
  }

  public void setAt(long at) {
    this.at = at;
  }

  @Override
  public String toString() {
    return "Noise [id=" + id + ", value=" + value + ", at=" + at + "]";
  }

}
