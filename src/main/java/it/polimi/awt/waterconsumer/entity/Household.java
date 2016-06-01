package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the household database table.
 * 
 */
@Entity
@NamedQuery(name="Household.findAll", query="SELECT h FROM Household h")
public class Household implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	@Column(name="children0_4")
	private int children04;

	@Column(name="children5_9")
	private int children59;

	private int children9;

	@Column(name="environmental_attitude")
	private String environmentalAttitude;

	@Column(name="family_id")
	private String familyId;

	@Column(name="household_garden")
	private byte householdGarden;

	@Column(name="household_garden_area")
	private BigDecimal householdGardenArea;

	@Column(name="household_pool")
	private byte householdPool;

	@Column(name="household_pool_volume")
	private BigDecimal householdPoolVolume;

	@Column(name="household_size")
	private BigDecimal householdSize;

	@Column(name="irrigation_system")
	private byte irrigationSystem;

	@Column(name="number_adults")
	private int numberAdults;

	@Column(name="number_bathrooms")
	private String numberBathrooms;

	@Column(name="number_pets")
	private int numberPets;

	private byte ownership;

	@Column(name="public")
	private byte public_;

	@Column(name="residency_type")
	private String residencyType;

	private byte second;

	private byte visible;

	//bi-directional many-to-one association to Building
	@ManyToOne
	private Building building;

	//bi-directional many-to-one association to SmartMeter
	@ManyToOne
	@JoinColumn(name="smart_meter_oid")
	private SmartMeter smartMeter;

	//bi-directional many-to-one association to NeutralUser
	@OneToMany(mappedBy="household")
	private List<NeutralUser> neutralUsers;

	//bi-directional many-to-one association to ComplexDeviceInstance
	@OneToMany(mappedBy="household")
	private List<ComplexDeviceInstance> complexDeviceInstances;

	public Household() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getChildren04() {
		return this.children04;
	}

	public void setChildren04(int children04) {
		this.children04 = children04;
	}

	public int getChildren59() {
		return this.children59;
	}

	public void setChildren59(int children59) {
		this.children59 = children59;
	}

	public int getChildren9() {
		return this.children9;
	}

	public void setChildren9(int children9) {
		this.children9 = children9;
	}

	public String getEnvironmentalAttitude() {
		return this.environmentalAttitude;
	}

	public void setEnvironmentalAttitude(String environmentalAttitude) {
		this.environmentalAttitude = environmentalAttitude;
	}

	public String getFamilyId() {
		return this.familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public byte getHouseholdGarden() {
		return this.householdGarden;
	}

	public void setHouseholdGarden(byte householdGarden) {
		this.householdGarden = householdGarden;
	}

	public BigDecimal getHouseholdGardenArea() {
		return this.householdGardenArea;
	}

	public void setHouseholdGardenArea(BigDecimal householdGardenArea) {
		this.householdGardenArea = householdGardenArea;
	}

	public byte getHouseholdPool() {
		return this.householdPool;
	}

	public void setHouseholdPool(byte householdPool) {
		this.householdPool = householdPool;
	}

	public BigDecimal getHouseholdPoolVolume() {
		return this.householdPoolVolume;
	}

	public void setHouseholdPoolVolume(BigDecimal householdPoolVolume) {
		this.householdPoolVolume = householdPoolVolume;
	}

	public BigDecimal getHouseholdSize() {
		return this.householdSize;
	}

	public void setHouseholdSize(BigDecimal householdSize) {
		this.householdSize = householdSize;
	}

	public byte getIrrigationSystem() {
		return this.irrigationSystem;
	}

	public void setIrrigationSystem(byte irrigationSystem) {
		this.irrigationSystem = irrigationSystem;
	}

	public int getNumberAdults() {
		return this.numberAdults;
	}

	public void setNumberAdults(int numberAdults) {
		this.numberAdults = numberAdults;
	}

	public String getNumberBathrooms() {
		return this.numberBathrooms;
	}

	public void setNumberBathrooms(String numberBathrooms) {
		this.numberBathrooms = numberBathrooms;
	}

	public int getNumberPets() {
		return this.numberPets;
	}

	public void setNumberPets(int numberPets) {
		this.numberPets = numberPets;
	}

	public byte getOwnership() {
		return this.ownership;
	}

	public void setOwnership(byte ownership) {
		this.ownership = ownership;
	}

	public byte getPublic_() {
		return this.public_;
	}

	public void setPublic_(byte public_) {
		this.public_ = public_;
	}

	public String getResidencyType() {
		return this.residencyType;
	}

	public void setResidencyType(String residencyType) {
		this.residencyType = residencyType;
	}

	public byte getSecond() {
		return this.second;
	}

	public void setSecond(byte second) {
		this.second = second;
	}

	public byte getVisible() {
		return this.visible;
	}

	public void setVisible(byte visible) {
		this.visible = visible;
	}

	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public SmartMeter getSmartMeter() {
		return this.smartMeter;
	}

	public void setSmartMeter(SmartMeter smartMeter) {
		this.smartMeter = smartMeter;
	}

	public List<NeutralUser> getNeutralUsers() {
		return this.neutralUsers;
	}

	public void setNeutralUsers(List<NeutralUser> neutralUsers) {
		this.neutralUsers = neutralUsers;
	}

	public NeutralUser addNeutralUser(NeutralUser neutralUser) {
		getNeutralUsers().add(neutralUser);
		neutralUser.setHousehold(this);

		return neutralUser;
	}

	public NeutralUser removeNeutralUser(NeutralUser neutralUser) {
		getNeutralUsers().remove(neutralUser);
		neutralUser.setHousehold(null);

		return neutralUser;
	}

	public List<ComplexDeviceInstance> getComplexDeviceInstances() {
		return this.complexDeviceInstances;
	}

	public void setComplexDeviceInstances(List<ComplexDeviceInstance> complexDeviceInstances) {
		this.complexDeviceInstances = complexDeviceInstances;
	}

	public ComplexDeviceInstance addComplexDeviceInstance(ComplexDeviceInstance complexDeviceInstance) {
		getComplexDeviceInstances().add(complexDeviceInstance);
		complexDeviceInstance.setHousehold(this);

		return complexDeviceInstance;
	}

	public ComplexDeviceInstance removeComplexDeviceInstance(ComplexDeviceInstance complexDeviceInstance) {
		getComplexDeviceInstances().remove(complexDeviceInstance);
		complexDeviceInstance.setHousehold(null);

		return complexDeviceInstance;
	}

}