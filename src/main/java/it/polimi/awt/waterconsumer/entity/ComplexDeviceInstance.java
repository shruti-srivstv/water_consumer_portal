package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the complex_device_instance database table.
 * 
 */
@Entity
@Table(name="complex_device_instance")
@NamedQuery(name="ComplexDeviceInstance.findAll", query="SELECT c FROM ComplexDeviceInstance c")
public class ComplexDeviceInstance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	private byte ecomode;

	private String efficiency;

	private byte timer;

	//bi-directional many-to-one association to DeviceType
	@ManyToOne
	@JoinColumn(name="device_type_oid")
	private DeviceType deviceType;

	//bi-directional many-to-one association to Household
	@ManyToOne
	private Household household;

	//bi-directional many-to-one association to DeviceConsumption
	@OneToMany(mappedBy="complexDeviceInstance")
	private List<DeviceConsumption> deviceConsumptions;

	public ComplexDeviceInstance() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public byte getEcomode() {
		return this.ecomode;
	}

	public void setEcomode(byte ecomode) {
		this.ecomode = ecomode;
	}

	public String getEfficiency() {
		return this.efficiency;
	}

	public void setEfficiency(String efficiency) {
		this.efficiency = efficiency;
	}

	public byte getTimer() {
		return this.timer;
	}

	public void setTimer(byte timer) {
		this.timer = timer;
	}

	public DeviceType getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Household getHousehold() {
		return this.household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

	public List<DeviceConsumption> getDeviceConsumptions() {
		return this.deviceConsumptions;
	}

	public void setDeviceConsumptions(List<DeviceConsumption> deviceConsumptions) {
		this.deviceConsumptions = deviceConsumptions;
	}

	public DeviceConsumption addDeviceConsumption(DeviceConsumption deviceConsumption) {
		getDeviceConsumptions().add(deviceConsumption);
		deviceConsumption.setComplexDeviceInstance(this);

		return deviceConsumption;
	}

	public DeviceConsumption removeDeviceConsumption(DeviceConsumption deviceConsumption) {
		getDeviceConsumptions().remove(deviceConsumption);
		deviceConsumption.setComplexDeviceInstance(null);

		return deviceConsumption;
	}

}