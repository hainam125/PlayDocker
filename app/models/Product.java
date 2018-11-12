package models;

import play.data.validation.Constraints;
import play.libs.F;
import play.mvc.PathBindable;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Product implements PathBindable<Product> {
  private static List<Product> products;
  static {
    products = new ArrayList<Product>();
    products.add(new Product("1111111111111", "Paperclips 1",
            "Paperclips description 1"));
    products.add(new Product("2222222222222", "Paperclips 2",
            "Paperclips description "));
    products.add(new Product("3333333333333", "Paperclips 3",
            "Paperclips description 3"));
    products.add(new Product("4444444444444", "Paperclips 4",
            "Paperclips description 4"));
    products.add(new Product("5555555555555", "Paperclips 5",
            "Paperclips description 5"));
  }

  public static List<Product> findAll() {
    return new ArrayList<Product>(products);
  }
  public static Product findByEan(String ean) {
    for (Product candidate : products) {
      if (candidate.ean.equals(ean)) {
        return candidate;
      }
    }
    return null;
  }
  public static List<Product> findByName(String term) {
    final List<Product> results = new ArrayList<Product>();
    for (Product candidate : products) {
      if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
        results.add(candidate);
      }
    }
    return results;
  }
  public static boolean remove(Product product) {
    return products.remove(product);
  }
  public void save() {
    products.remove(findByEan(this.ean));
    products.add(this);
  }

  public static class EanValidator extends Constraints.Validator<String> implements ConstraintValidator<EAN, String> {
    final static public String message = "error.invalid.ean";
    public EanValidator() {}
    @Override
    public void initialize(EAN constraintAnnotation) {}
    @Override
    public boolean isValid(String value) {
      String pattern = "^[0-9]{13}$";
      return value != null && value.matches(pattern);
    }
    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
      return new F.Tuple<String, Object[]>(message, new Object[]{});
    }
  }

  @Constraint(validatedBy = EanValidator.class)
  @Target( { ElementType.FIELD })
  @Retention(RetentionPolicy.RUNTIME)
  public @interface EAN {
    String message() default "error.invalid.ean";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
  }

  public String getEan() {
    return ean;
  }

  public void setEan(String ean) {
    this.ean = ean;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public byte[] getPicture() {
    return picture;
  }

  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  @Constraints.Required
  @EAN
  private String ean;
  private String name;
  private String description;
  private byte[] picture;
  private List<Tag> tags = new LinkedList<Tag>();

  public Product() {}
  public Product(String ean, String name, String description) {
    this.ean = ean;
    this.name = name;
    this.description = description;
  }
  public String toString() {
    return String.format("%s - %s - %s", ean, name, description);
  }

  //Binding—we’re looking in the DB for a product with an EAN number equal to the one passed in our URL
  @Override
  public Product bind (String key, String value) {
    return findByEan(value);
  }
  //Unbinding—we’re returning our raw value
  @Override
  public String unbind(String key) {
    return this.ean;
  }
  //JavaScript unbinding—we’re returning our raw value
  @Override
  public String javascriptUnbind() {
    return this.ean;
  }
}
