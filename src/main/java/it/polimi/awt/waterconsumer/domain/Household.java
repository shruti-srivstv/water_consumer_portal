package it.polimi.awt.waterconsumer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Household{
	@Id
	private Integer oid;	
	
	@ManyToOne
	private Building building;
	
	@ManyToOne
	private SmartMeter smart_meter;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public SmartMeter getSmartMeter() {
		return smart_meter;
	}

	public void setSmartMeter(SmartMeter smartMeter) {
		this.smart_meter = smartMeter;
	}
	
}