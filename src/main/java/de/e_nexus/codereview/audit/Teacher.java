package de.e_nexus.codereview.audit;
// Generated Aug 24, 2020, 12:52:23 PM by Hibernate Tools 5.4.19.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Teacher generated by hbm2java
 */
@Entity
@Table(name = "teacher", schema = "public")
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Teacher implements java.io.Serializable {

	private int id;
	private String name;

	public Teacher() {
	}

	public Teacher(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
