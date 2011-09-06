package controllers;

import play.db.jpa.GenericModel.JPAQuery;
import models.User;

public class Security extends controllers.Secure.Security {
	static boolean authenticate(String username, String password) {
		long count=User.count("userName=? and passWord=?", username,password); 
		 return count>0?true:false;
	}
}
