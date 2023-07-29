package in.ineuron.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.dto.AdminDto;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;
import in.ineuron.service.IAdminService;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.AdminServiceFactory;
import in.ineuron.serviceFactory.StudentServiceFactory;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/controller/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAdminService adminService=null;
	private IStudentService studentService=null;
	private RequestDispatcher dispatcher=null;
	private HttpSession session=null;
	@Override
	public void init()
	{
		adminService=AdminServiceFactory.getAdminServiceObject();
		studentService=StudentServiceFactory.getStudentServiceObject();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String input=request.getRequestURI();
		
		
		//adminRegistration
		if(input.endsWith("adminRegistration"))
		{
			String adminName=request.getParameter("adminName");
			String adminEmail=request.getParameter("adminEmail");
			String adminPassword=request.getParameter("adminPassword");
			Long adminMobile=Long.parseLong(request.getParameter("adminMobile"));
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminName(adminName);
			adminDto.setAdminEmail(adminEmail);
			adminDto.setAdminPassword(adminPassword);
			adminDto.setAdminMobilenumber(adminMobile);
			List<String> list=adminService.checkingAdminAvailability(adminDto);
			if(list.isEmpty())
			{
				String result=adminService.registerAdmin(adminDto);
				if(result.equals("SUCCESS"))
				{
					Integer adminId=adminService.getAdminId(adminDto);
					request.setAttribute("adminId",adminId);
					dispatcher=request.getRequestDispatcher("./../adminRegistrationSuccessfull.jsp");
					dispatcher.forward(request,response);
				}	
				else
				{
					dispatcher=request.getRequestDispatcher("./../adminRegistrationFailed.jsp");
					dispatcher.forward(request,response);
				}
					
			}
			else
			{
				String emailErrorMessage="Enter your email";
				String passwordErrorMessage="Enter your password";
				String mobilenumberErrorMessage="Enter yout mobileNumber";
				for(String s:list)
				{
					if(s.equals("Email is already taken"))
					{
						emailErrorMessage=s;
					}
					if(s.equals("Password is already taken"))
					{
						passwordErrorMessage=s;
					}
					if(s.equals("Mobile Number is already registered"))
					{
						mobilenumberErrorMessage=s;
					}
				}
				request.setAttribute("emailErrorMessage",emailErrorMessage);
				request.setAttribute("passwordErrorMessage",passwordErrorMessage);
				request.setAttribute("mobilenumberErrorMessage",mobilenumberErrorMessage);
				dispatcher=request.getRequestDispatcher("./../adminRegistrationError.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		//adminLogin
		if(input.endsWith("adminLogin"))
		{
			String email=request.getParameter("adminEmail");
			String password=request.getParameter("adminPassword");
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(email);
			adminDto.setAdminPassword(password);
			String result=adminService.AdminLoginCheck(adminDto);
			AdminDto dto=adminService.validateAdmin(adminDto);
			if(dto!=null && result.equals("SUCCESS"))
			{
				session=request.getSession(true);
				session.setAttribute("adminName",dto.getAdminName());
				dispatcher=request.getRequestDispatcher("./../adminDashboard.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher("./../adminLoginFailed.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		
		//adminForgotPassword
		if(input.endsWith("adminForgotPassword"))
		{
				
			String email=request.getParameter("adminEmail");
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(email);
			AdminDto dto=adminService.validateAdmin(adminDto);
			if(dto!=null)
			{
				session=request.getSession(true);
				session.setAttribute("adminEmail",email);
				dispatcher=request.getRequestDispatcher("./../adminPasswordReset.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher("./../adminEmailNotRegistered.jsp");
				dispatcher.forward(request,response);
			}
		}	
		//adminPasswordReset
		if(input.endsWith("adminPasswordReset"))
		{
			String password1=request.getParameter("password1");
			String password2=request.getParameter("password2");
			if(password1.equals(password2))
			{
				AdminDto dto=new AdminDto();
				dto.setAdminPassword(password2);
				String result1=adminService.checkAdminPasswordAvailability(dto);
				if(result1.equals("Password is not taken"))
				{
					dto.setAdminEmail((String)session.getAttribute("adminEmail"));
					dto.setAdminPassword(password2);
					String result=adminService.updateAdminPassword(dto);
					if(result.equals("SUCCESS"))
					{
						dispatcher=request.getRequestDispatcher("./../adminPasswordResetSuccessfull.jsp");
						dispatcher.forward(request,response);	
					}
				}
				else
				{
					dispatcher=request.getRequestDispatcher("./../adminPasswordAlreadyRegistered.jsp");
					dispatcher.forward(request,response);	
				}
				
			}
			else
			{
				dispatcher=request.getRequestDispatcher("./../adminPasswordResetFailed.jsp");
				dispatcher.forward(request,response);
			}
		}
				
		//addBook
		if(input.endsWith("addBook"))
		{
			BookDto bookDto=new BookDto();
			bookDto.setBookName(request.getParameter("bookName"));
			bookDto.setBookTitle(request.getParameter("bookTitle"));
			bookDto.setBookAuthor(request.getParameter("bookAuthor"));
			bookDto.setBookCategory(request.getParameter("bookCategory"));
			bookDto.setBookQuantity(Integer.parseInt(request.getParameter("bookQuantity")));
			String result=adminService.addBook(bookDto);
			request.setAttribute("addBookResult", result);
			dispatcher=request.getRequestDispatcher("./../addBookResult.jsp");
			dispatcher.forward(request,response);
			
		}
		
		//deleteBook
		if(input.endsWith("deleteBook"))
		{
			BookDto bookDto=new BookDto();
			bookDto.setBookId(Integer.parseInt(request.getParameter("bookId")));
			bookDto.setBookName(request.getParameter("bookName"));
			String result=adminService.deleteBook(bookDto);
			request.setAttribute("deleteBookResult", result);
			dispatcher=request.getRequestDispatcher("./../deleteBookResult.jsp");
			dispatcher.forward(request,response);
			
		}
		
		//selectBook
		if(input.endsWith("selectBook"))
		{
			BookDto bookDto=new BookDto();
			bookDto.setBookId(Integer.parseInt(request.getParameter("bookId")));
			bookDto.setBookName(request.getParameter("bookName"));
			bookDto=adminService.selectBookByIdAndName(bookDto);
			request.setAttribute("bookDetails",bookDto);
			dispatcher=request.getRequestDispatcher("./../selectBookResult.jsp");
			dispatcher.forward(request,response);
		}
		
		if(input.endsWith("updateBook"))
		{
			BookDto bookDto=new BookDto();
			bookDto.setBookId(Integer.parseInt(request.getParameter("bookId")));
			bookDto.setBookName(request.getParameter("bookName"));
			bookDto.setBookTitle(request.getParameter("bookTitle"));
			bookDto.setBookAuthor(request.getParameter("bookAuthor"));
			bookDto.setBookCategory(request.getParameter("bookCategory"));
			bookDto.setBookQuantity(Integer.parseInt(request.getParameter("bookQuantity")));
			String result=adminService.bookUpdation(bookDto);
			request.setAttribute("updateBookResult",result);
			dispatcher=request.getRequestDispatcher("./../updateBookResult.jsp");
			dispatcher.forward(request,response);
		}
		
		
		//displayingAllStudents
		if(input.endsWith("viewAllStudents"))
		{
			List<StudentDto> allStudents = adminService.getAllStudents();
			request.setAttribute("allStudents",allStudents);
			dispatcher=request.getRequestDispatcher("./../displayAllStudents.jsp");
			dispatcher.forward(request,response);
		}
		
		//displayingAllBooks
		if(input.endsWith("viewAllBooks"))
		{
			List<BookDto> allBooks = adminService.getAllBooks();
			request.setAttribute("allBooks",allBooks);
			dispatcher=request.getRequestDispatcher("./../displayAllBooks.jsp");
			dispatcher.forward(request,response);
		}
		
		//issueBook
		if(input.endsWith("issueBook"))
		{
			StudentDto sdto=new StudentDto();
			BookDto bdto=new BookDto();
			bdto.setBookId(Integer.parseInt(request.getParameter("bookId")));
			bdto.setBookName(request.getParameter("bookName"));
			sdto.setStudentId(Integer.parseInt(request.getParameter("studentId")));
			sdto.setStudentName(request.getParameter("studentName"));
			String issueBookResult=adminService.IssueBookToStudent(sdto, bdto);
			request.setAttribute("issueBookResult",issueBookResult);
			dispatcher=request.getRequestDispatcher("./../issueBookResult.jsp");
			dispatcher.forward(request,response);
			
			
		}
		
		//returnBook
		if(input.endsWith("returnBook"))
		{
			StudentDto sdto=new StudentDto();
			BookDto bdto=new BookDto();
			bdto.setBookId(Integer.parseInt(request.getParameter("bookId")));
			bdto.setBookName(request.getParameter("bookName"));
			sdto.setStudentId(Integer.parseInt(request.getParameter("studentId")));
			sdto.setStudentName(request.getParameter("studentName"));
			String returnBookResult=adminService.returnBook(sdto, bdto);
			request.setAttribute("studentDto",sdto);
			request.setAttribute("bookDto",bdto);
			request.setAttribute("returnBookResult",returnBookResult);
			dispatcher=request.getRequestDispatcher("./../returnBookResult.jsp");
			
			dispatcher.forward(request,response);
		}
		if(input.endsWith("finePaid"))
		{
			StudentDto studentDto=new StudentDto();
			BookDto bookDto=new BookDto();
			studentDto.setStudentId(Integer.parseInt(request.getParameter("studentId")));
			studentDto.setStudentName(request.getParameter("studentName"));
			bookDto.setBookId(Integer.parseInt(request.getParameter("bookId")));
			bookDto.setBookName(request.getParameter("bookName"));
			adminService.updatingHistoryReturnDate(studentDto, bookDto);
			adminService.returnBookOrDeleteBookFromStudent(studentDto,bookDto);
			adminService.increaseBookQuantity(bookDto);
			request.setAttribute("finePaid","fine paid successfully");
			dispatcher=request.getRequestDispatcher("./../returnBookResult.jsp");
			dispatcher.forward(request,response);

		}
		
		//viewAllPendingBooks
		if(input.endsWith("viewAllPendingBooks"))
		{
			List<String[]> allPendingBooks=adminService.getAllPendingBooks();
			request.setAttribute("allPendingBooks", allPendingBooks);
			dispatcher=request.getRequestDispatcher("./../displayAllPendingBooks.jsp");
			dispatcher.forward(request,response);
		}
		
		
		
		//viewAllHistory
		if(input.endsWith("viewAllHistory"))
		{
			List<String[]> history=adminService.getHistory();
			request.setAttribute("history", history);
			dispatcher=request.getRequestDispatcher("./../displayHistory.jsp");
			dispatcher.forward(request,response);
		}
		
		
		
		
		//studentRegistartion
		if(input.endsWith("studentRegistration"))
		{
			String studentName=request.getParameter("studentName");
			String studentEmail=request.getParameter("studentEmail");
			String studentPassword=request.getParameter("studentPassword");
			Long studentMobile=Long.parseLong(request.getParameter("studentMobile"));
			String studentDepartment=request.getParameter("studentDepartment");
			StudentDto studentDto=new StudentDto();
			studentDto.setStudentName(studentName);
			studentDto.setStudentEmail(studentEmail);
			studentDto.setStudentPassword(studentPassword);
			studentDto.setStudentMobilenumber(studentMobile);
			studentDto.setStudentDepartment(studentDepartment);
			List<String> list=studentService.checkingStudentAvailability(studentDto);
			if(list.isEmpty())
			{
				String result=studentService.registerStudent(studentDto);
				if(result.equals("SUCCESS"))
				{
					Integer studentId=studentService.getStudentId(studentDto);
					request.setAttribute("studentId",studentId);
					dispatcher=request.getRequestDispatcher("./../studentRegistrationSuccessfull.jsp");
					dispatcher.forward(request,response);
				}	
				else
				{
					dispatcher=request.getRequestDispatcher("./../studentRegistrationFailed.jsp");
					dispatcher.forward(request,response);
				}
							
			}
			else
			{
				String emailErrorMessage="Enter your email";
				String passwordErrorMessage="Enter your password";
				String mobilenumberErrorMessage="Enter yout mobileNumber";
				for(String s:list)
				{
					if(s.equals("Email is already taken"))
					{
						emailErrorMessage=s;
					}
					if(s.equals("Password is already taken"))
					{
						passwordErrorMessage=s;
					}
					if(s.equals("Mobile Number is already registered"))
					{
						mobilenumberErrorMessage=s;
					}
				}
				request.setAttribute("emailErrorMessage",emailErrorMessage);
				request.setAttribute("passwordErrorMessage",passwordErrorMessage);
				request.setAttribute("mobilenumberErrorMessage",mobilenumberErrorMessage);
				dispatcher=request.getRequestDispatcher("./../studentRegistrationError.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		
		//studentLogin
		if(input.endsWith("studentLogin"))
		{
		
			String email=request.getParameter("studentEmail");
			String password=request.getParameter("studentPassword");
			StudentDto studentDto=new StudentDto();
			studentDto.setStudentEmail(email);
			studentDto.setStudentPassword(password);
			String result=studentService.studentLoginCheck(studentDto);
			StudentDto dto=studentService.validateStudent(studentDto);
			if(dto!=null && result.equals("SUCCESS"))
			{
				session=request.getSession(true);
				session.setAttribute("studentName",dto.getStudentName());
				session.setAttribute("studentId",dto.getStudentId());
				dispatcher=request.getRequestDispatcher("./../studentDashboard.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher("./../studentLoginFailed.jsp");
				dispatcher.forward(request,response);
		
			}
		}
		
		//StudentForgotPassword
		if(input.endsWith("studentForgotPassword"))
		{
		
			String email=request.getParameter("studentEmail");
			StudentDto studentDto=new StudentDto();
			studentDto.setStudentEmail(email);
			StudentDto dto=studentService.validateStudent(studentDto);
			if(dto!=null)
			{
				session=request.getSession(true);
				session.setAttribute("studentEmail",email);
				dispatcher=request.getRequestDispatcher("./../studentPasswordReset.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				dispatcher=request.getRequestDispatcher("./../studentEmailNotRegistered.jsp");
				dispatcher.forward(request,response);
		
			}
		}
		
		//StudentPasswordReset
		if(input.endsWith("studentPasswordReset"))
		{
			String password1=request.getParameter("password1");
			String password2=request.getParameter("password2");
			if(password1.equals(password2))
			{
				StudentDto dto=new StudentDto();
				dto.setStudentPassword(password2);
				String result1=studentService.checkStudentPasswordAvailability(dto);
				if(result1.equals("Password is not taken"))
				{
					dto.setStudentEmail((String)session.getAttribute("studentEmail"));
					dto.setStudentPassword(password2);
					String result=studentService.updateStudentPassword(dto);
					if(result.equals("SUCCESS"))
					{
						dispatcher=request.getRequestDispatcher("./../studentPasswordResetSuccessfull.jsp");
						dispatcher.forward(request,response);	
					}
				}
				else
				{
					dispatcher=request.getRequestDispatcher("./../studentPasswordAlreadyRegistered.jsp");
					dispatcher.forward(request,response);	
				}
				
			}
			else
			{
				dispatcher=request.getRequestDispatcher("./../studentPasswordResetFailed.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		if(input.endsWith("studentSearchBooks"))
		{
			String search=request.getParameter("search");
			String type=request.getParameter("type");
			if(search.equals("Book Name"))
			{
				List<BookDto> list = studentService.fetchBookByName(type);
				request.setAttribute("bookList",list);
				dispatcher=request.getRequestDispatcher("./../searchBookResult.jsp");
				dispatcher.forward(request,response);
			}
			else if(search.equals("Book Title"))
			{
				List<BookDto> list =studentService.fetchBookByTitle(type);
				request.setAttribute("bookList",list);
				dispatcher=request.getRequestDispatcher("./../searchBookResult.jsp");
				dispatcher.forward(request,response);
			}
			else if(search.equals("Book Author"))
			{
				List<BookDto> list=studentService.fetchBookByAuthor(type);
				request.setAttribute("bookList",list);
				dispatcher=request.getRequestDispatcher("./../searchBookResult.jsp");
				dispatcher.forward(request,response);
			}
			else
			{
				List<BookDto> list =studentService.fetchBookByCategory(type);
				request.setAttribute("bookList",list);
				dispatcher=request.getRequestDispatcher("./../searchBookResult.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		if(input.endsWith("studentIssuedBooks"))
		{
			session=request.getSession(false);
			StudentDto studentDto=new StudentDto();
			studentDto.setStudentId((Integer)session.getAttribute("studentId"));
			studentDto.setStudentName((String)session.getAttribute("studentName"));
			List<String[]> studentIssuedBooks = studentService.fetchStudentIssuedBooks(studentDto);

			request.setAttribute("studentIssuedBooks",studentIssuedBooks);
			dispatcher=request.getRequestDispatcher("./../displayStudentIssuedBooks.jsp");
			dispatcher.forward(request,response);	
		}
		
	}

}
