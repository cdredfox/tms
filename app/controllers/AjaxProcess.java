package controllers;

import models.Car;
import models.Customer;
import play.Logger;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class AjaxProcess extends Controller {
	public static long saveCustomer(String name,String t){
		Logger.error("saveCustomer ajax请求开始，name:"+name);
		if(Customer.count("name=?", name)>0){
			return 0;
		}
		Customer customer=new Customer();
		customer.name=name;
		customer=customer.save();
		Logger.error("saveCustomer ajax请求结束，customer:"+customer);
		return customer.getId(); 
	}
	
	public static long saveCar(String carNO,String t){
		if(Car.count("cardNO=?", carNO)>0){
			return 0;
		}
		Car car=new Car();
		car.cardNO=carNO;
		car=car.save();
		return car.getId();
	}
	
}
