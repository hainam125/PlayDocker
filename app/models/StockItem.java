package models;

import java.util.ArrayList;
import java.util.List;

public class StockItem {
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
