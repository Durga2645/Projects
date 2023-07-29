

package in.ineuron.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import in.ineuron.bo.AdminBo;
import in.ineuron.bo.BookBo;
import in.ineuron.bo.StudentBo;
import in.ineuron.dto.AdminDto;
import in.ineuron.dto.BookDto;
import in.ineuron.dto.StudentDto;
import in.ineuron.util.JdbcUtil;

public class AdminDaoImpl implements IAdminDao{
	private static Connection connection=null;
	private PreparedStatement ps=null;
	static
	{
		try {
			connection=JdbcUtil.getJdbcConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<AdminBo> fetchAllAdmins() {
		String query="SELECT ADMIN_EMAIL,ADMIN_PASSWORD,ADMIN_MOBILENUMBER FROM ADMIN";
		ResultSet resultSet=null;
		List<AdminBo> list=null;
		try {
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				resultSet=ps.executeQuery();
			}
			if(resultSet!=null)
			{
				list=new ArrayList<AdminBo>();
				while(resultSet.next())
				{
					AdminBo adminBo=new AdminBo();
					adminBo.setAdminEmail(resultSet.getString("ADMIN_EMAIL"));
					adminBo.setAdminPassword(resultSet.getString("ADMIN_PASSWORD"));
					adminBo.setAdminMobilenumber(resultSet.getLong("ADMIN_MOBILENUMBER"));
					list.add(adminBo);
				}
			}
			return list;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	@Override
	public String registerAdmin(AdminDto dto) {
		String query="INSERT INTO ADMIN(ADMIN_NAME,ADMIN_EMAIL,ADMIN_MOBILENUMBER,ADMIN_PASSWORD) VALUES(?,?,?,?)";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,dto.getAdminName());
				ps.setString(2,dto.getAdminEmail());
				ps.setLong(3,dto.getAdminMobilenumber());
				ps.setString(4, dto.getAdminPassword());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}

	@Override
	public AdminBo fetchAdminIdOrNameOrPassword(AdminDto adminDto) {
		String query="SELECT ADMIN_ID,ADMIN_NAME,ADMIN_PASSWORD FROM ADMIN WHERE ADMIN_EMAIL=?";
		ResultSet rs=null;
		AdminBo bo=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,adminDto.getAdminEmail());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					bo=new AdminBo();
					bo.setAdminId(rs.getInt("ADMIN_ID"));
					bo.setAdminName(rs.getString("ADMIN_NAME"));
				}
			}
			return bo;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bo;
	}



	@Override
	public String updateAdminPassword(AdminDto adminDto) {
		String query="UPDATE ADMIN SET ADMIN_PASSWORD=? WHERE ADMIN_EMAIL=?";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,adminDto.getAdminPassword());
				ps.setString(2,adminDto.getAdminEmail());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public String checkBookAvailability(BookDto bookDto) {
		String query="SELECT * FROM  BOOKDETAILS WHERE BOOK_NAME=? and BOOK_TITLE=? and BOOK_AUTHOR=? and BOOK_CATEGORY=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,bookDto.getBookName());
				ps.setString(2,bookDto.getBookTitle());
				ps.setString(3,bookDto.getBookAuthor());
				ps.setString(4,bookDto.getBookCategory());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "Book is already registered";
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Book is not registered";
	}



	@Override
	public String addBook(BookDto bookDto) {
		String query="INSERT INTO BOOKDETAILS(BOOK_NAME,BOOK_TITLE,BOOK_AUTHOR,BOOK_CATEGORY,BOOK_QUANTITY) VALUES(?,?,?,?,?)";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,bookDto.getBookName());
				ps.setString(2,bookDto.getBookTitle());
				ps.setString(3,bookDto.getBookAuthor());
				ps.setString(4,bookDto.getBookCategory());
				ps.setInt(5,bookDto.getBookQuantity());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public String deleteBook(BookDto bookDto) {
		String query="DELETE FROM BOOKDETAILS WHERE BOOK_ID=? and BOOK_NAME=?";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,bookDto.getBookId());
				ps.setString(2,bookDto.getBookName());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public BookBo fetchBookByIdAndName(BookDto bookDto) {
		String query="SELECT * FROM  BOOKDETAILS WHERE BOOK_ID=? and BOOK_NAME=?";
		ResultSet rs=null;
		BookBo bo=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,bookDto.getBookId());
				ps.setString(2,bookDto.getBookName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					bo =new BookBo();
					bo.setBookId(rs.getInt("BOOK_ID"));
					bo.setBookName(rs.getString("BOOK_NAME"));
					bo.setBookTitle(rs.getString("BOOK_TITLE"));
					bo.setBookAuthor(rs.getString("BOOK_AUTHOR"));
					bo.setBookCategory(rs.getString("BOOK_CATEGORY"));
					bo.setBookQuantity(rs.getInt("BOOK_QUANTITY"));
					return bo;
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bo;
	}



	@Override
	public String checkBookAvailabilityforGivenIdUpdation(BookDto bookDto) {
		String query="SELECT * FROM BOOKDETAILS WHERE BOOK_ID=? and BOOK_NAME=? and BOOK_TITLE=? and BOOK_AUTHOR=? and BOOK_CATEGORY=? and BOOK_QUANTITY=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,bookDto.getBookId());
				ps.setString(2,bookDto.getBookName());
				ps.setString(3,bookDto.getBookTitle());
				ps.setString(4,bookDto.getBookAuthor());
				ps.setString(5,bookDto.getBookCategory());
				ps.setInt(6,bookDto.getBookQuantity());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "Don't update";
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "UPDATE";
	}
	
	@Override
	public String checkBookAvailabilityforUpdation(BookDto bookDto) {
		String query="SELECT * FROM  BOOKDETAILS WHERE BOOK_NAME=? and BOOK_TITLE=? and BOOK_AUTHOR=? and BOOK_CATEGORY=? and BOOK_ID !=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,bookDto.getBookName());
				ps.setString(2,bookDto.getBookTitle());
				ps.setString(3,bookDto.getBookAuthor());
				ps.setString(4,bookDto.getBookCategory());
				ps.setInt(5,bookDto.getBookId());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "Book is already available and cannot enter duplicate Books";
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Book is not available(Update)";
	}



	@Override
	public String updateBok(BookDto bookDto) {
		String query="UPDATE BOOKDETAILS SET BOOK_NAME=?,BOOK_TITLE=?,BOOK_AUTHOR=?,BOOK_CATEGORY=?,BOOK_QUANTITY=? WHERE BOOK_ID=?";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				
				ps.setString(1,bookDto.getBookName());
				ps.setString(2,bookDto.getBookTitle());
				ps.setString(3,bookDto.getBookAuthor());
				ps.setString(4,bookDto.getBookCategory());
				ps.setInt(5,bookDto.getBookQuantity());
				ps.setInt(6,bookDto.getBookId());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}
	
	@Override
	public String fetchAdminByEmailAndPassword(AdminDto adminDto)
	{
		String query="SELECT * FROM ADMIN WHERE ADMIN_EMAIL=? and ADMIN_PASSWORD=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,adminDto.getAdminEmail());
				ps.setString(2,adminDto.getAdminPassword());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "SUCCESS";
				}
					
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public List<StudentBo> fetchAllStudents() {
		String query="SELECT STUDENT_ID,STUDENT_NAME,STUDENT_EMAIL,STUDENT_DEPARTMENT,STUDENT_MOBILENUMBER FROM STUDENT";
		ResultSet resultSet=null;
		List<StudentBo> list=null;
		try {
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				resultSet=ps.executeQuery();
			}
			if(resultSet!=null)
			{
				list=new ArrayList<StudentBo>();
				while(resultSet.next())
				{
					StudentBo studentBo=new StudentBo();
					studentBo.setStudentId(resultSet.getInt("STUDENT_ID"));
					studentBo.setStudentName(resultSet.getString("STUDENT_NAME"));
					studentBo.setStudentEmail(resultSet.getString("STUDENT_EMAIL"));
					studentBo.setStudentDepartment(resultSet.getString("STUDENT_DEPARTMENT"));
					studentBo.setStudentMobilenumber(resultSet.getLong("STUDENT_MOBILENUMBER"));
					list.add(studentBo);
				}
			}
			return list;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<BookBo> fetchAllBooks() {
		String query="SELECT * FROM  BOOKDETAILS";
		List<BookBo> list=null;
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				list=new ArrayList<BookBo>();
				while(rs.next())
				{
					BookBo bo=new BookBo();
					bo.setBookId(rs.getInt("BOOK_ID"));
					bo.setBookName(rs.getString("BOOK_NAME"));
					bo.setBookTitle(rs.getString("BOOK_TITLE"));
					bo.setBookAuthor(rs.getString("BOOK_AUTHOR"));
					bo.setBookCategory(rs.getString("BOOK_CATEGORY"));
					bo.setBookQuantity(rs.getInt("BOOK_QUANTITY"));
					list.add(bo);
				}
				
			}
			return list;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public String fetchStudentByIdAndName(StudentDto studentDto) {
		String query="SELECT * FROM  STUDENT WHERE STUDENT_ID=? and STUDENT_NAME=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "Student is available";
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Student is not available";
	}



	@Override
	public String fetchBookDetailsByIdAndName(BookDto bookDto) {
		String query="SELECT * FROM BOOKDETAILS WHERE BOOK_ID=? and BOOK_NAME=? and BOOK_QUANTITY>=1";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,bookDto.getBookId());
				ps.setString(2,bookDto.getBookName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "Book is available";
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Book is not available";
	}
	public int getStudentBooksCount(StudentDto studentDto)
	{
		String query="SELECT count(*) as booksCount FROM STUDENT_BOOK_DETAILS WHERE S_ID=? and S_NAME=?";
		int count=0;
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					count=rs.getInt("booksCount");
				}
			}
			return count;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
		
	}



	@Override
	public String issueBook(StudentDto studentDto, BookDto bookDto, Date issueDate) {
		String query="INSERT INTO STUDENT_BOOK_DETAILS(S_ID,S_NAME,B_ID,B_NAME,BOOK_ISSUE_DATE) VALUES(?,?,?,?,?)";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				ps.setInt(3,bookDto.getBookId());
				ps.setString(4,bookDto.getBookName());
				ps.setDate(5, issueDate);
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public String checkBookIsAlreadyIssuedtoStudent(StudentDto studentDto, BookDto bookDto) {
		String query="SELECT * FROM STUDENT_BOOK_DETAILS WHERE S_ID=? and S_NAME=? and B_ID=? and B_NAME=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				ps.setInt(3,bookDto.getBookId());
				ps.setString(4,bookDto.getBookName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					return "Book is already issued";
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return "Book is not issued";
	}
	public int getBookQuantity(BookDto bookDto)
	{
		String query="SELECT BOOK_QUANTITY FROM BOOKDETAILS WHERE BOOK_ID=? and BOOK_NAME=?";
		int quantity=0;
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,bookDto.getBookId());
				ps.setString(2,bookDto.getBookName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					quantity=rs.getInt("BOOK_QUANTITY");
					return quantity;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return quantity;
	}
	public String decreaseBookQuantity(BookDto bookDto,int quantity)
	{
		if(quantity >=1)
		{
			quantity=quantity-1;
		}
		String query="UPDATE BOOKDETAILS SET BOOK_QUANTITY=? WHERE BOOK_ID=? and BOOK_NAME=?";
		int rows=0;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,quantity);
				ps.setInt(2,bookDto.getBookId());
				ps.setString(3,bookDto.getBookName());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}






	@Override
	public int getDiffernceBetweenIssuedAndReturnedDates(StudentDto studentDto, BookDto bookDto) {
		String query="SELECT DATEDIFF(CURDATE(),BOOK_ISSUE_DATE) as DIFFERENCE FROM STUDENT_BOOK_DETAILS WHERE S_ID=? and S_NAME=? and B_ID=? and B_NAME=?";
		int difference=0;
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				ps.setInt(3,bookDto.getBookId());
				ps.setString(4,bookDto.getBookName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					difference=rs.getInt("DIFFERENCE");
				}
			}
			return difference;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return difference;
	}



	@Override
	public String returnBookOrDeleteBookFromStudent(StudentDto studentDto, BookDto bookDto) {
		String query="DELETE FROM STUDENT_BOOK_DETAILS WHERE S_ID=? and S_NAME=? and B_ID=? and B_NAME=?";
		int rows=0;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				ps.setInt(3,bookDto.getBookId());
				ps.setString(4,bookDto.getBookName());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}
	public String increaseBookQuantity(BookDto bookDto,int quantity) {
		quantity=quantity+1;
		String query="UPDATE BOOKDETAILS SET BOOK_QUANTITY=? WHERE BOOK_ID=? and BOOK_NAME=?";
		int rows=0;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{	
				ps.setInt(1,quantity);
				ps.setInt(2,bookDto.getBookId());
				ps.setString(3,bookDto.getBookName());
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public String insertRecordIntoHistory(StudentDto studentDto, BookDto bookDto, Date issueDate) {
		String query="INSERT INTO ISSUED_RETURNED_BOOKS_HISTORY(STUDENT_ID,STUDENT_NAME,BOOK_ID,BOOK_NAME,ISSUED_DATE) VALUES(?,?,?,?,?)";
		int rows=0;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				ps.setInt(3,bookDto.getBookId());
				ps.setString(4,bookDto.getBookName());
				ps.setDate(5,issueDate);
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}
	
	public String updatingHistoryReturnDate(StudentDto studentDto,BookDto bookDto,Date  returnDate,Date issueDate)
	{
		String query="UPDATE ISSUED_RETURNED_BOOKS_HISTORY SET RETURNED_DATE=? WHERE BOOK_ID=? and BOOK_NAME=? and STUDENT_ID=? and STUDENT_NAME=? and ISSUED_DATE=?";
		int rows=0;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{	
				ps.setDate(1,returnDate);
				ps.setInt(2,bookDto.getBookId());
				ps.setString(3,bookDto.getBookName());
				ps.setInt(4,studentDto.getStudentId());
				ps.setString(5,studentDto.getStudentName());
				ps.setDate(6, issueDate);
				rows=ps.executeUpdate();
			}
			if(rows==1)
			{
				return "SUCCESS";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "FAILURE";
	}



	@Override
	public Date getStudentBookIssuedDate(StudentDto studentDto, BookDto bookDto) {
		String query="SELECT BOOK_ISSUE_DATE FROM STUDENT_BOOK_DETAILS WHERE S_ID=? and S_NAME=? and B_ID=? and B_NAME=?";
		ResultSet rs=null;
		Date issueDate=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
				ps.setInt(3,bookDto.getBookId());
				ps.setString(4,bookDto.getBookName());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					issueDate=rs.getDate("BOOK_ISSUE_DATE");
				}
			}
			return issueDate;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return issueDate;
	}



	@Override
	public List<String[]> getAllPendingBooks() {
		String query="SELECT S_ID,S_NAME,B_ID,B_NAME,BOOK_ISSUE_DATE FROM STUDENT_BOOK_DETAILS";
		ResultSet rs=null;
		List<String[]> list=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				list=new ArrayList<String[]>();
				while(rs.next())
				{
					String[] s=new String[5];
					s[0]=Integer.toString(rs.getInt("S_ID"));
					s[1]=rs.getString("S_NAME");
					s[2]=Integer.toString(rs.getInt("B_ID"));
					s[3]=rs.getString("B_NAME");
					s[4]=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("BOOK_ISSUE_DATE"));
					list.add(s);
				}
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;

	}
	
	@Override
	public List<String[]> getHistory()
	{
		String query="SELECT STUDENT_ID,STUDENT_NAME,BOOK_ID,BOOK_NAME,ISSUED_DATE,RETURNED_DATE FROM ISSUED_RETURNED_BOOKS_HISTORY WHERE RETURNED_DATE IS NOT NULL";
		ResultSet rs=null;
		List<String[]> list=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				list=new ArrayList<String[]>();
				while(rs.next())
				{
					String[] s=new String[6];
					s[0]=Integer.toString(rs.getInt("STUDENT_ID"));
					s[1]=rs.getString("STUDENT_NAME");
					s[2]=Integer.toString(rs.getInt("BOOK_ID"));
					s[3]=rs.getString("BOOK_NAME");
					s[4]=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("ISSUED_DATE"));
					s[5]=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("RETURNED_DATE"));
					list.add(s);
				}
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
}
