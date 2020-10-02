<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Objects"%>
<%@ page import="packge14_SaleTicket.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            box-sizing: border-box;


        }

        /* Create two equal columns that floats next to each other */
        .column {
            float: left;
            width: 50%;
            padding: 10px;
            width: 300px;
            height: 300px;
            /* Should be removed. Only for demonstration */
        }

        /* Clear floats after the columns */
        .row:after {
            /* width: 600px; */

            content: "";
            display: table;
            clear: both;
        }

        .row {
            margin-left: 500px;
        }
    </style>
</head>

<body>
    <h2 style="text-align: center;">Two Equal Columns</h2>

    <div class="row">
        <div class="column" style="background-color:#aaa;">
            <h2 class="l" id="jk">Column 1</h2>
            <p>Some text..</p>
            <label class="col-sm-3 control-label">é¸æçï¼</label>
            <select class="select-control" name="YYYY" id="YYYY" onchange="prin()">
                <option value="花枝">花枝</option>
                <option value="魷魚">魷魚</option>
                <option value="章魚">章魚</option>
            </select>
            <select class="select-control" name="YYYY" id="YYY" onchange="prin2()">
                <option value="èè²">èè²</option>
                <option value="ç½è²">ç½è²</option>
                <option value="ç´«è²">ç´«è²</option>
            </select>
        </div>
        <div class="column" style="background-color:#bbb;">
            <h2 id="jk2">Column 2</h2>
            <p id="p1">Some text..</p>
            
     <% 
   		List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
		for(int i=0; i < carList.size(); i++) {
			ProductItem order;
			order = (ProductItem) carList.get(i);
   		%>
   		<p><%= order.getProdutTitle() %><p>
   		<p>數量:<%= order.getProductNum() %><p>
   		
   		<%} %>
           
        </div>
    </div>



    <script>
        var a = "AAAAA";


        function prin() {

            test = document.getElementById("YYYY").value;
            document.getElementById("jk2").innerHTML = (test);
            return test;
        }
        function prin2() {

            
            document.getElementById("p1").innerHTML = (document.getElementById("YYY").value);
            return test;
        }
        var test = "ç";
        document.getElementById("jk").innerHTML = (test);



    </script>
</body>

</html>