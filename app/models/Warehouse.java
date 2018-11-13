package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;

@Entity
public class Warehouse extends Model {
  public static final Finder<Long, Warehouse> find = new Finder<>(Warehouse.class);

  @Id
  private Long id;
  private String name;
  private List<StockItem> stock = new ArrayList<>();

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

  public List<StockItem> getStock() {
    return stock;
  }

  public void setStock(List<StockItem> stock) {
    this.stock = stock;
  }

  public String toString() {
    return name;
  }
}
