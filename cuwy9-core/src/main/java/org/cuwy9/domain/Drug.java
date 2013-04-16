package org.cuwy9.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.cuwy9.reference.DrugType;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Drug extends MtlObject{
	public static List<Drug> findAllDrugs() {
		return entityManager().createQuery("SELECT o FROM Drug o", Drug.class).getResultList();
	}
	private Drug() {}
	public Drug(Node node) {
		setNode(node);
	}
	@Override
	public String toString() {
		return "drug::"+getId()+"::drugname="+drugname+"::generic="+(null==getGeneric()?null:getGeneric().getId());
	}
	@NotNull
	@Column(unique=true)
	@Size(min = 2, max = 30)
	private String drugname;

	@Enumerated
	private DrugType drugType;
	public String getDrugname() {
		return drugname;
	}
	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}
	@ManyToOne
	private Drug generic;
	public Drug getGeneric() {
		return generic;
	}
	public void setGeneric(Drug generic) {
		this.generic = generic;
	}
	@OneToMany(mappedBy="generic")
	private List<Drug> trades;
	public List<Drug> getTrades() {
		return trades;
	}

}
