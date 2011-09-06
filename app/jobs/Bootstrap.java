package jobs;

import models.ContainerType;
import models.Place;
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
		if(User.count()==0){
			new User("admin","snnuiabc").save();
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
