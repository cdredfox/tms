package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import models.Company;
import models.User;
import models.Waybill;
import play.data.binding.Binder;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Users extends Controller {

	public static String passwd(String passwd, String old_passwd) {
		String username = session.get("username");
		User user = User.find("userName=?", username).first();
		if (user == null) {
			return "密码修改失败,登录已过期，请重新登录!";
		}
		if (!user.passWord.equals(old_passwd)) {
			return "密码修改失败,您的当前密码输入错误，请重新输入后再提交!";
		}
		user.passWord = passwd;
		user.save();
		return "密码修改成功，请下次登录使用新密码进行登录!";
	}

	public static void index() {
		renderTemplate("Users/list.html");
	}

	public static void list(String userName, String name, boolean status) {
		List list = null;
		if (StringUtils.isEmpty(userName) && StringUtils.isEmpty(name)) {
			list = User.find("status=?", status).fetch();
		} else {
			List<Predicate> conditionList=new ArrayList<Predicate>();
			CriteriaBuilder cb = User.em().getCriteriaBuilder();
			CriteriaQuery<User> query = cb.createQuery(User.class);
			Root user = query.from(User.class);
			if (StringUtils.isNotEmpty(userName)) {
				Predicate userNameCondition = cb.equal(user.get("userName"), userName);
				conditionList.add(userNameCondition);
			}

			if (StringUtils.isNotEmpty(name)) {
				Predicate nameCondition = cb.equal(user.get("name"), name);
				conditionList.add(nameCondition);
			}
		    Predicate[] predicates = new Predicate[conditionList.size()];
		    conditionList.toArray(predicates);
			query.where(predicates);
			list = User.em().createQuery(query).getResultList();
		}
		renderJSON(list);
	}

	public static boolean delete(long id) {
		User user = User.findById(id);
		user.delete();
		return true;
	}

	public static String create() {
		User user;
		String msg = "";
		if (StringUtils.isEmpty(params.get("id"))) {
			user = new User();
			String username = session.get("username");
			User operatorUser = User.find("userName=?", username).first();
			user.company=operatorUser.company;
			user.passWord="111";
			msg = "用户信息创建成功,默认密码为111，请尽快登录修改密码!";
		} else {
			user = User.findById(Long.parseLong(params.get("id")));
			msg = "用户信息修改成功!";
		}
		Binder.bind(user, "", params.all());
		if(user.role!=null && user.role.id==null){
			user.role=null;
		}
		user.save();
		return msg;
	}
}
