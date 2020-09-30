package test;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import packge14_SaleTicket.DAOPage;
import packge14_SaleTicket.Product;

public class CblobTest {

	public static void main(String[] args) {
List<Product> productList = new ArrayList<Product>();
		
		DAOPage daoPage = new DAOPage(); 
        
		try  
			(Connection connection = daoPage.getDataSourcePJ().getConnection();
			Statement stmt= connection.createStatement();
			ResultSet rs= stmt.executeQuery("select PRODUCT_ID, PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_IMG, PRODUCT_NUM, PRODUCT_DES, PRODUCT_TYPE from "
					+ "PRODUCT");){
								
				while (rs.next()) { 
					Product product = new Product();
					product.setProductId(rs.getString("PRODUCT_ID"));
					product.setProductTitle(rs.getString("PRODUCT_TITLE"));
					product.setProductPrice(rs.getString("PRODUCT_PRICE"));
					product.setProductImg(rs.getString("PRODUCT_IMG"));
					product.setProductNum(rs.getInt("PRODUCT_NUM"));
//					product.setProductDes(rs.getClob("PRODUCT_DES"));
					
					Clob c = rs.getClob("PRODUCT_DES");
					String clobString = c.getSubString(1, (int) c.length());
					product.setProductDes(clobString);
					
					product.setProductType(rs.getString("PRODUCT_TYPE"));
				
					productList.add(product);
					
					System.out.println(clobString);
					
					}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
