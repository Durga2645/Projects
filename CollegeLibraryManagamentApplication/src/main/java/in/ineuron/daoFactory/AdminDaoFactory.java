package in.ineuron.daoFactory;

import in.ineuron.dao.AdminDaoImpl;
import in.ineuron.dao.IAdminDao;

public class AdminDaoFactory {
	private static IAdminDao adminDao;
	public static IAdminDao getAdminDaoObject() {
		if(adminDao==null)
		{
			adminDao=new AdminDaoImpl();
		}
		return adminDao;
	}
}
