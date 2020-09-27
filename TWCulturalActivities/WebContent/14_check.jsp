<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        input {
            width: 50px;
        }
    </style>
</head>

<body>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <FORM ACTION="./Check_new">
    <h2>訂票確認頁面</h2>
    <div>您預定的節目為：</div>
    <br>
    <div>

        <%=request.getParameter("param1")%>
        
        <input type="hidden" name="param1" value='<%=request.getParameter("param1")%>'>
        

    </div>
    <br>
    <div>請輸入欲購買的票種數量 (一人兩種票種限購 5 張)</div>
    <div>
        <label>全票數量:</label>
        <span id="sp1"><input type="number" id="adlt" name="param2" onchange="checka()" value="0" min="0" max="5"><br></span>
    </div>
    <div>
        <label>半票數量:</label>
        <span id="sp2"><input type="number" id="half" name="param3" onchange="checkb()" value="0" min="0" max="5"><br></span>
    </div>
    <div>
        <button onclick="cal()">計算金額</button>
    </div>
    <div id="total"></div>
    <br><br>
    <button type="submit" onclick="checkNull()" disabled="disabled" id="checkBut">確認送出</button>



    <script>


        let flag_a = false;
        let flag_h = false;

        function cal() {
            let cost = 0;
            var adltCost = document.getElementById("adlt");
            var adltValue = adltCost.value;
            var halfCost = document.getElementById("half");
            var halfValue = halfCost.value;
            var totalEl = document.getElementById("total");
            var totalC = halfValue * 500 + adltValue * 1000;
            totalEl.innerHTML = ("總計: " + totalC)


        };

        function checkNull() {
            var adltCost = document.getElementById("adlt");
            var adltValue = adltCost.value;
            var aValue = parseInt(adltValue);
            var halfCost = document.getElementById("half");
            var halfValue = halfCost.value;
            var hValue = parseInt(halfValue);
            var totalEl = document.getElementById("total");
            console.log(adltCost);
            console.log(adltValue);

            // if (adltValue < 0 || halfValue < 0) {
            //     adltCost.innerHTML = '<input type="number" class="adlt" name="param2" value="0"><br>';
            //     halfCost.innerHTML = '<input type="number" class="half" name="param3" value="0"><br>';
            //     alert('欄位不可小於 0')

            // }
            if ((aValue + hValue) > 5) {

                adltCost.innerHTML = '<input type="number" class="adlt" name="param2" value="0"><br>';
                halfCost.innerHTML = '<input type="number" class="half" name="param3" value="0"><br>';
                console.log(adltValue);
                console.log(aValue + hValue);
                alert('一人兩種票種合計限購 5 張')

            }
        }

        function checka() {
            var adltCost = document.getElementById("adlt");
            var sp1Obj = document.getElementById("sp1");
            var adltValue = adltCost.value;
            var aValue = parseInt(adltValue);


            if (aValue > 0) {
                console.log("a on")
                flag_a = true;
                flagon_a();
            } else {
                flag_a = false;
                var adltValue = 0;
                console.log("change")
                sp1Obj.innerHTML='<input type="number" id="adlt" name="param2" onchange="checka()" value="0" min="0" max="5"><br>'
                flagon_a();

            }


        }

        function checkb() {
            var halfCost = document.getElementById("half");
            var sp2Obj = document.getElementById("sp2");
            var halfValue = halfCost.value;
            var hValue = parseInt(halfValue);
            console.log("checkb on")
            console.log(hValue)

            if (hValue > 0) {
                flag_h = true;
                console.log("b on")
                flagon_h();
            } else {
                flag_h = false;
                console.log("b off")
                sp2Obj.innerHTML='<input type="number" id="half" name="param3" onchange="checkb()" value="0" min="0" max="5"><br>'
                // var halfValue = 0;
                flagon_h();
            }


        }
        function flagon_a() {
            var adltCost = document.getElementById("adlt");

            // $("#adlt").bind("input onchange", function (event) {
            console.log($("#adlt").val())
            console.log("flag_h:" + flag_h + "__flag_a:" + flag_a)
            if (flag_h == true || flag_a == true) {
                console.log("into if")
                $('#checkBut').removeAttr('disabled');
                

                // }
            }
            else  {
                console.log("turn off")
                $('#checkBut').attr('disabled',true);
                // adltCost.innerHTML='<input type="number" class="half" name="param3" value="0"><br>'

            }
        }



        // });
        // $(document).ready(function () {
        function flagon_h() {
            var halfCost = document.getElementById("half");

            // $("#half").bind("input onchange", function (event) {
            console.log($("#half").val())
            console.log("flag_h:" + flag_h + "__flag_a:" + flag_a)
            if (flag_h == true || flag_a == true) {
                console.log("into if")
                $('#checkBut').removeAttr('disabled');

            }
            else  {
                console.log("turn off")
                halfCost.innerHTML = ('<input type="number" class="half" name="param3" >0<br>')
                $('#checkBut').attr('disabled',true);

            }
        };
        // }
    </script>



</body>

</html>