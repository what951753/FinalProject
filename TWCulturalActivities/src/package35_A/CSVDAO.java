package package35_A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CSVDAO {
	
	//MT轉字串
	public String mtToString() {
		
		JDBCDAO jdbcdao = new JDBCDAO();
		ArrayList<MainTable> list = jdbcdao.readDBtoMT();
		
		StringBuilder sb = new StringBuilder();
		sb.append("ID(非必填),縣市(必填),鄉鎮(必填),村里(可不填),鄰(可不填),地址(必填)\n");
		String addressStr="";
		for(MainTable item: list) {
			addressStr = item.getLocation();
			if (addressStr!=null) {
				if (addressStr.contains(",")){
					addressStr  = item.getLocation().replace(",", "、");
				}
				if (addressStr.regionMatches(0, "202", 0, 3)){
					sb.append(item.getNo()+","+addressStr.substring(4,7)+","+addressStr.substring(7,10)+","+","+","+addressStr.substring(10)+"\n");
				}else if (addressStr.regionMatches(0, "74彰", 0, 3)) {
					sb.append(item.getNo()+","+addressStr.substring(2,5)+","+addressStr.substring(5,8)+","+","+","+addressStr.substring(8)+"\n");
				}else if (addressStr.charAt(0) >= 48 && addressStr.charAt(0) <= 57 && addressStr.indexOf("區") == 9 && addressStr.length() > 10) {
					sb.append(item.getNo()+","+addressStr.substring(5,8)+","+addressStr.substring(8,10)+","+","+","+addressStr.substring(10)+"\n");
				}else if (addressStr.charAt(0) >= 48 && addressStr.charAt(0) <= 57 && addressStr.length() > 11) {
					sb.append(item.getNo()+","+addressStr.substring(5,8)+","+addressStr.substring(8,11)+","+","+","+addressStr.substring(11)+"\n");
				}else if (addressStr.length() > 6 && addressStr.indexOf("區") == 4){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,5)+","+","+","+addressStr.substring(5)+"\n");
				}else if (addressStr.length() > 6){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,6)+","+","+","+addressStr.substring(6)+"\n");
				}else if (addressStr.length() > 5){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,6)+","+","+","+"\n");
				}else if (addressStr.length() > 4){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,5)+","+","+","+"\n");
				}else {
					sb.append(item.getNo()+","+","+","+","+","+"\n");
				}
			}else {
				sb.append(item.getNo()+","+","+","+","+","+"\n");
			}
			
		}
		String str = String.valueOf(sb);
		System.out.println("MainTable物件已轉換為字串物件");
		return str;
		
	}
	
	//字串寫入csv檔
	public void stringWriteCSV() {

//		FileOutputStream某參數設定true，資料會從覆蓋寫入變成插入
		try( FileOutputStream fos = new FileOutputStream("C:\\Java\\FinalProject\\outputCSV\\1.csv");//Win
//		try( FileOutputStream fos = new FileOutputStream("/Users/bill/projectx-workspace/outputCSV/1.csv");//Mac
				OutputStreamWriter osw = new OutputStreamWriter(fos, "BIG5");
				BufferedWriter bw = new BufferedWriter(osw);
				){
			
			String string = mtToString();
			bw.write(string);
			
		} catch (FileNotFoundException e) { //file not found時的exception			
			e.printStackTrace();
		} catch (IOException e) { //close時會丟出的exception
			e.printStackTrace();
		}
		System.out.println("字串物件已寫入至csv檔");

	}
	
	//讀取內政部轉換下來的csv檔，轉成ArrayList<PositionTable>資料型態
	public ArrayList<PositionTable> readCSVtoPT() {

		ArrayList<PositionTable> list = new ArrayList<PositionTable>();
//		FileOutputStream某參數設定true，資料會從覆蓋寫入變成插入
		try( FileInputStream fis = new FileInputStream("C:\\Java\\FinalProject\\inputCSV\\2.csv");//Win
//		try( FileInputStream fis = new FileInputStream("/Users/bill/projectx-workspace/inputCSV/2.csv");//Mac
				InputStreamReader isr = new InputStreamReader(fis, "BIG5");
				BufferedReader br = new BufferedReader(isr);
				){
			
			StringBuilder sb = new StringBuilder();
			int c; //BufferedReader.read()回傳的是int型態，先建變數接
			while ((c=br.read())!=-1) {	
				char d=(char)c;  //ASCII型態的數字轉成位元代碼
				sb.append(String.valueOf(d)); //位元代碼轉人類看的字串
			}
			
			String str =String.valueOf(sb);
			String[] strArr = str.split("[,\n\r]");
//			for (String item: strArr) {
//				System.out.println(item);
//			}
			for (int i=13; i<strArr.length; i+=13) {
				PositionTable pt = new PositionTable();
				if(strArr[i+8].equals("門牌比對失敗或格式有誤")) {
					pt.setNo(Integer.valueOf(strArr[i]));
					pt.setCity(null);
					pt.setDistrict(null);
					pt.setVillage(null);
					pt.setAddress(null);
					pt.setLatitude(new Double(0));
					pt.setLongitude(new Double(0));	
				}else {
					pt.setNo(Integer.valueOf(strArr[i]));
					pt.setCity(strArr[i+1]);
					pt.setDistrict(strArr[i+2]);
					pt.setVillage(strArr[i+3]);
					pt.setAddress(strArr[i+4]);
					pt.setLatitude(new Double(strArr[i+10]));
					pt.setLongitude(new Double(strArr[i+9]));	
				}
				list.add(pt);
			}
			
//			for (PositionTable item: list) {
//				System.out.println(item.getNo());
//				System.out.println(item.getCity());
//				System.out.println(item.getDistrict());
//				System.out.println(item.getVillage());
//				System.out.println(item.getAddress());
//				System.out.println(item.getLatitude());
//				System.out.println(item.getLongitude());
//			}
			
		} catch (FileNotFoundException e) { //file not found時的exception			
			e.printStackTrace();
		} catch (IOException e) { //close時會丟出的exception
			e.printStackTrace();
		}
		System.out.println("已讀取經緯度csv檔為PositionTable物件");
		return list;
		
	}

}
