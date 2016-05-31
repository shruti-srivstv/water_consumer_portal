package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the device_type database table.
 * 
 */
@Entity
@Table(name="device_type")
@NamedQuery(name="DeviceType.findAll", query="SELECT d FROM DeviceType d")
public class DeviceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	@Lob
	private String icon;

	private String name;

	private String type;

	//bi-directional many-to-one association to ComplexDeviceInstance
	@OneToMany(mappedBy="deviceType")
	private List<ComplexDeviceInstance> complexDeviceInstances;

	public DeviceType() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ComplexDeviceInstance> getComplexDeviceInstances() {
		return this.complexDeviceInstances;
	}

	public void setComplexDeviceInstances(List<ComplexDeviceInstance> complexDeviceInstances) {
		this.complexDeviceInstances = complexDeviceInstances;
	}

	public ComplexDeviceInstance addComplexDeviceInstance(ComplexDeviceInstance complexDeviceInstance) {
		getComplexDeviceInstances().add(complexDeviceInstance);
		complexDeviceInstance.setDeviceType(this);

		return complexDeviceInstance;
	}

	public ComplexDeviceInstance removeComplexDeviceInstance(ComplexDeviceInstance complexDeviceInstance) {
		getComplexDeviceInstances().remove(complexDeviceInstance);
		complexDeviceInstance.setDeviceType(null);

		return complexDeviceInstance;
	}

}