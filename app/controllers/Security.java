package controllers;


import java.util.Date;

import play.db.jpa.GenericModel.JPAQuery;
import models.User;

public class Security extends controllers.Secure.Security {
	static boolean authenticate(String username, String password) {
		User user=User.find("userName=? and passWord=? and status=true", username,password).first(); 		
		boolean isValid=(user!=null?true:false);
		if(isValid){
			user.lastLogin=new Date();
			user.save();
		}
			
		return isValid;
	}
}
