package in.ineuron.bo;

public class AdminBo {
	private Integer adminId;
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	private Long adminMobilenumber;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Long getAdminMobilenumber() {
		return adminMobilenumber;
	}
	public void setAdminMobilenumber(Long adminMobilenumber) {
		this.adminMobilenumber = adminMobilenumber;
	}
	@Override
	public String toString() {
		return "AdminBo [adminId=" + adminId + ", adminName=" + adminName + ", adminEmail=" + adminEmail
				+ ", adminPassword=" + adminPassword + ", adminMobilenumber=" + adminMobilenumber + "]";
	}
	
}
