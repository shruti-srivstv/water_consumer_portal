package it.polimi.awt.waterconsumer.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the consumer_segment database table.
 * 
 */
@Entity
@Table(name="consumer_segment")
@NamedQuery(name="ConsumerSegment.findAll", query="SELECT c FROM ConsumerSegment c")
public class ConsumerSegment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int oid;

	private String description;

	private String name;

	public ConsumerSegment() {
	}

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}