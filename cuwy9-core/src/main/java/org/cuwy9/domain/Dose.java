package org.cuwy9.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.cuwy9.reference.DoseProType;
import org.cuwy9.reference.DoseType;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Dose  extends MtlObject{
	private Dose(){};
	public Dose(Node node){
		setNode(node);
	};
	@Override
	public String toString() {
		return "dose::"+getId()
				+"::doseunit="+doseunit
				+"::dosevalue="+dosevalue
				;
	}
	private String doseunit;
	private double dosevalue;
	@NotNull
    @Enumerated
    private DoseType doseType=DoseType.Definition;
	@NotNull
    @Enumerated
    private DoseProType doseProType=DoseProType.Once;

	public DoseProType getDoseProType() {
		return doseProType;
	}
	public void setDoseProType(DoseProType doseProType) {
		this.doseProType = doseProType;
	}
	public DoseType getDoseType() {
		return doseType;
	}
	public void setDoseType(DoseType doseType) {
		this.doseType = doseType;
	}
	public String getDoseunit() {
		return doseunit;
	}
	public void setDoseunit(String doseunit) {
		this.doseunit = doseunit;
	}
	public double getDosevalue() {
		return dosevalue;
	}
	public void setDosevalue(double dosevalue) {
		this.dosevalue = dosevalue;
	}
}
