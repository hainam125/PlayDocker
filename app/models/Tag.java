package models;

import play.data.validation.Constraints;
import io.ebean.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class Tag  extends Model {
  public static final Finder<Long, Tag> find = new Finder<>(Tag.class);

  @Id
  private Long id;
  @Constraints.Required
  private String name;
  private List<Product> products;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

}
