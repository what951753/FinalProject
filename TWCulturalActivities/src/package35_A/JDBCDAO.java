package package35_A;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JDBCDAO {
private DataSource dataSource;
	
	//getDataSource連線的方法
	public DataSource getDataSource() {//只需get因為只要用不希望被改
		
		//lazy init，能有多晚用到就多晚產生，放constructor會先產出佔用記憶體
		if (dataSource == null) { //不希望重複產生，
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("group4");
			ds.setPassword("oracle");
			ds.setMaxTotal(50); 
			ds.setMaxIdle(50);  			

			dataSource=ds; //把BasicDataSource放在屬性上
		}
		return dataSource;
	}
	
	//創MainTable表格
	public void createTableMT() {
		
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE MAINTABLE (ACT_NO NUMBER(8,2), ACT_UID VARCHAR2(1000), ACT_TITLE VARCHAR(1000), ACT_CATEGORY NUMBER(8,2),  ACT_LOCATION VARCHAR2(1000), ACT_LOCATION_NAME VARCHAR2(1000), ACT_ON_SALES VARCHAR2(1000), ACT_PRICE VARCHAR2(4000), ACT_TIME VARCHAR2(1000), ACT_ENDTIME VARCHAR2(1000), ACT_MAINUNIT VARCHAR2(1000), ACT_SHOWUNIT VARCHAR2(1000), ACT_COMMENT VARCHAR2(4000), ACT_DESCRIPTION VARCHAR2(4000), ACT_IMAGE VARCHAR2(1000), ACT_STARTDATE VARCHAR2(1000), ACT_ENDDATE VARCHAR2(1000), PRIMARY KEY(ACT_NO))";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("主表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪MainTable表格
	public void dropTableMT() {
		
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE MAINTABLE CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("主表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MainTable> readJsonToMT() {
		
		ArrayList<MainTable> list = new ArrayList<MainTable>();
		try (InputStream is = new URL("https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category=all").openStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF8");
				BufferedReader br = new BufferedReader(isr);	
				) {
			int c;
			StringBuilder strBuilder = new StringBuilder();
			while ((c = br.read())!=-1) {
				char d =(char)c;
				strBuilder.append(String.valueOf(d));
			}
			
			String tableStr= String.valueOf(strBuilder);
//			若資料不乾淨要用下面方法篩選符號
//			System.out.println(tableStr);
//			String result1 = tableStr.replaceAll("\\\\", "");
//			String result2 = result1.replaceAll(Matcher.quoteReplacement("$"), "");
//			String result3 = result2.substring(1, result2.length()-1);
//			System.out.println(result3);
			
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<MainTable>>() {}.getType();
			ArrayList<MainTable> jsonArr = gson.fromJson(tableStr,listType);
			
			int counter = 0;
			for (MainTable item: jsonArr) {
				MainTable mt = new MainTable();
				counter++;
				mt.setNo(counter);
				mt.setUid(item.getUid());
				mt.setTitle(item.getTitle());
				mt.setCategory(item.getCategory());
//				System.out.println("UID:"+ item.getUid());
//				System.out.println("title:"+ item.getTitle());
//				System.out.println("category:"+ item.getCategory());
				
//				"showInfo"
//				System.out.println("ArrayList<HashMap> showInfo:"+ item.getShowInfo());
				HashMap hashMap1= new HashMap();
				for (int i=0 ; i < item.getShowInfo().size(); i++) {
					hashMap1 = item.getShowInfo().get(i);										
				}
				mt.setLocation(String.valueOf(hashMap1.get("location")));
				mt.setLocationName(String.valueOf(hashMap1.get("locationName")));
				mt.setOnSales(String.valueOf(hashMap1.get("onSales")));
				mt.setPrice(String.valueOf(hashMap1.get("price")));
				mt.setTime(String.valueOf(hashMap1.get("time")));
				mt.setEndTime(String.valueOf(hashMap1.get("endTime")));
//				System.out.println("location:"+hashMap1.get("location"));
//				System.out.println("locationName:"+hashMap1.get("locationName"));
//				System.out.println("onSales:"+hashMap1.get("onSales"));
//				System.out.println("price:"+hashMap1.get("price"));
//				System.out.println("time:"+hashMap1.get("time"));
//				System.out.println("endTime:"+hashMap1.get("endTime"));

//				"masterUnit"
//				System.out.println("ArrayList<String> masterUnit:"+ item.getMasterUnit());
				String str1= "";
				for (int i=0 ; i < item.getMasterUnit().size(); i++) {
					str1 = item.getMasterUnit().get(i);										
				}
				mt.setMainUnit(str1);
//				System.out.println("masterUnit:"+str1);
				
				mt.setShowUnit(item.getShowUnit());
				mt.setComment(item.getComment());
				mt.setDescriptionFilterHtml(item.getDescriptionFilterHtml());
				mt.setImageUrl(item.getImageUrl());				
//				System.out.println("showUnit:"+ item.getShowUnit());
//				System.out.println("comment:"+ item.getComment());
//				System.out.println("descriptionFilterHtml:"+ item.getDescriptionFilterHtml());
//				System.out.println("imageUrl:"+ item.getImageUrl());
				
				mt.setStartDate(item.getStartDate());
				mt.setEndDate(item.getEndDate());
//				System.out.println("startDate:"+ item.getStartDate());
//				System.out.println("endDate:"+ item.getEndDate());
				list.add(mt);
			}
			
//			int counter=0;
//			for (MainTable item: list) {
//				counter++;
//				System.out.println(item.getLocation());
//			}
//			System.out.println( counter);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取文化部json檔為MainTable物件");
		return list;
	}
	
	public void mtWriteDB(ArrayList<MainTable> list) {
		
		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement("insert into MainTable (ACT_NO, ACT_UID, ACT_TITLE, ACT_CATEGORY,  ACT_LOCATION, ACT_LOCATION_NAME, ACT_ON_SALES, ACT_PRICE, ACT_TIME, ACT_ENDTIME, ACT_MAINUNIT, ACT_SHOWUNIT, ACT_COMMENT, ACT_DESCRIPTION, ACT_IMAGE, ACT_STARTDATE, ACT_ENDDATE) values (?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?,?)");
				){
		
			for (MainTable item: list) {
				pstmt.setInt(1, item.getNo());
				pstmt.setString(2, item.getUid());
				pstmt.setString(3, item.getTitle());
				pstmt.setInt(4, item.getCategory());
				pstmt.setString(5, item.getLocation());
				pstmt.setString(6, item.getLocationName());
				pstmt.setString(7, item.getOnSales());
				pstmt.setString(8, item.getPrice());
				pstmt.setString(9, item.getTime());
				pstmt.setString(10, item.getEndTime());
				pstmt.setString(11, item.getMainUnit());
				pstmt.setString(12, item.getShowUnit());
				pstmt.setString(13, item.getComment());
				if(item.getDescriptionFilterHtml().length()<1500) {
					pstmt.setString(14, item.getDescriptionFilterHtml());
				}else {
					pstmt.setString(14, "");
				}
				pstmt.setString(15, item.getImageUrl());
				pstmt.setString(16, item.getStartDate());
				pstmt.setString(17, item.getEndDate());
				pstmt.addBatch();//加入批次
				pstmt.clearParameters();//清除參數再加入下一物件的值
			}
			pstmt.executeBatch();
			pstmt.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("MainTable物件已寫入資料庫");
		
	}
	
	public ArrayList<MainTable> readDBtoMT() {
		 
		ArrayList<MainTable> list = new ArrayList<MainTable>();//建立空arraylist放置增添的物件	
		try (	Connection connection = getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select ACT_NO, ACT_LOCATION from MAINTABLE");//執行sql查詢				
				){
				
			while( rs.next() ) {
				MainTable mt = new MainTable();
				mt.setNo(rs.getInt("ACT_NO"));
				mt.setLocation(rs.getString("ACT_LOCATION"));
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取資料庫MainTable表格為MainTable物件");
		return list;
	}

	public void ptWriteDB(ArrayList<PositionTable> list) {
		
		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement("insert into PositionTable (NO, CITY, DISTRICT,  VILLAGE, ADDRESS, LATITUDE, LONGITUDE) values (?, ?, ?, ?, ?,?,?)");
				){
		
			for (PositionTable item: list) {
				pstmt.setInt(1, item.getNo());
				pstmt.setString(2, item.getCity());
				pstmt.setString(3, item.getDistrict());
				pstmt.setString(4, item.getVillage());
				pstmt.setString(5, item.getAddress());
				pstmt.setDouble(6, item.getLatitude());
				pstmt.setDouble(7, item.getLongitude());
				pstmt.addBatch();//加入批次
				pstmt.clearParameters();//清除參數再加入下一物件的值
			}
			pstmt.executeBatch();
			pstmt.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("PositionTable物件已寫入資料庫");
		
	}
	
	//創PositionTable表格
	public void createTablePT() {
		
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE POSITIONTABLE (NO Number(8,2), CITY VARCHAR(1000), DISTRICT VARCHAR(1000),  VILLAGE VARCHAR2(1000), ADDRESS VARCHAR2(1000),  LATITUDE NUMBER(25, 20), LONGITUDE NUMBER(25, 20), PRIMARY KEY(NO))";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("POSITION表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪PositionTable表格
	public void dropTablePT() {
		
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE POSITIONTABLE CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("POSITION表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
