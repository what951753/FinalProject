package package14_SaleTicket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DAOPage {
	

		
		private DataSource dataSource;
		
		public DataSource getDataSourcePJ() {
			
			if(dataSource == null) {  
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("group4");
			ds.setPassword("oracle");
			ds.setMaxTotal(50); 
			ds.setMaxIdle(50);   

			dataSource = ds; 
		  }
			return dataSource;
		}

	public List<Shows> listShows() {
		
		List<Shows> list = new ArrayList<Shows>();
        
		try  
			(Connection connection = getDataSourcePJ().getConnection();
			Statement stmt= connection.createStatement();
			ResultSet rs= stmt.executeQuery("select ACT_UID, ACT_TITLE, ACT_CATEGORY, ACT_LOCATION, ACT_DESCRIPTION from "
					+ "MAINTABLE WHERE ACT_TITLE LIKE '科%'");){
								
				while (rs.next()) { 
					Shows sh = new Shows();
							
							
					
					String titlString = rs.getString("ACT_TITLE");
					sh.setTitle(titlString);
					
					sh.setUUID(rs.getString("ACT_UID"));
					sh.setCategory(rs.getString("ACT_CATEGORY"));
					sh.setLocation(rs.getString("ACT_LOCATION"));
					sh.setDescription(rs.getString("ACT_DESCRIPTION"));
					
					list.add(sh);
					
					}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
