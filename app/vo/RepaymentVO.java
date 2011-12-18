package vo;

import java.math.BigDecimal;
import java.util.Date;

import models.Repayment;

public class RepaymentVO {
	
	public Long id;
	public BigDecimal amount=new BigDecimal(0);
	public BigDecimal chargeAmount=new BigDecimal(0);
	public Date finashDate;
	public char status;
	public Date waybillCreateDate;
	public String customerName;
	public String warehouseNO;
	public String containerType;
	public String containerNO;
	public String extractPlace;
	public String wardrobeAddress;
	public String backPlace;
	public String carNO;
	public String waybillMemo;
	/** 未销账金额（还可以收多少钱) */
	public BigDecimal arrearageAmount=new BigDecimal(0);
	public static RepaymentVO convert(Repayment repayment){
		RepaymentVO vo=new RepaymentVO();
		vo.amount=repayment.amount;
		vo.chargeAmount=repayment.chargedAmount;
		vo.finashDate=repayment.finashDate;
		vo.id=repayment.id;
		vo.status=repayment.status;
		vo.backPlace=repayment.waybill.BackPlace.name;
		vo.carNO=repayment.waybill.car.cardNO;
		vo.containerNO=repayment.waybill.ContainerNO;
		vo.containerType=repayment.waybill.containerType.name;
		vo.customerName=repayment.waybill.customer.name;
		vo.extractPlace=repayment.waybill.ExtractPlace.name;
		vo.wardrobeAddress=repayment.waybill.wardrobeAddress;
		vo.warehouseNO=repayment.waybill.warehouseNO;
		vo.waybillCreateDate=repayment.waybill.createDate;
		vo.waybillMemo=repayment.waybill.memo;	
		vo.arrearageAmount=vo.amount;
		if(vo.chargeAmount!=null){
			vo.arrearageAmount=repayment.amount.subtract(vo.chargeAmount);
		}
		return vo;
		
	}

}
