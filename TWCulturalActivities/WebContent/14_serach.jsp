<%@page import="java.util.Objects"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="packge14_SaleTicket.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FrontPage</title>
    <style>
        table{
        
            border: solid blue 2px;
             border-collapse: collapse;
        }
        td{
            border:green solid 2px;
        	text-align : center
        }
        
        .td1{
        	width: 300px;
        	
        }
        
       
    </style>
</head>

<body>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <h1>商城首頁</h1>
    
   <%DAOPage daoPage = new DAOPage(); 
   	Product product = new Product();
   	
	List<Product> listProducts = product.listProducts();
    request.setAttribute("listProducts", listProducts);
    int total = listProducts.size();
    int pages = 10;
    double temp = total/pages;
    int tpages = Double.valueOf(Math.ceil(temp)).intValue();
    
    int currentPage = 1;
    int carSize=0;
    
    String sCurrentPage = String.valueOf(pageContext.getRequest().getAttribute("currentPage"));
    if(Objects.equals(sCurrentPage, "null") || Objects.equals(sCurrentPage, "")){
    	pageContext.getRequest().setAttribute("currentPage", currentPage);
    }else{
    	currentPage = Integer.parseInt(sCurrentPage);
    }
   
    if(pageContext.getRequest().getAttribute("carSize")==null){
    	pageContext.getRequest().setAttribute("carSize", 0);
    }
    
    
   %>
   	
	
 	<FORM ACTION="./ProductArray">
    <h3>這是商城首頁的副標題之類的</h3>
    <div><span>購物車項目筆數： </span><span name="carSize"><%=request.getAttribute("carSize") %></span></div>
    <input type="submit" name="check" value="前往結帳"/>
 	</FORM>
 	
 	<FORM ACTION="./ProductArray">
	<input type="hidden" name="method" value="changePage"/>
    <div><span>目前位於第&nbsp<%=currentPage%>&nbsp頁，共&nbsp<%=tpages %>&nbsp頁</span></div>
    <select name="currentPage" onchange="this.form.submit()" >
    
    <% for(int i=1 ; i <= tpages ; i++) { %>
	<% if(i==currentPage){ %>
		<option selected text-align : center>&nbsp<%= i%>&nbsp</option>
	<% }else{ %>
	
	<option><%= i%></option>
	
	<% }} %>

    </select>
    </FORM>
    
    
   <!--  <input type="submit"> -->
    <FORM ACTION="./ProductArray" name="method" value="selectItem">
    <input type="hidden" name="method" value="selectItem"/>
	<input type="hidden" name="currentPage" value="<%=currentPage%>"/>
    
    <table id="mytable" >
    <tbody>

    
    
    <%
    List<Product> subList = listProducts.subList((currentPage-1)*pages, currentPage*pages);
    for (Product productItem : subList) {
    %> 
    
            
			
            <tr>
                <td class="td1"><img src="<%=productItem.getProductImg() %>" title="圖片提示文字" alt="123"></td>
                
                <td rowspan="5" width="600px"><%=productItem.getProductDes() %></td>
            </tr>
            <tr>            
                <td class="td1" style="text-align: center ;" name="pTitle"><%=productItem.getProductTitle() %></td>            
            </tr>
            <tr>
                <td class="td1" style="text-align: center;" name="pPrice"><%=productItem.getProductPrice() %>$NTD</td>
            </tr>
            <tr>             
                <td class="td1" style="text-align: center;">目前庫存： <%=productItem.getProductNum() %></td>
            </tr>
            
            <tr>
            <td class="td1" id="flagId">請選擇數量：
<%-- 	         <input type="number" min="1" max="<%=productItem.getProductNum() %>" id="itemNum" name="orderNum" value="1"><td> --%>
            </tr>
            
            <tr>
            <td style="text-align: center;"><button type="submit" name="param1" 
            value="<%=productItem.getProductTitle() %>" id="checkBut" >加入購物車 </button></td>
            </tr>
            
            
        <% } %>
       
           
            
        </tbody>
    </table>
    </FORM>
    
    
    
</body>
</html>