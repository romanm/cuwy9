package org.cuwy9.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

@Entity
public class Patient extends MtlObject{
	private Patient() {}
	public Patient(Node node) {
		setNode(node);
	}
	@Override
	public String toString() {
		return "patient="+getId()+"::"+personalName+", "+familyName
				+" ::sex="+sex;
	}
	@NotNull
	String familyName;
	@NotNull
	String personalName;
	@NotNull
	@Size(min = 1, max=1)
	private String sex;
	
	@Column(columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = org.cuwy9.reference.JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime birthdate;

	public DateTime getBirthdate() { return birthdate; }
	public void setBirthdate(DateTime birthdate) { this.birthdate = birthdate; }
	public String getSex() { return sex; }
	public void setSex(String sex) { this.sex = sex; }
	public String getPersonalName() { return personalName; }
	public void setPersonalName(String personalName) { this.personalName = personalName; }
	public String getFamilyName() { return familyName; }
	public void setFamilyName(String familyName) { this.familyName = familyName; }
	
	public static List<Patient> findAllPatient() {
		return entityManager().createQuery("SELECT o FROM Patient o", Patient.class).getResultList();
	}
//	@PersistenceContext
//	transient EntityManager entityManager;
//	public static final EntityManager entityManager() {
//		EntityManager em = new Node().entityManager;
//		if (em == null) throw 
//		new IllegalStateException("Entity manager has not been injected (is the Spring " +
//				"Aspects JAR configured as an AJC/AJDT aspects library?)");
//		return em;
//	}

}
