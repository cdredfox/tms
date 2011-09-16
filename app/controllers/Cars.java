package controllers;

import java.util.List;

import models.Car;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Cars extends Controller {

	public static void list() {
		List<Car> result = Car.findAll();
		renderJSON(result);
	}
}
