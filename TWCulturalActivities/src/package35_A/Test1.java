package package35_A;

import java.util.ArrayList;

public class Test1 {

	public static void main(String[] args) {
				
		JDBCDAO jdbcdao = new JDBCDAO();
		CSVDAO csvdao = new CSVDAO();

//		1.MT表格
		jdbcdao.dropTableMT(); //刪MainTable表格
		jdbcdao.createTableMT(); //創MainTable表格
		
		ArrayList<MainTable> listMT = jdbcdao.readJsonToMT(); //讀取網路上的json到MainTable資料型態
		jdbcdao.mtWriteDB(listMT); //寫入資料到資料庫內的MainTable表格

		csvdao.stringWriteCSV(); //輸出專為轉換經緯度而生的csv檔
	
	}

}
