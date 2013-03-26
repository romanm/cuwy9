package org.cuwy9.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class MtlObject {
	@Id
	private Long id;
	public Long getId() {return id;}
//	public void setId(Long id) {this.id = id;}
	@OneToOne
	@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
	private Node node;
	protected void setNode(Node node) {
		this.node = node;
		id=node.getId();
		node.setMtlObject(this);
	}
	public Node getNode() {return node;}
}
