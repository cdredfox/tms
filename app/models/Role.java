package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Role extends Model {
	public String roleName;
	public String roleDesc;
	public Role(String roleName, String roleDesc) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}
	
	public Role() {
		super();
	}
	
}
