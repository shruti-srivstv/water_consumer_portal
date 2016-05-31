package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the simple_device_instance database table.
 * 
 */
@Entity
@Table(name="simple_device_instance")
@NamedQuery(name="SimpleDeviceInstance.findAll", query="SELECT s FROM SimpleDeviceInstance s")
public class SimpleDeviceInstance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	@Column(name="device_type_oid")
	private int deviceTypeOid;

	@Column(name="household_oid")
	private int householdOid;

	private int number;

	//bi-directional many-to-one association to DeviceConsumption
	@OneToMany(mappedBy="simpleDeviceInstance")
	private List<DeviceConsumption> deviceConsumptions;

	public SimpleDeviceInstance() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getDeviceTypeOid() {
		return this.deviceTypeOid;
	}

	public void setDeviceTypeOid(int deviceTypeOid) {
		this.deviceTypeOid = deviceTypeOid;
	}

	public int getHouseholdOid() {
		return this.householdOid;
	}

	public void setHouseholdOid(int householdOid) {
		this.householdOid = householdOid;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<DeviceConsumption> getDeviceConsumptions() {
		return this.deviceConsumptions;
	}

	public void setDeviceConsumptions(List<DeviceConsumption> deviceConsumptions) {
		this.deviceConsumptions = deviceConsumptions;
	}

	public DeviceConsumption addDeviceConsumption(DeviceConsumption deviceConsumption) {
		getDeviceConsumptions().add(deviceConsumption);
		deviceConsumption.setSimpleDeviceInstance(this);

		return deviceConsumption;
	}

	public DeviceConsumption removeDeviceConsumption(DeviceConsumption deviceConsumption) {
		getDeviceConsumptions().remove(deviceConsumption);
		deviceConsumption.setSimpleDeviceInstance(null);

		return deviceConsumption;
	}

}