package in.ineuron.dao;

import java.sql.Date;
import java.util.List;

import in.ineuron.bo.AdminBo;
import in.ineuron.bo.BookBo;
import in.ineuron.bo.StudentBo;
import in.ineuron.dto.AdminDto;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;

public interface IAdminDao {
	public List<AdminBo> fetchAllAdmins();
	public String registerAdmin(AdminDto adminDto);
	public AdminBo fetchAdminIdOrNameOrPassword(AdminDto adminDto);
	public String fetchAdminByEmailAndPassword(AdminDto adminDto);
	public String updateAdminPassword(AdminDto adminDto);
	public String checkBookAvailability(BookDto bookDto);
	public String addBook(BookDto bookDto);
	public String deleteBook(BookDto bookDto);
	public BookBo fetchBookByIdAndName(BookDto bookDto);
	public String checkBookAvailabilityforGivenIdUpdation(BookDto bookDto);
	public String checkBookAvailabilityforUpdation(BookDto bookDto);
	public String updateBok(BookDto bookDto);
	public List<StudentBo> fetchAllStudents();
	public List<BookBo> fetchAllBooks();
	public String fetchStudentByIdAndName(StudentDto studentDto);
	public String fetchBookDetailsByIdAndName(BookDto bookDto);
	public int getStudentBooksCount(StudentDto studentDto);
	public String issueBook(StudentDto studentDto,BookDto bookDto,Date issueDate);
	public String checkBookIsAlreadyIssuedtoStudent(StudentDto studentDto,BookDto bookDto);
	public String decreaseBookQuantity(BookDto bookDto,int quantity);
	public int getBookQuantity(BookDto bookDto);
	public int getDiffernceBetweenIssuedAndReturnedDates(StudentDto studentDto,BookDto bookDto);
	public String returnBookOrDeleteBookFromStudent(StudentDto studentDto,BookDto bookDto);
	public String increaseBookQuantity(BookDto bookDto,int quantity);
	public String insertRecordIntoHistory(StudentDto studentDto,BookDto bookDto,Date issueDate);
	public String updatingHistoryReturnDate(StudentDto studentDto,BookDto bookDto,Date  returnDate,Date issueDate);
	public Date getStudentBookIssuedDate(StudentDto studentDto,BookDto bookDto);
	public List<String[]> getAllPendingBooks();
	public List<String[]> getHistory();
}
