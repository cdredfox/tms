package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Customers extends Controller{
	
	public static void index(){
		render("customers/index.html");
	}
	
}
