package it.polimi.awt.waterconsumer.domain;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer oid;
	
	@NotBlank(message = "{NotBlank.User.username}")
	private String username;
	
	@NotBlank(message = "{NotBlank.User.password}")
	private String password;
	
	//@NotNull(message = "{NotNull.book.publishDate")
	//@DateTimeFormat(pattern = "MM/dd/yyyy")
	//@Past(message = "{Past.book.publishDate")
	//private Date publishDate;
	
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
	
}
