package controllers;

import models.StockItem;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class StockItems extends Controller {
  public Result index(){
    List<StockItem> items = StockItem.find.query()
            .where()
            .ge("quantity", 300)
            .orderBy("quantity")
            .setFirstRow(0)//set first to start
            .setMaxRows(10)//get first 10 items
            .findList();
    return ok(items.toString());
  }
}
