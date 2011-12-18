package models;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import play.db.jpa.Model;
@Entity
public class Repayment extends Model {
	/** 对应的运单 */ 
	@OneToOne
	public Waybill waybill;
	/** 应收的状态 I:未销 S全销 W部分销*/
	public char status;
	/** 应收的金额 */
	public BigDecimal amount=new BigDecimal(0);
	/** 已收金额（部分销的时候，这里会有值) */
	public BigDecimal chargedAmount=new BigDecimal(0);
	/** 收款日期 */
	public Date finashDate;
	
	
	/**
	 * 查所有未销账的应收记录
	 * @param customerName
	 * @param houseNO
	 * @return
	 * @throws ParseException 
	 */
	public static List<Repayment> query(String customerName,String houseNO,String startDate, String endDate,Character...status) throws ParseException{
		
		List<Predicate> conditionList=new ArrayList<Predicate>();
		CriteriaBuilder cb = Repayment.em().getCriteriaBuilder();
		
		CriteriaQuery<Repayment> query = cb.createQuery(Repayment.class);
		Root repayment = query.from(Repayment.class);
		if (StringUtils.isNotEmpty(customerName)) {
			Predicate customerNameCondition = cb.equal(repayment.get("waybill").get("customer").get("name"), customerName);
			conditionList.add(customerNameCondition);
		}

		if (StringUtils.isNotEmpty(houseNO)) {
			Predicate houseNOCondition = cb.equal(repayment.get("waybill").get("warehouseNO"), houseNO);
			conditionList.add(houseNOCondition);
		}
		
		if (StringUtils.isNotEmpty(startDate)) {
			Predicate houseNOCondition = cb.greaterThanOrEqualTo(repayment.get("waybill").get("createDate"), DateUtils.parseDate(startDate, new String[]{"yyyy-MM-dd"}));
			conditionList.add(houseNOCondition);
		}
		
		if (StringUtils.isNotEmpty(endDate)) {
			Predicate houseNOCondition = cb.lessThanOrEqualTo(repayment.get("waybill").get("createDate"), DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd"}));
			conditionList.add(houseNOCondition);
		}
		
		Predicate houseNOCondition = repayment.get("status").in(status);
		conditionList.add(houseNOCondition);
		
	    Predicate[] predicates = new Predicate[conditionList.size()];
	    conditionList.toArray(predicates);
		query.where(predicates);
		List<Repayment> result = Repayment.em().createQuery(query).getResultList();
		return result;
	}
	
  
}
