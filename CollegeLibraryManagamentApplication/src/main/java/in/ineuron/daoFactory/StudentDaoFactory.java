package in.ineuron.daoFactory;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private static IStudentDao studentDao=null;
	public static IStudentDao getStudentDaoObject()
	{
		if(studentDao==null)
		{
			studentDao=new StudentDaoImpl();
		}
		return studentDao;
	}
}
