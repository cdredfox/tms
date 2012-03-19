package models;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import play.db.jpa.Model;

@MappedSuperclass
public class Entity extends Model {

	public boolean valid;
	public Date modifyDate;

	public Date getModifyDate() {
		return new Date();
	}
}
