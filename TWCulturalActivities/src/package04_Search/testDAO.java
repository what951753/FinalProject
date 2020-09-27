package package04_Search;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class testDAO {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
//	SelectDAO selectDAO = new SelectDAO();
//	List<ShowOj> showList = selectDAO.Showlist();
//	for (ShowOj showOj :showList) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		String dateString = showOj.getSTART_DATE();
//		Date date = sdf.parse(dateString);		
//		System.out.println(date);
//	}
//	}

	//開始日查詢
			try {

//	//將request日期字串轉為date
//				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//				String queryVal = request.getParameter("startday");
//				Date requestDate = sdf2.parse(queryVal);
//				System.out.println(requestDate);
	//帶入list內日期字串 轉為date格式	  

				SelectDAO selectDAO = new SelectDAO();
				List<ShowOj> showList = selectDAO.Showlist();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EE");
				//比較日期大小
				for (ShowOj showOj : showList) {
					String dateString = showOj.getACT_START_DATE();
					Date date;
					date = sdf.parse(dateString);
					System.out.println(dateFormat.format(date));
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
