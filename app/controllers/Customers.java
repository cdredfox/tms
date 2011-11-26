package controllers;

import java.util.List;

import models.Customer;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Customers extends Controller {

	public static void index() {
		render("customers/index.html");
	}

	public static void list() {
		List<Customer> results = Customer.findAll();
		renderJSON(results);
	}
	
}
