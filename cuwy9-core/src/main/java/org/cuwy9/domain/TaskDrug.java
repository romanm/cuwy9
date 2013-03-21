package org.cuwy9.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class TaskDrug extends MtlObject{
	@NotNull
	@ManyToOne
	private Drug drug;
	@NotNull
	@ManyToOne
	private Dose dose;
	@NotNull
	@ManyToOne
	private App app;

	public Drug getDrug() { return drug; }
	public void setDrug(Drug drug) { this.drug = drug; }
	public Dose getDose() { return dose; }
	public void setDose(Dose dose) { this.dose = dose; }
	public App getApp() { return app; }
	public void setApp(App app) { this.app = app; }
}
