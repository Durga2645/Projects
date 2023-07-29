package in.ineuron.service;

import java.util.List;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;

public interface IStudentService {
	public List<String> checkingStudentAvailability(StudentDto studentDto);
	public String registerStudent(StudentDto studentDto);
	public Integer getStudentId(StudentDto studentDto);
	public StudentDto validateStudent(StudentDto studentDto);
	public String updateStudentPassword(StudentDto studentDto);
	public String checkStudentPasswordAvailability(StudentDto studentDto);
	public String studentLoginCheck(StudentDto studentDto);
	public List<BookDto> fetchBookByName(String bookName);
	public List<BookDto> fetchBookByTitle(String bookTitle);
	public List<BookDto> fetchBookByAuthor(String bookAuthor);
	public List<BookDto> fetchBookByCategory(String bookCategory);
	public List<String[]> fetchStudentIssuedBooks(StudentDto studentDto);
	
}
