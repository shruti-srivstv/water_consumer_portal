package it.polimi.awt.waterconsumer.domain;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
//import javax.enterprise.context.RequestScoped;

@Entity
//@Scope("session")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer oid;
	
	@NotBlank(message = "{NotBlank.User.username}")
	private String username;
	
	@NotBlank(message = "{NotBlank.User.password}")
	private String password;
	
	private String zipcode;
	
	
	@OneToOne
	Neutral_User neutral_user;
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getUsername() {
		return username;
	}
	public void setIsbn(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
//	public Neutral_User getNeutral() {
//		return neutral_user;
//	}
	
}
