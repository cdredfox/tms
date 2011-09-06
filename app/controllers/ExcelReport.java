package controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import play.mvc.Controller;
import play.mvc.With;

@With(ExcelControllerHelper.class)
public class ExcelReport extends Controller {
	
	public static void genCarWayBills(String startDate, String endDate,String customerName,String cardNO){
request.format = "xls";
    	
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
        
    	List wayBills=models.Waybill.find(where==null?null:where.toString(), null).fetch();
        renderArgs.put("__EXCEL_FILE_NAME__", "司机托运单.xls");
    	render(wayBills);
	}
	
	public static void genWayBills(String startDate, String endDate,String customerName,String cardNO){
    	request.format = "xls";
    	
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
        
    	List wayBills=models.Waybill.find(where==null?null:where.toString(), null).fetch();
        renderArgs.put("__EXCEL_FILE_NAME__", "客户托货运单.xls");
    	render(wayBills);
	}
}
