package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Place extends Model {
	
	public String name;

	public Place(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
}
