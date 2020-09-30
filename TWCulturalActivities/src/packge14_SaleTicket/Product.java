package packge14_SaleTicket;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private String productId;
	private String productTitle;
	private String productPrice;
	private String productImg;
	private int productNum;
	private String productDes;
	private String productType;
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductDes() {
		return productDes;
	}
	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}
	
	
	public List<Product> listProducts() {
		List<Product> productList = new ArrayList<Product>();
		
		DAOPage daoPage = new DAOPage(); 
        
		try  
			(Connection connection = daoPage.getDataSourcePJ().getConnection();
			Statement stmt= connection.createStatement();
			ResultSet rs= stmt.executeQuery("select PRODUCT_ID, PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_IMG, PRODUCT_NUM, PRODUCT_DES, PRODUCT_TYPE from "
					+ "PRODUCT ORDER BY 2 ");){
								
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
					
					}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
		
	}

}
