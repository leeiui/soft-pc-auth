package org.soft.pc.auth.uenum;

public enum RoleEnum implements AuthEnum {
	ADMIN(1), MANAGER(2), STAFF(3);

	private Integer rid;
	private RoleEnum(Integer rid) {
		this.rid = rid;
	}
	@Override
	public String getCode() {
		return this.rid.toString();
	}

}
