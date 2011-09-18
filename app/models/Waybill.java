package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import net.sf.oval.constraint.Length;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Waybill extends Model {
	@Required
	public Date createDate;
	@Required
	@ManyToOne
	public Customer customer;
	@ManyToOne
	public Car car;
	public String warehouseNO;
	public String ContainerNO;
	public String SealNO;
	@ManyToOne
	@Required
	public Place ExtractPlace;
	@ManyToOne
	@Required
	public Place BackPlace;
	@ManyToOne
	@Required
	public ContainerType containerType;
	//装柜地址
	public String wardrobeAddress;
	//拖车费
	public BigDecimal trailerFee;
	public BigDecimal realTrailerFee;
	//异提费
	public BigDecimal provideDifferentFee;
	public BigDecimal realProvideDifferentFee;
	public BigDecimal portFee;
	public BigDecimal printFee;
	//压车费
	public BigDecimal pressureCarFee;
	public BigDecimal realPressureCarFee;
	public BigDecimal otherFee;
	public BigDecimal totalFee;
	public BigDecimal realTotalFee;
	public String memo;
	/**
	 * 客户工作单号
	 */
	public String customerWorkNO;
	
}
