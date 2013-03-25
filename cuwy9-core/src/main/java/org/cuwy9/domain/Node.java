package org.cuwy9.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuwy9.reference.DrugType;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class Node {
	@Transient
	protected final Log log = LogFactory.getLog(getClass());
	public Log getLog() {
		return log;
	}
	@Override
	public String toString() {
		return "node="+id+"::"+id
				+"::dad_id="+(dad==null?null:dad.getId())
				+"::link_id="+(link==null?null:link.getId())
				;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id; 
	public Long getId() { return id; } 
	public void setId(Long id) { this.id = id; } 
	@ManyToOne
	@JoinColumn(name="dad_id")
	private Node dad;
	public Node getDad() { return dad; } 
	public void setDad(Node dad) { this.dad = dad; } 
	@OneToMany(mappedBy="dad")
	private List<Node> childs; 
	public List<Node> getChilds() { return childs; } 
	public void setChilds(List<Node> childs) { this.childs = childs; }
	@ManyToOne
	@JoinColumn(name="link_id")
	private Node link; 
	public Node getLink() { return link; }
	public void setLink(Node link) { this.link = link; }

	@OneToOne(mappedBy = "node")
	private Folder folder;
	public Folder getFolder(){return folder;}
	@OneToOne(mappedBy = "node")
	private Task task;
	public Task getTask() {return task;}
	@OneToOne(mappedBy = "node")
	private Drug drug;
	public Drug getDrug() {return drug;}
	@OneToOne(mappedBy = "node")
	private Dose dose;
	public Dose getDose() {return dose;}
	@OneToOne(mappedBy = "node")
	private App app;
	public App getApp() {return app;}
	@OneToOne(mappedBy = "node")
	private TaskDrug taskDrug;
	public TaskDrug getTaskDrug() {return taskDrug;}
	@OneToOne(mappedBy = "node")
	private Patient patient;	
	public Patient getPatient() {return patient;}
	
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
	public void flush() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.flush();
	}
	public static Node  findNode(Long id) {
		if (id == null) return null;
		return entityManager().find(Node.class, id);
	}
	public static List<Node>  findInfo2Entries(int firstResult, int maxResults) {
		System.out.println("---------- 11");
		EntityManager entityManager2 = entityManager();
		System.out.println("---------- 12 "+entityManager2);
		return entityManager2.createQuery("SELECT o FROM Node o", Node.class)
				.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}
	public static long countNodes() {
		return entityManager().createQuery("SELECT COUNT(o) FROM Node o", Long.class).getSingleResult();
	}
	public static Node persistNode() {
			Node node = new Node();
//			node.setTag(TagType.Folder);
			node.persist();
//			node.flush();
//			node.setLink(node);
			node.log.debug("persistNode=="+node);
			return node;
	}
}
