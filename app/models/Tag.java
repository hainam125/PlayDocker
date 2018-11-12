package models;

import play.data.validation.Constraints;
import java.util.*;
public class Tag {
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

  private Long id;
  @Constraints.Required
  private String name;
  private List<Product> products;
  public Tag(){
    // Left empty
  }
  public Tag(Long id, String name, Collection<Product> products) {
    this.id = id;
    this.name = name;
    this.products = new LinkedList<Product>(products);
    for (Product product : products) {
      product.getTags().add(this);
    }
  }

  private static List<Tag> tags = new LinkedList<Tag>();
  static {
    tags.add(new Tag(1L, "lightweight",
            Product.findByName("paperclips 1")));tags.add(new Tag(2L, "metal",
            Product.findByName("paperclips")));
    tags.add(new Tag(3L, "plastic",
            Product.findByName("paperclips")));
  }
  public static Tag findById(Long id) {
    for (Tag tag : tags) {
      if(tag.id == id) return tag;
    }
    return null;
  }
}
