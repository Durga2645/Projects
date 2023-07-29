package in.ineuron.serviceFactory;

import in.ineuron.service.AdminServiceImpl;
import in.ineuron.service.IAdminService;

public class AdminServiceFactory {
	private static IAdminService adminService=null;
	public static IAdminService getAdminServiceObject()
	{
		if(adminService==null)
		{
			adminService=new AdminServiceImpl();
		}
		return adminService;
	}
}
