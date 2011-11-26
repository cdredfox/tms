package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class User extends Model {
	public String userName;
	public String passWord;
	
	public String name;
	public boolean status;
	public Date lastLogin;
	public String phone;
	public String mobilePhone;
	@ManyToOne
	public Role role;
	@ManyToOne
	public Company company;
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	
	public User(String userName, String passWord, boolean status) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
	}


	public User() {
		super();
	}

	
	
}
