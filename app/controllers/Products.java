package controllers;

import com.google.common.io.Files;
import io.ebean.PagedList;
import models.Product;
import models.StockItem;
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
    PagedList<Product> products = Product.find(page);
    return ok(list.render(products));
  }

  public Result newProduct() {
    Form<Product> productForm = formFactory.form(Product.class);
    return ok(details.render(productForm));
  }
  public Result details(String ean) {
    Product product = Product.find.query().where().eq("ean", ean).findOne();
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
        tags.add(Tag.find.byId(tag.getId()));
      }
    }
    product.setTags(tags);

    if(product.getId() == null) product.save();
    else product.update();

    flash("success", String.format("Successfully added product %s", product));
    return redirect(routes.Products.list(0));
  }

  public Result delete(String ean) {
    Product product = Product.find.query().where().eq("ean", ean).findOne();
    if(product == null) {
      return notFound(String.format("Product %s does not exists.", ean));
    }
    product.delete();
    return redirect(routes.Products.list(0));
  }

  public Result picture(String ean) {
    final Product product = Product.find.query().findOne();
    if(product == null) return notFound();
    return ok(product.getPicture());
  }
}
