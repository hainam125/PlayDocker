package controllers;

import com.google.common.io.Files;
import models.Product;
import models.Tag;
import play.mvc.Http.MultipartFormData;
import views.html.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.FormFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
  public Result details(Product product) {
    Form<Product> filledForm = formFactory.form(Product.class).fill(product);
    return ok(details.render(filledForm));
  }
  public Result save() {
    Form<Product> boundForm = formFactory.form(Product.class).bindFromRequest();
    if(boundForm.hasErrors()) {
      flash("error", "Please correct the form below.");
      return badRequest(details.render(boundForm));
    }
    Product product = boundForm.get();

    MultipartFormData data = request().body().asMultipartFormData();
    MultipartFormData.FilePart part = data.getFile("picture");
    if(part != null) {
      File picture = (File) part.getFile();
      try{
        product.setPicture(Files.toByteArray(picture));
      }
      catch (IOException e) {
        return internalServerError("Error reading file upload");
      }
    }

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

  public Result picture(String ean) {
    final Product product = Product.findByEan(ean);
    if(product == null) return notFound();
    return ok(product.getPicture());
  }
}
