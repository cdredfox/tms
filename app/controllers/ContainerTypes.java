package controllers;

import java.util.List;

import models.ContainerType;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class ContainerTypes extends Controller {
	
	public static void list(){
		List<ContainerType> result = ContainerType.findAll();
		renderJSON(result);
	}
}
