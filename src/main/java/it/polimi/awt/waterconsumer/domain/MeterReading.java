package it.polimi.awt.waterconsumer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="meter_reading")
public class MeterReading{
	@Id
	private int oid;

	private String company;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reading_date_time")
	private Date readingDateTime;

	@Column(name="total_consumption_adjusted")
	private Float totalConsumptionAdjusted;
	
	@Column(name="smart_meter_oid")
	private Integer smartMeterOid;
	
//	@ManyToOne
//	@JoinColumn(name="smart_meter_oid")
//	private SmartMeter smartMeter;
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getReadingDateTime() {
		return readingDateTime;
	}

	public void setReadingDateTime(Date readingDateTime) {
		this.readingDateTime = readingDateTime;
	}


	public Float getTotalConsumptionAdjusted() {
		return totalConsumptionAdjusted;
	}

	public void setTotalConsumptionAdjusted(Float totalConsumptionAdjusted) {
		this.totalConsumptionAdjusted = totalConsumptionAdjusted;
	}

//	public SmartMeter getSmartMeter() {
//		return smartMeter;
//	}
//
//	public void setSmartMeter(SmartMeter smartMeter) {
//		this.smartMeter = smartMeter;
//	}

}