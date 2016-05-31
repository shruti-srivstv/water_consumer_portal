package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the module database table.
 * 
 */
@Entity
@NamedQuery(name="Module.findAll", query="SELECT m FROM Module m")
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	private String moduledomainname;

	private String moduleid;

	public Module() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getModuledomainname() {
		return this.moduledomainname;
	}

	public void setModuledomainname(String moduledomainname) {
		this.moduledomainname = moduledomainname;
	}

	public String getModuleid() {
		return this.moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

}