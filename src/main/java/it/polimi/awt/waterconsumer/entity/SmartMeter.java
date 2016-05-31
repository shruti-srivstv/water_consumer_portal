package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the smart_meter database table.
 * 
 */
@Entity
@Table(name="smart_meter")
@NamedQuery(name="SmartMeter.findAll", query="SELECT s FROM SmartMeter s")
public class SmartMeter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	@Column(name="smart_meter_id")
	private String smartMeterId;

	//bi-directional many-to-one association to Household
	@OneToMany(mappedBy="smartMeter")
	private List<Household> households;

	//bi-directional many-to-one association to MeterReading
	@OneToMany(mappedBy="smartMeter")
	private List<MeterReading> meterReadings;

	//bi-directional many-to-one association to Building
	@ManyToOne
	private Building building;

	public SmartMeter() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getSmartMeterId() {
		return this.smartMeterId;
	}

	public void setSmartMeterId(String smartMeterId) {
		this.smartMeterId = smartMeterId;
	}

	public List<Household> getHouseholds() {
		return this.households;
	}

	public void setHouseholds(List<Household> households) {
		this.households = households;
	}

	public Household addHousehold(Household household) {
		getHouseholds().add(household);
		household.setSmartMeter(this);

		return household;
	}

	public Household removeHousehold(Household household) {
		getHouseholds().remove(household);
		household.setSmartMeter(null);

		return household;
	}

	public List<MeterReading> getMeterReadings() {
		return this.meterReadings;
	}

	public void setMeterReadings(List<MeterReading> meterReadings) {
		this.meterReadings = meterReadings;
	}

	public MeterReading addMeterReading(MeterReading meterReading) {
		getMeterReadings().add(meterReading);
		meterReading.setSmartMeter(this);

		return meterReading;
	}

	public MeterReading removeMeterReading(MeterReading meterReading) {
		getMeterReadings().remove(meterReading);
		meterReading.setSmartMeter(null);

		return meterReading;
	}

	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

}