package com.model;

public class Admin {
	private Integer AdminId;
	private String AdminName;
	private String AdminPassword;
	public Integer getAdminId() {
		return AdminId;
	}
	public void setAdminId(Integer adminId) {
		AdminId = adminId;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public String getAdminPassword() {
		return AdminPassword;
	}
	public void setPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [AdminId=" + AdminId + ", AdminName=" + AdminName + ", AdminPassword=" + AdminPassword + "]";
	}
}
