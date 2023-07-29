package in.ineuron.bo;

public class StudentBo {
	private Integer studentId;
	private String studentName;
	private String studentEmail;
	private String studentDepartment;
	private String studentPassword;
	private Long studentMobilenumber;
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentDepartment() {
		return studentDepartment;
	}
	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public Long getStudentMobilenumber() {
		return studentMobilenumber;
	}
	public void setStudentMobilenumber(Long studentMobilenumber) {
		this.studentMobilenumber = studentMobilenumber;
	}
	@Override
	public String toString() {
		return "StudentBo [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentDepartment=" + studentDepartment + ", studentPassword=" + studentPassword
				+ ", studentMobilenumber=" + studentMobilenumber + "]";
	}
	
}
