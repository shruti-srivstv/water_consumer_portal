package it.polimi.awt.waterconsumer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Household{
	@Id
	private Integer oid;	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	public Building getBuilding() {
		return building;
	}
	public void setBuilding_oid(Building building) {
		this.building = building;
	}
	
	@OneToOne
	@JoinColumn(name="building_oid", insertable=false, updatable=false)
	private Building building;
	
}