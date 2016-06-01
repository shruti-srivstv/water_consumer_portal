package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the device_consumption database table.
 * 
 */
@Entity
@Table(name="device_consumption")
@NamedQuery(name="DeviceConsumption.findAll", query="SELECT d FROM DeviceConsumption d")
public class DeviceConsumption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	@Column(name="device_consumption")
	private BigDecimal deviceConsumption;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to ComplexDeviceInstance
	@ManyToOne
	@JoinColumn(name="complex_device_instance_oid")
	private ComplexDeviceInstance complexDeviceInstance;

	//bi-directional many-to-one association to SimpleDeviceInstance
	@ManyToOne
	@JoinColumn(name="simple_device_instance_oid")
	private SimpleDeviceInstance simpleDeviceInstance;

	public DeviceConsumption() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public BigDecimal getDeviceConsumption() {
		return this.deviceConsumption;
	}

	public void setDeviceConsumption(BigDecimal deviceConsumption) {
		this.deviceConsumption = deviceConsumption;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public ComplexDeviceInstance getComplexDeviceInstance() {
		return this.complexDeviceInstance;
	}

	public void setComplexDeviceInstance(ComplexDeviceInstance complexDeviceInstance) {
		this.complexDeviceInstance = complexDeviceInstance;
	}

	public SimpleDeviceInstance getSimpleDeviceInstance() {
		return this.simpleDeviceInstance;
	}

	public void setSimpleDeviceInstance(SimpleDeviceInstance simpleDeviceInstance) {
		this.simpleDeviceInstance = simpleDeviceInstance;
	}

}