package org.cuwy9.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

@Entity
public class Folder extends MtlObject{
	@Override
	public String toString() {
		return "folder="+getId()+"::foldername="+foldername;
	}
	@NotNull
	@Column(unique=true)
	private String foldername;
	public String getFoldername() { return foldername; }
	public void setFoldername(String foldername) { this.foldername = foldername; }
	public static List<Folder> findAllFolder() {
		return entityManager().createQuery("SELECT o FROM Folder o", Folder.class).getResultList();
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
	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}
	public static Folder find(String folderName) {
		EntityManager em = new Info1().entityManager;
		System.out.println("folderName = "+folderName);
		Query q = em.createQuery("SELECT f FROM Folder f WHERE f.foldername=:foldername");
		q.setParameter("foldername", folderName);
		List<Folder> resultList = q.getResultList();
		System.out.println("resultList = "+resultList);
		return resultList.size()>0?resultList.get(0):null;
	}
}
