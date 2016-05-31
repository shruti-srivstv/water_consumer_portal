package it.polimi.awt.waterconsumer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SmartMeter{
	@Id
	private Integer oid;
	
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getSmartMeterId() {
		return smartMeterId;
	}

	public void setSmartMeterId(String smartMeterId) {
		this.smartMeterId = smartMeterId;
	}

	private String smartMeterId;
	
}