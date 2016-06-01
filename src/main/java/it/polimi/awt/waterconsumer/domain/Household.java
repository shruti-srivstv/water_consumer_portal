package it.polimi.awt.waterconsumer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Household{
	@Id
	private Integer oid;	
	private Integer smart_meter_oid;
	private Integer building_oid;
	
	public Integer getHouseHoldOid() {
		return oid;
	}
	
	public Integer getSmartMeterOid() {
		return smart_meter_oid;
	}
	
	public Integer getBuildingOid() {
		return building_oid;
	}
}