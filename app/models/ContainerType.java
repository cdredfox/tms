package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class ContainerType extends Model {
	
	public String name;

	public ContainerType(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
