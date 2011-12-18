package controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import models.Repayment;

import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;
import vo.Footer;
import vo.RepaymentVO;
import vo.SummaryTotalVO;

@With(Secure.class)
public class Repayments extends Controller {

	public static void list(String customerName,String houseNO,String startDate, String endDate) throws ParseException{
		List<RepaymentVO> data=query(customerName, houseNO,startDate,endDate,new Character[]{'I','W'});
		
//		SummaryTotalVO<RepaymentVO> json=new SummaryTotalVO<RepaymentVO>();
//		json.rows=data;
//		json.footer=new Footer() {
//			private BigDecimal amount=new BigDecimal(100);
//			private BigDecimal chargedAmount=new BigDecimal(100);
//			private String warehouseNO="合计";
//		};
//		
		renderJSON(data);
	}
	public static void index(){
		renderTemplate("repayments/list.html");
	}
	
	public static void cancel(){
		renderTemplate("repayments/cancel.html");
	}
	
	public static void summary(){
		
	}
	
	public static void cancel_list(String customerName,String houseNO,String startDate, String endDate) throws ParseException{
		List<RepaymentVO> data=query(customerName, houseNO,startDate,endDate,new Character[]{'I','W','S'});
		
		renderJSON(data);
	}
	
	public static List<RepaymentVO> query(String customerName,String houseNO,String startDate, String endDate,Character[] status) throws ParseException{
		List<Repayment> payments=Repayment.query(customerName, houseNO,startDate,endDate,status);
		List<RepaymentVO> data=new ArrayList<RepaymentVO>();
		for (Repayment repayment : payments) {
			data.add(RepaymentVO.convert(repayment));
		}
		return data;
	}
	
	public static boolean do_cancel(long[] id,BigDecimal[] amount){
		for(int i=0;i<id.length;i++){
			Repayment repayment = Repayment.findById(id[i]);
			repayment.chargedAmount=amount[i].add(repayment.chargedAmount==null?new BigDecimal(0):repayment.chargedAmount);
			int result=repayment.chargedAmount.compareTo(repayment.amount);
			if(result>=0){
				repayment.status='S';
				repayment.finashDate=new Date();
			}else{
				repayment.status='W';
			}
			if(repayment.chargedAmount.compareTo(new BigDecimal(0))==0){
				//反销账情况，如果全销，则置为I状态
				repayment.status='I';
			}
			repayment.save();
			
		}
		return true;
	}
}
