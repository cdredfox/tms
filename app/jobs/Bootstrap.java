package jobs;

import java.util.Date;

import models.Company;
import models.ContainerType;
import models.Message;
import models.Place;
import models.Role;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
@OnApplicationStart
public class Bootstrap extends Job {

	@Override
	public void doJob() throws Exception {
		if(Place.count()==0){
			initPlace();
		}
		if(ContainerType.count()==0){
			initContainerType();
		}
		if(Role.count()==0){
			new Role("普通用户","普通用户,过渡后删除").save();
		}
		
		if(Company.count()==0){
			new Company("深圳市众泰物流有限公司").save();
		}
		if(User.count()==0){
			Company company=Company.findById((long)1);
			Role role=Role.findById((long)1);
			User user = new User("admin","snnuiabc",true);
			user.role=role;
			user.company=company;
			user.save();
		}else if(User.count()==1){
			User user =User.findById((long)1);
			if(user.role==null){
				Role role=Role.findById((long)1);
				user.role=role;
			}
			if(user.company==null){
				Company company=Company.findById((long)1);
				user.company=company;
			}
			user.save();
			//测试
			Company company=Company.findById((long)1);
			Role role=Role.findById((long)1);
			user = new User("fei.yang","snnuiabc",true);
			user.role=role;
			user.company=company;
			user.save();
		}
		
		
	}

	private void initPlace(){
		new Place("SCT").save();
		new Place("CCT").save();
		new Place("蛇口").save();
		new Place("南集部").save();
		new Place("妈湾O号").save();
		new Place("大铲湾").save();
		new Place("华南物流").save();
		new Place("盐田").save();
		new Place("异地").save();
	}
	
	private void initContainerType(){
		new ContainerType("20").save();
		new ContainerType("40").save();
		new ContainerType("45").save();
		new ContainerType("48").save();
	}
}
