package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import in.ineuron.bo.BookBo;
import in.ineuron.bo.StudentBo;
import in.ineuron.dto.StudentDto;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao{
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
	public List<StudentBo> fetchAllStudents() {
		String query="SELECT STUDENT_EMAIL,STUDENT_PASSWORD,STUDENT_MOBILENUMBER FROM STUDENT";
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
					studentBo.setStudentEmail(resultSet.getString("STUDENT_EMAIL"));
					studentBo.setStudentPassword(resultSet.getString("STUDENT_PASSWORD"));
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
	public String registerStudent(StudentDto dto) {
		String query="INSERT INTO STUDENT(STUDENT_NAME,STUDENT_EMAIL,STUDENT_MOBILENUMBER,STUDENT_PASSWORD,STUDENT_DEPARTMENT) VALUES(?,?,?,?,?)";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,dto.getStudentName());
				ps.setString(2,dto.getStudentEmail());
				ps.setLong(3,dto.getStudentMobilenumber());
				ps.setString(4, dto.getStudentPassword());
				ps.setString(5,dto.getStudentDepartment());
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
	public StudentBo fetchStudentIdOrNameOrPassword(StudentDto studentDto) {
		String query="SELECT STUDENT_ID,STUDENT_NAME,STUDENT_PASSWORD FROM STUDENT WHERE STUDENT_EMAIL=?";
		ResultSet rs=null;
		StudentBo bo=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,studentDto.getStudentEmail());
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					bo=new StudentBo();
					bo.setStudentId(rs.getInt("STUDENT_ID"));
					bo.setStudentName(rs.getString("STUDENT_NAME"));
					bo.setStudentPassword(rs.getString("STUDENT_PASSWORD"));
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
	public String updateStudentPassword(StudentDto studentDto) {
		String query="UPDATE STUDENT SET STUDENT_PASSWORD=? WHERE STUDENT_EMAIL=?";
		Integer rows=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,studentDto.getStudentPassword());
				ps.setString(2,studentDto.getStudentEmail());
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
	public String fetchStudentByEmailAndPassword(StudentDto studentDto) {
		String query="SELECT * FROM STUDENT WHERE STUDENT_EMAIL=? and STUDENT_PASSWORD=?";
		ResultSet rs=null;
		try
		{
			if(connection!=null)
			{
				ps=connection.prepareStatement(query);
			}
			if(ps!=null)
			{
				ps.setString(1,studentDto.getStudentEmail());
				ps.setString(2,studentDto.getStudentPassword());
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
	public List<BookBo> fetchBookByName(String bookName) {
		String query="SELECT * FROM BOOKDETAILS WHERE BOOK_NAME=?";
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
				ps.setString(1,bookName);
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
	public List<BookBo> fetchBookByTitle(String bookTitle)
	{
		String query="SELECT * FROM BOOKDETAILS WHERE BOOK_TITLE=?";
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
				ps.setString(1,bookTitle);
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
	public List<BookBo> fetchBookByAuthor(String bookAuthor)
	{
		String query="SELECT * FROM BOOKDETAILS WHERE BOOK_AUTHOR=?";
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
				ps.setString(1,bookAuthor);
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
	public List<BookBo> fetchBookByCategory(String bookCategory)
	{
		String query="SELECT * FROM BOOKDETAILS WHERE BOOK_CATEGORY=?";
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
				ps.setString(1,bookCategory);
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
	public List<String[]> fetchStudentIssuedBooks(StudentDto studentDto)
	{
		String query="SELECT S_ID,S_NAME,B_ID,B_NAME,BOOK_ISSUE_DATE FROM STUDENT_BOOK_DETAILS WHERE S_ID=? and S_NAME=?";
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
				ps.setInt(1,studentDto.getStudentId());
				ps.setString(2,studentDto.getStudentName());
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
}	
