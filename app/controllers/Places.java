package controllers;

import java.util.List;

import models.Place;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Places extends Controller {
	public static void list(){
		List<Place> result = Place.findAll();
		renderJSON(result);
	}
}
