package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Message extends Model {
	public String title;
	public String context;
	@ManyToOne
	public User from;
	@ManyToOne
	public User to;
	public int status;
	public Date gmt_create;
	
	public Message(String title, String context, User from, User to,
			int status, Date gmt_create) {
		super();
		this.title = title;
		this.context = context;
		this.from = from;
		this.to = to;
		this.status = status;
		this.gmt_create = gmt_create;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
