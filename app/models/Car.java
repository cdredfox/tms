package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Car extends Model {
	
	public String cardNO;
	
	@Override
	public String toString() {
		return cardNO;
	}
}
