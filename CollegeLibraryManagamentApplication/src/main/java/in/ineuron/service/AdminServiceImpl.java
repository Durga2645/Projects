package in.ineuron.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import in.ineuron.bo.AdminBo;
import in.ineuron.bo.BookBo;
import in.ineuron.bo.StudentBo;
import in.ineuron.dao.AdminDaoImpl;
import in.ineuron.dao.IAdminDao;
import in.ineuron.dto.AdminDto;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;

public class AdminServiceImpl implements IAdminService {

	private static IAdminDao adminDao=null;
	static
	{
		adminDao=new AdminDaoImpl();
	}
	@Override
	public List<String> checkingAdminAvailability(AdminDto adminDto) {
		List<AdminBo> list =adminDao.fetchAllAdmins();
		List<String> list2=new ArrayList<String>();
		l1:for(AdminBo adminBo:list)
		{
			if(adminBo.getAdminEmail().equals(adminDto.getAdminEmail()))
			{
				list2.add("Email is already taken");
				break l1;
			}
			if(adminBo.getAdminPassword().equals(adminDto.getAdminPassword()))
			{
				list2.add("Password is already taken");
				break l1;
			}
			if(adminBo.getAdminMobilenumber().equals(adminDto.getAdminMobilenumber()))
			{
				list2.add("Mobile Number is already registered");
				break l1;
			}
		}
		return list2;
	}
	
	
	@Override
	public String registerAdmin(AdminDto adminDto) {
		return adminDao.registerAdmin(adminDto);
	}


	@Override
	public Integer getAdminId(AdminDto adminDto) {
		AdminBo adminBo=adminDao.fetchAdminIdOrNameOrPassword(adminDto);
		if(adminBo!=null)
			return adminBo.getAdminId();
		else
			return null;
		
		
	}

	@Override
	public AdminDto validateAdmin(AdminDto adminDto) {
		AdminBo adminBo=adminDao.fetchAdminIdOrNameOrPassword(adminDto);
		if(adminBo!=null)
		{
			AdminDto adto=new AdminDto();
			adto.setAdminId(adminBo.getAdminId());
			adto.setAdminName(adminBo.getAdminName());
			return adto;
		}
		else
			return null;
		
	}


	@Override
	public String updateAdminPassword(AdminDto adminDto) {
		return adminDao.updateAdminPassword(adminDto);
	}


	@Override
	public String checkAdminPasswordAvailability(AdminDto adminDto) {
		List<AdminBo> allAdmins = adminDao.fetchAllAdmins();
		String s="Password is not taken";
		l1:for(AdminBo bo:allAdmins)
		{
			if(adminDto.getAdminPassword().equals(bo.getAdminPassword()))
			{
				s="Password is already taken";
				break l1;
			}
		}
		return s;
	}


	@Override
	public String addBook(BookDto bookDto) {
		String result=adminDao.checkBookAvailability(bookDto);
		if(result.equalsIgnoreCase("Book is not registered"))
		{
			adminDao.addBook(bookDto);
			return "Book added successfully";
		}
		else
		{
			return "Book is already available in the Library";
		}
		
	}


	@Override
	public String deleteBook(BookDto bookDto) {
		String result=adminDao.deleteBook(bookDto);
		if(result.equals("SUCCESS"))
		{
			return "Book Deleted Successfully";
		}
		else
		{
			return "Given Book Details are not available";
		}
	}


	@Override
	public BookDto selectBookByIdAndName(BookDto bookDto) {
		BookBo bookBo=adminDao.fetchBookByIdAndName(bookDto);
		if(bookBo!=null)
		{
			BookDto dto=new BookDto();
			dto.setBookId(bookBo.getBookId());
			dto.setBookName(bookBo.getBookName());
			dto.setBookTitle(bookBo.getBookTitle());
			dto.setBookAuthor(bookBo.getBookAuthor());
			dto.setBookCategory(bookBo.getBookCategory());
			dto.setBookQuantity(bookBo.getBookQuantity());
			return dto;
		}
		else
		{
			return null;
		}
		
	}


	@Override
	public String bookUpdation(BookDto bookDto) {
		String result=adminDao.checkBookAvailabilityforGivenIdUpdation(bookDto);
		if(result.equals("UPDATE"))
		{
			String result1=adminDao.checkBookAvailabilityforUpdation(bookDto);
			if(result1.equals("Book is not available(Update)"))
			{
				String result2=adminDao.updateBok(bookDto);
				if(result2.equals("SUCCESS"))
				{
					return "Book updated Successfully";
				}
				else
				{
					return "Book Updation Failed";
				}
				
			}
			else
			{
				return "Book is already available and it can be duplicated";
			}
		}
		else
		{
			return "Book updated Successfully";
		}
	}


	@Override
	public String AdminLoginCheck(AdminDto adminDto) 
	{
		return adminDao.fetchAdminByEmailAndPassword(adminDto);
	}


	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentBo> students = adminDao.fetchAllStudents();
		List<StudentDto> studentsList=new ArrayList<StudentDto>();
		for(StudentBo bo:students)
		{
			StudentDto dto=new StudentDto();
			dto.setStudentId(bo.getStudentId());
			dto.setStudentName(bo.getStudentName());
			dto.setStudentEmail(bo.getStudentEmail());
			dto.setStudentDepartment(bo.getStudentDepartment());
			dto.setStudentMobilenumber(bo.getStudentMobilenumber());
			studentsList.add(dto);
		}
		return studentsList;
	}


	@Override
	public List<BookDto> getAllBooks() {
		List<BookBo> books=adminDao.fetchAllBooks();
		List<BookDto> allBooks=new ArrayList<BookDto>();
		for(BookBo book:books)
		{
			BookDto dto=new BookDto();
			dto.setBookId(book.getBookId());
			dto.setBookName(book.getBookName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookAuthor(book.getBookAuthor());
			dto.setBookCategory(book.getBookCategory());
			dto.setBookQuantity(book.getBookQuantity());
			allBooks.add(dto);
		}
		return allBooks;
	}
	
	public String IssueBookToStudent(StudentDto studentDto,BookDto bookDto)
	{
		String result="";
		if(adminDao.fetchStudentByIdAndName(studentDto).equals("Student is available"))
		{
			if(adminDao.fetchBookDetailsByIdAndName(bookDto).equals("Book is available"))
			{
				if(adminDao.getStudentBooksCount(studentDto)<3)
				{
					if(adminDao.checkBookIsAlreadyIssuedtoStudent(studentDto, bookDto).equals("Book is not issued"))
					{
						java.util.Date utilDate=new java.util.Date();
						long l=utilDate.getTime();
						java.sql.Date issueDate=new java.sql.Date(l);
						if(adminDao.issueBook(studentDto, bookDto, issueDate).equals("SUCCESS"))
						{
							int quantity=adminDao.getBookQuantity(bookDto);
							adminDao.decreaseBookQuantity(bookDto, quantity);
							adminDao.insertRecordIntoHistory(studentDto, bookDto, issueDate);
							result= "Book issued to the student successfully";
						}
						else
						{
							result= "Book not issued";
						}
					}
					else
					{
						result="Given Book is already issued to the Student";
					}
				}
				else
				{
					result="Each student can be issued only 3 books at a time";
				}
			}
			else
			{
				result="Given Book Details are not available";
			}
		}
		else
		{
			result="Given Student Details are not available";
		}
		return result;
	}
	public String returnBook(StudentDto studentDto,BookDto bookDto)
	{
		String result="";
		if(adminDao.fetchStudentByIdAndName(studentDto).equals("Student is available"))
		{
			if(adminDao.fetchBookByIdAndName(bookDto)!=null)
			{
				if(adminDao.checkBookIsAlreadyIssuedtoStudent(studentDto, bookDto).equals("Book is already issued"))
				{
					int difference=adminDao.getDiffernceBetweenIssuedAndReturnedDates(studentDto, bookDto);
					int fine=0;
					if(difference>13)
					{
						fine=(difference-13)*10;
					}
					result="Your fine is :: "+fine;					
				}
				else
				{
					result="Book is not issued to that student";
				}
				
			}
			else
			{
				result="Book details are not available";
			}
		}
		else
		{
			result="Student details are not available";
		}
		return result;
	}


	@Override
	public String returnBookOrDeleteBookFromStudent(StudentDto studentDto, BookDto bookDto) {
		return adminDao.returnBookOrDeleteBookFromStudent(studentDto, bookDto);
	}


	@Override
	public String increaseBookQuantity(BookDto bookDto) {
		int quantity=adminDao.getBookQuantity(bookDto);
		return adminDao.increaseBookQuantity(bookDto, quantity);
	}
	
	@Override
	public String updatingHistoryReturnDate(StudentDto studentDto,BookDto bookDto)
	{
		java.util.Date utilDate=new java.util.Date();
		long l=utilDate.getTime();
		java.sql.Date returnDate=new java.sql.Date(l);
		Date issueDate=adminDao.getStudentBookIssuedDate(studentDto, bookDto);
		return adminDao.updatingHistoryReturnDate(studentDto, bookDto, returnDate,issueDate);
	}
	
	@Override
	public List<String[]> getAllPendingBooks()
	{
		return adminDao.getAllPendingBooks();
	}
	
	@Override
	public List<String[]> getHistory()
	{
		return adminDao.getHistory();
	}
}	
