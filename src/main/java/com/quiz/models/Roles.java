package com.quiz.models;

public enum Roles {
	ADMIN("ADMIN"), USER("USER");

	private final String roleCode;

	Roles(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	 

	// Level level = Level.HIGH;level.getLevelCode()

}
