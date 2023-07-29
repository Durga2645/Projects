package in.ineuron.util;

import java.sql.Connection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {	
	static
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getJdbcConnection() throws Exception
	{
		HikariConfig config=new HikariConfig("D:\\CollegeLibrabryManagementApplication\\CollegeLibraryManagamentApplication\\src\\main\\java\\in\\ineuron\\commons\\application.properties");
		@SuppressWarnings("resource")
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}
}
