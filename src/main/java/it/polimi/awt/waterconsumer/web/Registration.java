package it.polimi.awt.waterconsumer.web;

import org.hibernate.validator.constraints.NotEmpty;

public class Registration{
	@NotEmpty(message="{NotEmpty.registration.housholdId}")
	private Integer householdId;
	
	private Integer buildingId;
	private Integer smartMeterId;
	private String zipcode;
	
	public Integer getHouseholdId() {
		return householdId;
	}
	public void setHouseholdId(Integer householdId) {
		this.householdId = householdId;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public Integer getSmartMeterId() {
		return smartMeterId;
	}
	public void setSmartMeterId(Integer smartMeterId) {
		this.smartMeterId = smartMeterId;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String username;
	private String password;
}