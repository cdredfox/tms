package controllers;

import java.util.List;

import models.Role;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Roles extends Controller {
	
	public static void list(){
		List<Role> result = Role.all().fetch();
		renderJSON(result);
	}
}
