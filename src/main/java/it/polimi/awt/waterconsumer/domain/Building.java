package it.polimi.awt.waterconsumer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Building{
	@Id
	private Integer oid;
	
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String address;
	
}