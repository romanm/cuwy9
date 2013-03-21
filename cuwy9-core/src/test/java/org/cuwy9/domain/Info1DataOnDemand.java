package org.cuwy9.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
@Configurable
@Component
public class Info1DataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Info1> data;

	public Info1 getNewTransientInfo1(int index) {
		Info1 obj = new Info1();
		setInfoName(obj, index);
		return obj;
	}

	public void setInfoName(Info1 obj, int index) {
		String infoName = "infoName_" + index;
		obj.setInfoName(infoName);
	}

	public Info1 getSpecificInfo1(int index) {
		init();
		if (index < 0) {
			index = 0;
		}
		if (index > (data.size() - 1)) {
			index = data.size() - 1;
		}
		Info1 obj = data.get(index);
		Long id = obj.getId();
		return Info1.findInfo1(id);
	}

	public Info1 getRandomInfo1() {
		init();
		Info1 obj = data.get(rnd.nextInt(data.size()));
		Long id = obj.getId();
		return Info1.findInfo1(id);
	}

	public boolean modifyInfo1(Info1 obj) {
		return false;
	}

	public void init() {
		int from = 0;
		int to = 10;
		data = Info1.findInfo1Entries(from, to);
		if (data == null) {
			throw new IllegalStateException("Find entries implementation for 'Info1' illegally returned null");
		}
		if (!data.isEmpty()) {
			return;
		}

		data = new ArrayList<Info1>();
		for (int i = 0; i < 10; i++) {
			Info1 obj = getNewTransientInfo1(i);
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
	}

}
