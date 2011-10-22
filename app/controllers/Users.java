package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Users extends Controller {
	
	public static boolean passwd(String passwd){
		String username=session.get("username");
		User user=User.find("userName=?", username).first();
		user.passWord=passwd;
		user.save();
		return true;
	}
}
