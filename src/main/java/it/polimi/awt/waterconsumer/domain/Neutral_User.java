package it.polimi.awt.waterconsumer.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Neutral_User{
	@Id
	private Integer user_oid;	
	private Date registration_date;
	private Integer household_id;
	
	@OneToOne(mappedBy="neutral_user")
	List<User> users;
	
	public Neutral_User() {
		this.users = new ArrayList<User>();
	}
	
	public Integer getUserOid() {
		return user_oid;
	}
	
	public Date getRegistrationDate(){
		return registration_date;
	}
	public Integer getHousholdId(){
		return household_id;
	}
	
//	public List<User> getUsers() {
//		return users;
//	}
	
}