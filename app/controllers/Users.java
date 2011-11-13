package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Users extends Controller {
	
	public static String passwd(String passwd,String old_passwd){
		String username=session.get("username");
		User user=User.find("userName=?", username).first();
		if(user==null){
			return "密码修改失败,登录已过期，请重新登录!";
		}
		if(!user.passWord.equals(old_passwd)){
			return "密码修改失败,您的当前密码输入错误，请重新输入后再提交!";
		}
		user.passWord=passwd;
		user.save();
		return "密码修改成功，请下次登录使用新密码进行登录!";
	}
}
