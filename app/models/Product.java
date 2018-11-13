package models;

import play.data.validation.Constraints;
import play.libs.F;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import javax.persistence.*;
import io.ebean.*;

@Entity
public class Product extends Model {
  public static final Finder<Long, Product> find = new Finder<>(Product.class);

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

  @Id
  private Long id;
  @Constraints.Required
  @EAN
  private String ean;
  private String name;
  private String description;
  private byte[] picture;
  @ManyToMany
  private List<Tag> tags;
  @OneToMany(mappedBy = "product")
  private List<StockItem> stockItems;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String toString() {
    return String.format("%s - %s - %s", ean, name, description);
  }
}
