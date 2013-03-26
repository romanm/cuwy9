package org.cuwy9.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class App extends MtlObject{
	private App() {}
	public App(Node node) {
		setNode(node);
	}
	@Override
	public String toString() {
		return "app::"+getId()
				+"::msinfusion="+msinfusion
				+"::unit="+unit
				;
	}
	@NotNull
	String unit="";
	Integer msinfusion=1;
	public String getUnit(){return unit;}
	public void setUnit(String unit){this.unit = unit;}
	public Integer getMsinfusion(){return msinfusion;}
	public void setMsinfusion(Integer msinfusion){this.msinfusion = msinfusion;}
}
