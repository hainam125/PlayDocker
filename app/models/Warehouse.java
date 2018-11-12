package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;

@Entity
public class Warehouse extends Model {
  public static final Finder<Long, Warehouse> find = new Finder<>(Warehouse.class);

  private Warehouse warehouse;
  private Product product;
  private Long quantity;

  public Warehouse getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String toString() {
    return String.format("%d %s", quantity, product);
  }
}
