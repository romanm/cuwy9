package org.cuwy9.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
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
	public static List<Task> findAllTask() {
		return entityManager().createQuery("SELECT o FROM Task o", Task.class).getResultList();
	}
	@PersistenceContext
	transient EntityManager entityManager;
	public static final EntityManager entityManager() {
		EntityManager em = new Node().entityManager;
		if (em == null) throw 
		new IllegalStateException("Entity manager has not been injected (is the Spring " +
				"Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
}
