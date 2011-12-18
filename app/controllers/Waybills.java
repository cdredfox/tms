package controllers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.ning.http.util.DateUtil;

import models.Repayment;
import models.Waybill;

import controllers.CRUD.ObjectType;
import play.Logger;
import play.Play;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Waybills extends Controller {

	public static void index() {
		renderTemplate("Application/index.html");
	}

	
	public static void list(int page, String startDate, String endDate,
			String customerName, String cardNO, String orderBy, String order,
			String rows) {
		
		Map result = doQuery(page, startDate, endDate, customerName, cardNO,
				orderBy, order, rows);
		renderJSON(result);
	}

	private static Map doQuery(int page, String startDate, String endDate,
			String customerName, String cardNO, String orderBy, String order,
			String rows) {
		if (StringUtils.isEmpty(orderBy)) {
			orderBy = "createDate";
			order = "DESC";
		}

		ObjectType type = null;
		try {
			type = ObjectType.forClass(Waybill.class.getName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		notFoundIfNull(type);
		if (page < 1) {
			page = 1;
		}
		StringBuffer where = null;
		if (!StringUtils.isEmpty(startDate)) {
			where = new StringBuffer();
			where.append("  createDate>='").append(startDate).append("'");
		}
		if (!StringUtils.isEmpty(endDate)) {
			if (where == null) {
				where = new StringBuffer();
			} else {
				where.append(" and");
			}
			where.append("  createDate<='").append(endDate).append("'");
		}
		if (!StringUtils.isEmpty(customerName)) {
			if (where == null) {
				where = new StringBuffer();
			} else {
				where.append(" and");
			}
			where.append("  customer.name='").append(customerName).append("'");
		}

		if (!StringUtils.isEmpty(cardNO)) {
			if (where == null) {
				where = new StringBuffer();
			} else {
				where.append(" and");
			}
			where.append(" car.cardNO ='").append(cardNO).append("'");
		}
		Play.configuration.setProperty("crud.pageSize", rows);
		List<Model> objects = type.findPage(page, null, null, orderBy, order,
				where == null ? null : where.toString());
		Long count = type.count(null, null,
				where == null ? null : where.toString());
		try {
			Map map = new HashMap();
			map.put("total", count);
			map.put("rows", objects);
			return map;
		} catch (Exception e) {
			Logger.error("query is error", e);
			return null;
		}
	}

	public static String create() {
		Waybill wayBill;
		String msg = "";
		if (StringUtils.isEmpty(params.get("id"))) {
			wayBill = new Waybill();
			msg = "货运单信息录入成功!";
		} else {
			wayBill = Waybill.findById(Long.parseLong(params.get("id")));
			msg = "货运单信息修改成功!";
		}

		Binder.bind(wayBill, "", params.all());
		if(wayBill.car!=null && null==wayBill.car.id){
			//json过来的字符串,如果对象为null,还是会弄一个空对象出来,在这里重置一下,设为null对象
			wayBill.car=null;
		}
		wayBill=wayBill.save();
		
		//处理生成应收
		//拖车费，打单费，异提费，港建费，压车费，其他
		BigDecimal trailerFee=wayBill.trailerFee==null?new BigDecimal(0):wayBill.trailerFee;
		BigDecimal printFee=wayBill.printFee==null?new BigDecimal(0):wayBill.printFee;
		BigDecimal provideDifferentFee=wayBill.provideDifferentFee==null?new BigDecimal(0):wayBill.provideDifferentFee;
		BigDecimal portFee=wayBill.portFee==null?new BigDecimal(0):wayBill.portFee;
		BigDecimal pressureCarFee=wayBill.pressureCarFee==null?new BigDecimal(0):wayBill.pressureCarFee;
		BigDecimal otherFee=wayBill.otherFee==null?new BigDecimal(0):wayBill.otherFee;
		
		Repayment repayment=new Repayment();
		repayment.amount=trailerFee.add(printFee).add(provideDifferentFee).add(portFee).add(pressureCarFee).add(otherFee);
		repayment.status='I';
		repayment.waybill=wayBill;
		repayment.save();
		
		return msg;
	}

	public static void find(long id) {
		Waybill wayBill = Waybill.findById(id);
		renderJSON(wayBill);
	}

	public static boolean delete(long id) {
		try {
			Waybill wayBill = Waybill.findById(id);
			wayBill.delete();
			return true;
		} catch (Exception e) {
			Logger.error("delete waybill error!", e);
			return false;
		}
	}
	
	/**
	 * 给外部客户公网查询用
	 * @param customerUserName
	 */
	public static void publicQuery(String customerUserName){
		
	}
}
