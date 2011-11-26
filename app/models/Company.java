package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Company extends Model {
	public String name;

	public Company(String name) {
		super();
		this.name = name;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
