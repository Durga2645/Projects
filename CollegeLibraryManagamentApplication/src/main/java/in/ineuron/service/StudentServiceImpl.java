package in.ineuron.service;

import java.util.ArrayList;
import java.util.List;

import in.ineuron.bo.BookBo;
import in.ineuron.bo.StudentBo;
import in.ineuron.dao.IStudentDao;
import in.ineuron.daoFactory.StudentDaoFactory;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;

public class StudentServiceImpl implements IStudentService {
	private static IStudentDao studentDao=null;
	static
	{
		studentDao=StudentDaoFactory.getStudentDaoObject();
	}
	
	@Override
	public List<String> checkingStudentAvailability(StudentDto studentDto) {
		List<StudentBo> list =studentDao.fetchAllStudents();
		List<String> list2=new ArrayList<String>();
		l1:for(StudentBo studentBo:list)
		{
			if(studentBo.getStudentEmail().equals(studentDto.getStudentEmail()))
			{
				list2.add("Email is already taken");
				break l1;
			}
			if(studentBo.getStudentPassword().equals(studentDto.getStudentPassword()))
			{
				list2.add("Password is already taken");
				break l1;
			}
			if(studentBo.getStudentMobilenumber().equals(studentDto.getStudentMobilenumber()))
			{
				list2.add("Mobile Number is already registered");
				break l1;
			}
		}
		return list2;
	}
	
	
	@Override
	public String registerStudent(StudentDto studentDto) {
		return studentDao.registerStudent(studentDto);
	}


	@Override
	public Integer getStudentId(StudentDto studentDto) {
		StudentBo studentBo=studentDao.fetchStudentIdOrNameOrPassword(studentDto);
		if(studentBo!=null)
			return studentBo.getStudentId();
		else
			return null;
		
		
	}

	@Override
	public StudentDto validateStudent(StudentDto studentDto) {
		StudentBo studentBo=studentDao.fetchStudentIdOrNameOrPassword(studentDto);
		if(studentBo!=null)
		{
			StudentDto sdto=new StudentDto();
			sdto.setStudentId(studentBo.getStudentId());
			sdto.setStudentName(studentBo.getStudentName());
			return sdto;
		}
		else
			return null;
	}


	@Override
	public String updateStudentPassword(StudentDto studentDto) {
		return studentDao.updateStudentPassword(studentDto);
	}


	@Override
	public String checkStudentPasswordAvailability(StudentDto studentDto) {
		List<StudentBo> allStudents = studentDao.fetchAllStudents();
		String s="Password is not taken";
		l1:for(StudentBo bo:allStudents)
		{
			if(studentDto.getStudentPassword().equals(bo.getStudentPassword()))
			{
				s="Password is already taken";
				break l1;
			}
		}
		return s;
	}


	@Override
	public String studentLoginCheck(StudentDto studentDto) {
		return studentDao.fetchStudentByEmailAndPassword(studentDto);
	}


	@Override
	public List<BookDto> fetchBookByName(String bookName) {
		List<BookBo> list=studentDao.fetchBookByName(bookName);
		List<BookDto> resultList=new ArrayList<BookDto>();
		for(BookBo book:list)
		{
			BookDto dto=new BookDto();
			dto.setBookId(book.getBookId());
			dto.setBookName(book.getBookName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookAuthor(book.getBookAuthor());
			dto.setBookCategory(book.getBookCategory());
			dto.setBookQuantity(book.getBookQuantity());
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<BookDto> fetchBookByTitle(String bookTitle) {
		List<BookBo> list=studentDao.fetchBookByTitle(bookTitle);
		List<BookDto> resultList=new ArrayList<BookDto>();
		for(BookBo book:list)
		{
			BookDto dto=new BookDto();
			dto.setBookId(book.getBookId());
			dto.setBookName(book.getBookName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookAuthor(book.getBookAuthor());
			dto.setBookCategory(book.getBookCategory());
			dto.setBookQuantity(book.getBookQuantity());
			resultList.add(dto);
		}
		return resultList;
	}


	@Override
	public List<BookDto> fetchBookByAuthor(String bookAuthor) {
		List<BookBo> list=studentDao.fetchBookByAuthor(bookAuthor);
		List<BookDto> resultList=new ArrayList<BookDto>();
		for(BookBo book:list)
		{
			BookDto dto=new BookDto();
			dto.setBookId(book.getBookId());
			dto.setBookName(book.getBookName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookAuthor(book.getBookAuthor());
			dto.setBookCategory(book.getBookCategory());
			dto.setBookQuantity(book.getBookQuantity());
			resultList.add(dto);
		}
		return resultList;
	}


	@Override
	public List<BookDto> fetchBookByCategory(String bookCategory) {
		List<BookBo> list=studentDao.fetchBookByCategory(bookCategory);
		List<BookDto> resultList=new ArrayList<BookDto>();
		for(BookBo book:list)
		{
			BookDto dto=new BookDto();
			dto.setBookId(book.getBookId());
			dto.setBookName(book.getBookName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookAuthor(book.getBookAuthor());
			dto.setBookCategory(book.getBookCategory());
			dto.setBookQuantity(book.getBookQuantity());
			resultList.add(dto);
		}
		return resultList;
	}
	
	@Override
	public List<String[]> fetchStudentIssuedBooks(StudentDto studentDto)
	{
		return studentDao.fetchStudentIssuedBooks(studentDto);
	}
	
	
	
	
}
