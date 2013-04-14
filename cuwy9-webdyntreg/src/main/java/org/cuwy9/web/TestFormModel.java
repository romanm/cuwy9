package org.cuwy9.web;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class TestFormModel {
	private String seek;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private DateTime birthDate;

	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}

	public String getSeek() {
		return seek;
	}

	public void setSeek(String seek) {
		this.seek = seek;
	}
}
