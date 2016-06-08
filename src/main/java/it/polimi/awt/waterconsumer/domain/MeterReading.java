package it.polimi.awt.waterconsumer.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@Column(name="total_consumption")
	private BigDecimal totalConsumption;

	@Column(name="total_consumption_adjusted")
	private BigDecimal totalConsumptionAdjusted;
	
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

	public BigDecimal getTotalConsumption() {
		return totalConsumption;
	}

	public void setTotalConsumption(BigDecimal totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	public BigDecimal getTotalConsumptionAdjusted() {
		return totalConsumptionAdjusted;
	}

	public void setTotalConsumptionAdjusted(BigDecimal totalConsumptionAdjusted) {
		this.totalConsumptionAdjusted = totalConsumptionAdjusted;
	}

}