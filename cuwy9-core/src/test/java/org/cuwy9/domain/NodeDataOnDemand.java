package org.cuwy9.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class NodeDataOnDemand {
	protected final Log log = LogFactory.getLog(getClass());
	private List<Node> data;
	
	protected Node persistNode() {
		Node node = new Node();
//		node.setTag(TagType.Folder);
		node.persist();
//		node.flush();
//		node.setLink(node);
		log.debug("persistNode=="+node);
		return node;
	}
	private Random rnd = new SecureRandom();
	public Node getRandomNode() {
		System.out.println("---------------");
		init();
		System.out.println("---------------");
		Node obj = data.get(rnd.nextInt(data.size()));
		Long id = obj.getId();
		return Node.findNode(id);
	}
	
	public void init() {
		int from = 0;
		int to = 10;
		data = Node.findInfo2Entries(from, to);
		if (data == null) {
			throw new IllegalStateException("Find entries implementation for 'Info2' illegally returned null");
		}
		if (!data.isEmpty()) {
			return;
		}

		data = new ArrayList<Node>();
		for (int i = 0; i < 10; i++) {
			Node obj = getNewTransientNode(i);
			try {
				obj.persist();
			} catch (ConstraintViolationException e) {
				StringBuilder msg = new StringBuilder();
				for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
					ConstraintViolation<?> cv = iter.next();
					msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
				}
				throw new RuntimeException(msg.toString(), e);
			}
			obj.flush();
			data.add(obj);
		}
		log.debug(data);
	}
	public Node getNewTransientNode(int index) {
		Node obj = new Node();
		return obj;
	}
}
