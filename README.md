# TWCulturalActivities-V1.0
  
這是main分支      
大家先按右上角fork，建立新的分支到自己帳號內，再clone帳號內的分支到本地電腦  
前幾天的ForkThisProject分支可以刪掉了~  
  
  
第一步. ***import專案***  
   
   
1. 新增C:\Java\FinalProject資料夾，並用eclipse開一個新的workspace，路徑是C:\Java\FinalProject  
2. 在該workspace import下載的TWCulturalActivities資料夾。其他下載的資料夾可以丟到C:\Java\FinalProject內，裡面有一些是輸入輸出csv檔要用到的文件
3. 新增server，context內增加的敘述“其它資料/Server and webxml設定/context config.txt”內可以複製貼上，設定完成後紅色叉叉就會消失  
4. 執行專案內Java Resources/src/package35_Model內的Test1.class，會重新建立MAINTABLE和插入資料，這版本的主表格有新增一個NO欄位，執行專案內Java Resources/src/package35_A內的Test2.class，會建立含活動地點經緯度的LOCATIONTABLE  
5. MAINTABLE不需手動新增或修改，它的用途是把文化部的網路資料塞到資料庫，方便大家去查詢測試自己要做的功能  
6. MAINTABLE欄位都是ACT_(活動)開頭，欄位詳細說明參見:https://opendata.culture.tw/upload/dataSource/2019-10-29/2adf6cae-fe57-4e01-a380-b45222e38ad1/7e69be410cfd8ba4f9695c5cab9bc87f.pdf
7. 如果功能需要運算欄位資料，可以select MAINTABLE欄位資料放到另一個新增的表格，e.g. LOCATIONTABLE
8. 最終SQL查詢資料的呈現如果會橫跨兩張表格的資料，可以用join的方式一次查兩張表
9. 每個人寫的程式碼都在Java Resources/src下開不同package放，package命名格式是package+座號+_自由命名，e.g. package35_A，package03_BB，package04_CCC
11. ＷebContent下的html網頁也要在檔名前方上座號，e.g. 35_XXX.html，03_XXX.html
12. 號碼後面的XXX和CCC都是可以按照個人習慣命名，記得前面加座號就好
13. 如果之後要用到別人的方法可以import其它人的package
  
第二步. ***仿照上課內容寫好透過 Datasource 方式串接JDBC連線到資料庫的Servlet程式***  


