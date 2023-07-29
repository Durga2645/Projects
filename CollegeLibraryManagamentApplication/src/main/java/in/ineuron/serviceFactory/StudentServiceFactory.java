package in.ineuron.serviceFactory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

public class StudentServiceFactory {
	private static IStudentService studentService=null;
	public static IStudentService getStudentServiceObject()
	{
		if(studentService==null)
		{
			studentService=new StudentServiceImpl();
		}
		return studentService;
	}
}
