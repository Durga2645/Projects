package in.ineuron.dao;

import java.util.List;

import in.ineuron.bo.BookBo;
import in.ineuron.bo.StudentBo;
import in.ineuron.dto.StudentDto;

public interface IStudentDao {
	public List<StudentBo> fetchAllStudents();
	public String registerStudent(StudentDto studentDto);
	public StudentBo fetchStudentIdOrNameOrPassword(StudentDto studentDto);
	public String updateStudentPassword(StudentDto studentDto);
	public String fetchStudentByEmailAndPassword(StudentDto studentDto);
	public List<BookBo> fetchBookByName(String bookName);
	public List<BookBo> fetchBookByTitle(String bookTitle);
	public List<BookBo> fetchBookByAuthor(String bookAuthor);
	public List<BookBo> fetchBookByCategory(String bookCategory);
	public List<String[]> fetchStudentIssuedBooks(StudentDto studentDto);
	
}
