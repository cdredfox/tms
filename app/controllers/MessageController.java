package controllers;

import java.util.List;

import models.Message;
import models.User;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class MessageController extends Controller {
 
	public static void getMessageByTo(long toId){
		User user=new User();
		user.id=toId;
		List<Message> result=Message.find("to=? or to is null",user).fetch();
		renderJSON(result);
	}
	
	public static void getMessage(long id){
		Message message=Message.findById(id);
		renderJSON(message);
	}
}
