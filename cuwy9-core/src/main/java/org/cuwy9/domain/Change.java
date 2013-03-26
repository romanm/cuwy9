package org.cuwy9.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author roman
 * Art of change:
 * DELETE -- 
 * change.node_id=node.id, node.dad_id -> change.dad_id, -> node.dad_id=null
 * change.sort=node.sort, node.sort=now()
 * UPDATE_LINK -- 
 * (for node(id!=link_id))
 * change.node_id=node.id, node.link_id ->  change.link_id, -> node.link_id=new value
 * change.sort=node.sort, node.sort=now()
 * UPDATE_VALUE -- 
 * (for node(id=link_id))
 * change.sort=node.sort, node.sort=now()
 * 
 */
@Configurable
@Entity
public class Change extends MtlObject{
	/**
	 * Old dad for DELETE node.
	 */
	@ManyToOne
	@JoinColumn(name="dad_id")
	private Node dad;
	/**
	 * Node that has changed.
	 * DELETE -- node_id=id
	 */
	@ManyToOne
	@JoinColumn(name="node_id")
	private Node node;
	@Column(columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = org.cuwy9.reference.JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime sort = new DateTime();
	
	public DateTime getSort() { return sort; }
	public void setSort(DateTime sort) { this.sort = sort; }
	public Node getDad() { return dad;}
	public void setDad(Node dad) { this.dad = dad;}
	public Node getNode() { return node; }
	public void setNode(Node node) { this.node = node;}
	
//	migrate to node
//	@ManyToOne
//	@JoinColumn(name="link_id")
//	private Node link;
//	public Node getLink() { return link; }
//	public void setLink(Node link) { this.link = link;}
}
