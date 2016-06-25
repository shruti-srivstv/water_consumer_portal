package it.polimi.awt.waterconsumer.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Building{
	@Id
	private Integer oid;
	
	@OneToMany(mappedBy="building")
	private List<SmartMeter> smartMeters;
	
	@ManyToOne
	private District district;
	
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

	public List<SmartMeter> getSmartMeters() {
		return smartMeters;
	}

	public void setSmartMeters(List<SmartMeter> smartMeters) {
		this.smartMeters = smartMeters;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	private String address;
}