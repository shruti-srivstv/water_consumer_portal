package it.polimi.awt.waterconsumer.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="smart_meter")
public class SmartMeter{
	@Id
	private Integer oid;
	
	@Column(name="smart_meter_id")
	private String smartMeterId;
	
	@ManyToOne
	@JoinColumn(name = "building_oid")
	private Building building;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "smart_meter")
	private List<MeterReading> meterReadings;


	@Override
	public String toString() {
		return "SmartMeter [meterReadings=" + meterReadings + "]";
	}

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

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public List<MeterReading> getMeterReadings() {
		return meterReadings;
	}

	public void setMeterReadings(List<MeterReading> meterReadings) {
		this.meterReadings = meterReadings;
	}
	
}