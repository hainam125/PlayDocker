package models;

import java.util.ArrayList;
import java.util.List;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;

@Entity
public class StockItem extends Model {
  public static final Finder<Long, StockItem> find = new Finder<>(StockItem.class);

  private String name;
  private List<StockItem> stock = new ArrayList<>();

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
