package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class User extends Model {
	public String userName;
	public String passWord;
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	public User() {
		super();
	}

	
	
}
