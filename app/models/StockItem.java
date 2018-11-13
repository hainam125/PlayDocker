package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;

@Entity
public class StockItem extends Model {
  public static final Finder<Long, StockItem> find = new Finder<>(StockItem.class);

  @Id
  private Long id;
  @ManyToOne
  private Warehouse warehouse;
  @ManyToOne
  private Product product;
  private Long quantity;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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
