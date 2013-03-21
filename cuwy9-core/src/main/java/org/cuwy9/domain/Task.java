package org.cuwy9.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Task extends MtlObject{
	@Override
	public String toString() {
		return "task="+getId()+"::taskName="+taskName;
	}
	@NotNull
	@Column(unique=true)
	@Size(min = 2, max = 30)
	String taskName;
	@ManyToOne
	private Task variant;

	public String getTaskName() { return taskName; }
	public void setTaskName(String taskName) { this.taskName = taskName; }
	public Task getVariant() { return variant; }
	public void setVariant(Task variant) { this.variant = variant; }
}
