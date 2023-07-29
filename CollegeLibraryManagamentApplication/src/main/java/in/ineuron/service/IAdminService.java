package in.ineuron.service;


import java.util.List;

import in.ineuron.dto.AdminDto;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;

public interface IAdminService {
	public List<String> checkingAdminAvailability(AdminDto adminDto);
	public String registerAdmin(AdminDto adminDto);
	public Integer getAdminId(AdminDto adminDto);
	public AdminDto validateAdmin(AdminDto adminDto);
	public String AdminLoginCheck(AdminDto adminDto);
	public String updateAdminPassword(AdminDto adminDto);
	public String checkAdminPasswordAvailability(AdminDto adminDto);
	public String addBook(BookDto bookDto);
	public String deleteBook(BookDto bookDto);
	public BookDto selectBookByIdAndName(BookDto bookDto);
	public String bookUpdation(BookDto bookDto);
	public List<StudentDto> getAllStudents();
	public List<BookDto> getAllBooks();
	public String IssueBookToStudent(StudentDto studentDto,BookDto bookDto);
	public String returnBook(StudentDto studentDto,BookDto bookDto);
	public String returnBookOrDeleteBookFromStudent(StudentDto studentDto,BookDto bookDto);
	public String increaseBookQuantity(BookDto bookDto);
	public String updatingHistoryReturnDate(StudentDto studentDto,BookDto bookDto);
	public List<String[]> getAllPendingBooks();
	public List<String[]> getHistory();
}
