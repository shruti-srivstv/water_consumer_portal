package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the neutral_user database table.
 * 
 */
@Entity
@Table(name="neutral_user")
@NamedQuery(name="NeutralUser.findAll", query="SELECT n FROM NeutralUser n")
public class NeutralUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_oid")
	private int userOid;

	private String currency;

	@Column(name="educational_level")
	private String educationalLevel;

	@Column(name="family_role")
	private String familyRole;

	@Column(name="house_holder")
	private byte houseHolder;

	@Column(name="household_id")
	private int householdId;

	@Column(name="income_rate")
	private String incomeRate;

	private String language;

	@Column(name="length_unit")
	private String lengthUnit;

	@Column(name="public")
	private byte public_;

	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;

	@Column(name="temperature_unit")
	private String temperatureUnit;

	//bi-directional many-to-one association to Household
	@ManyToOne
	private Household household;

	//bi-directional one-to-one association to User
	@OneToOne
	private User user;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="neutralUser2")
	private List<User> users1;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="neutralUser3")
	private List<User> users2;

	public NeutralUser() {
	}

	public int getUserOid() {
		return this.userOid;
	}

	public void setUserOid(int userOid) {
		this.userOid = userOid;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getEducationalLevel() {
		return this.educationalLevel;
	}

	public void setEducationalLevel(String educationalLevel) {
		this.educationalLevel = educationalLevel;
	}

	public String getFamilyRole() {
		return this.familyRole;
	}

	public void setFamilyRole(String familyRole) {
		this.familyRole = familyRole;
	}

	public byte getHouseHolder() {
		return this.houseHolder;
	}

	public void setHouseHolder(byte houseHolder) {
		this.houseHolder = houseHolder;
	}

	public int getHouseholdId() {
		return this.householdId;
	}

	public void setHouseholdId(int householdId) {
		this.householdId = householdId;
	}

	public String getIncomeRate() {
		return this.incomeRate;
	}

	public void setIncomeRate(String incomeRate) {
		this.incomeRate = incomeRate;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLengthUnit() {
		return this.lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	public byte getPublic_() {
		return this.public_;
	}

	public void setPublic_(byte public_) {
		this.public_ = public_;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getTemperatureUnit() {
		return this.temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	public Household getHousehold() {
		return this.household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public User addUsers1(User users1) {
		getUsers1().add(users1);
		users1.setNeutralUser2(this);

		return users1;
	}

	public User removeUsers1(User users1) {
		getUsers1().remove(users1);
		users1.setNeutralUser2(null);

		return users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public User addUsers2(User users2) {
		getUsers2().add(users2);
		users2.setNeutralUser3(this);

		return users2;
	}

	public User removeUsers2(User users2) {
		getUsers2().remove(users2);
		users2.setNeutralUser3(null);

		return users2;
	}

}