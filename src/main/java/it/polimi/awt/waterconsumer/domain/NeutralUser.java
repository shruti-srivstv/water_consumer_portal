package it.polimi.awt.waterconsumer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="neutral_user")
public class NeutralUser{
	@Id
	@Column(name="user_oid")
	private int userOid;
	
	@OneToOne
	@JoinColumn(name="household_oid", insertable=false, updatable=false)
	private Household household;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	public int getUserOid() {
		return userOid;
	}
	public void setUserOid(int userOid) {
		this.userOid = userOid;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistration_date(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Household getHousehold() {
		return household;
	}
	public void setHousehold(Household household) {
		this.household = household;
	}
	
}