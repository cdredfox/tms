package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.ning.http.util.DateUtil;

import models.Waybill;

import controllers.CRUD.ObjectType;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Waybills extends CRUD{
	
	
	public static void index(){
		Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar.set(Calendar.DAY_OF_MONTH,1);
		String startDate=DateUtil.formatDate(calendar.getTime(),"yyyy-MM-dd");
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		String endDate=DateUtil.formatDate(calendar.getTime(),"yyyy-MM-dd");
		list(0,startDate,endDate,null,null,"createDate","DESC");
	}
	
	public static void list(int page, String startDate, String endDate,String customerName,String cardNO, String orderBy, String order) {

		if(StringUtils.isEmpty(orderBy)){
			orderBy="createDate";
			order="DESC";
		}
		
		ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }
        StringBuffer where=null;
        if(!StringUtils.isEmpty(startDate)){
        	where=new StringBuffer();
        	where.append("  createDate>='").append(startDate).append("'");
        }
        if(!StringUtils.isEmpty(endDate)){
        	if(where==null){
        		where=new StringBuffer();
        	}else{
        		where.append(" and");
        	}
        	where.append("  createDate<='").append(endDate).append("'");
        }
        if(!StringUtils.isEmpty(customerName)){
        	if(where==null){
        		where=new StringBuffer();
        	}else{
        		where.append(" and");
        	}
        	where.append("  customer.name='").append(customerName).append("'");
        }
        if(!StringUtils.isEmpty(cardNO)){
        	if(where==null){
        		where=new StringBuffer();
        	}else{
        		where.append(" and");
        	}
        	where.append(" car.cardNO ='").append(cardNO).append("'");
        }
        List<Model> objects = type.findPage(page, null, null, orderBy, order,where==null?null:where.toString());
        Long count = type.count(null, null, where==null?null:where.toString());
        Long totalCount = type.count(null, null,null);
        try {
        	renderJSON(objects);
            //render(type, objects, count, totalCount, page, orderBy, order,startDate,endDate,customerName,cardNO);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order,startDate,endDate,customerName,cardNO);
        }
    }
	
}
