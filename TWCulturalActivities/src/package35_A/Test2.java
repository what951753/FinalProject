package package35_A;

import java.util.ArrayList;

public class Test2 {

	public static void main(String[] args) {
				
		JDBCDAO jdbcdao = new JDBCDAO();
		CSVDAO csvdao = new CSVDAO();

//		2.PT表格
		jdbcdao.dropTablePT(); //刪PositionTable
		jdbcdao.createTablePT(); //創PositionTable
		
		ArrayList<PositionTable> listPT = csvdao.readCSVtoPT(); //讀取內政部回傳的csv檔到PositionTable資料型態
		jdbcdao.ptWriteDB(listPT); //寫入資料到資料庫內的PositionTable表格
	
	}

}
