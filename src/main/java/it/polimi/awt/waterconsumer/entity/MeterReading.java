package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the meter_reading database table.
 * 
 */
@Entity
@Table(name="meter_reading")
@NamedQuery(name="MeterReading.findAll", query="SELECT m FROM MeterReading m")
public class MeterReading implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	private String company;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reading_date_time")
	private Date readingDateTime;

	@Column(name="total_consumption")
	private BigDecimal totalConsumption;

	@Column(name="total_consumption_adjusted")
	private BigDecimal totalConsumptionAdjusted;

	//bi-directional many-to-one association to SmartMeter
	@ManyToOne
	@JoinColumn(name="smart_meter_oid")
	private SmartMeter smartMeter;

	public MeterReading() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getReadingDateTime() {
		return this.readingDateTime;
	}

	public void setReadingDateTime(Date readingDateTime) {
		this.readingDateTime = readingDateTime;
	}

	public BigDecimal getTotalConsumption() {
		return this.totalConsumption;
	}

	public void setTotalConsumption(BigDecimal totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	public BigDecimal getTotalConsumptionAdjusted() {
		return this.totalConsumptionAdjusted;
	}

	public void setTotalConsumptionAdjusted(BigDecimal totalConsumptionAdjusted) {
		this.totalConsumptionAdjusted = totalConsumptionAdjusted;
	}

	public SmartMeter getSmartMeter() {
		return this.smartMeter;
	}

	public void setSmartMeter(SmartMeter smartMeter) {
		this.smartMeter = smartMeter;
	}

}