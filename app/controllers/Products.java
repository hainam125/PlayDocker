package controllers;

import models.Product;
import models.Tag;
import play.mvc.With;
import views.html.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.FormFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@With(CatchAction.class)
public class Products extends Controller {
  @Inject
  FormFactory formFactory;

  public Result index() {
    return redirect(routes.Products.list(0));
  }

  public Result list(int page) {
    List<Product> products = Product.findAll();
    return ok(list.render(products));
  }

  public Result newProduct() {
    Form<Product> productForm = formFactory.form(Product.class);
    return ok(details.render(productForm));
  }
  public Result details(String ean) {
    final Product product = Product.findByEan(ean);
    if(product == null){
      return notFound(String.format("Product %s does not exist.", ean));
    }
    Form<Product> filledForm = formFactory.form(Product.class).fill(product);
    return ok(details.render(filledForm));
  }
  public Result save() {
    Form<Product> boundForm = formFactory.form(Product.class);
    if(boundForm.hasErrors()) {
      flash("error", "Please correct the form below.");
      return badRequest(details.render(boundForm));
    }
    Product product = boundForm.bindFromRequest().get();
    List<Tag> tags = new ArrayList<>();
    for(Tag tag : product.getTags()){
      if(tag.getId() != null){
        tags.add(Tag.findById(tag.getId()));
      }
    }
    product.setTags(tags);
    product.save();
    flash("success", String.format("Successfully added product %s", product));
    return redirect(routes.Products.list(1));
  }

  public Result delete(String ean) {
    final Product product = Product.findByEan(ean);
    if(product == null) {
      return notFound(String.format("Product %s does not exists.", ean));
    }
    Product.remove(product);
    return redirect(routes.Products.list(1));
  }
}
