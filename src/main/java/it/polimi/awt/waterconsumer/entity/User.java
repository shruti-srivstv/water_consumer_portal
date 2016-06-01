package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	@Column(name="birth_date")
	private String birthDate;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private int id;

	private byte internal;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	private String zipcode;

	//bi-directional one-to-one association to NeutralUser
	@OneToOne(mappedBy="user")
	private NeutralUser neutralUser1;

	//bi-directional many-to-one association to NeutralUser
	@ManyToOne
	@JoinColumn(name="user_oid")
	private NeutralUser neutralUser2;

	//bi-directional many-to-one association to NeutralUser
	@ManyToOne
	@JoinColumn(name="neutral_user_user_oid")
	private NeutralUser neutralUser3;

	public User() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getInternal() {
		return this.internal;
	}

	public void setInternal(byte internal) {
		this.internal = internal;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public NeutralUser getNeutralUser1() {
		return this.neutralUser1;
	}

	public void setNeutralUser1(NeutralUser neutralUser1) {
		this.neutralUser1 = neutralUser1;
	}

	public NeutralUser getNeutralUser2() {
		return this.neutralUser2;
	}

	public void setNeutralUser2(NeutralUser neutralUser2) {
		this.neutralUser2 = neutralUser2;
	}

	public NeutralUser getNeutralUser3() {
		return this.neutralUser3;
	}

	public void setNeutralUser3(NeutralUser neutralUser3) {
		this.neutralUser3 = neutralUser3;
	}

}