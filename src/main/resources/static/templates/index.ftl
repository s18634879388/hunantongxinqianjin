<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>贷款大全</title>
    <link rel="stylesheet" type="text/css" href="/templates/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/templates/css/index.css">
    <script src="/templates/js/rem.js"></script>
    <script>

        function myClick(id1){
            var xmlhttp;
            if (window.XMLHttpRequest)
            {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            }
            else
            {// code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
                }
            }
            xmlhttp.open("POST","/addRecord?id="+id1,true);
            xmlhttp.send();
        }
    </script>
</head>
<body>
<div class="mainContent">
    <div class="title">
        <p><img src="/templates/img/05.png">  <span style="display:inline-block; margin-right:0.2rem;">贷款大全 | 小额贷款</span><span class="date" style="font-size:0.14rem;">${proUpdate?string('yyyy-MM-dd')}</span><span class="date" style="font-size:0.14rem;">更新</span></p>
    </div>
    <div class="content clearfix">
        <#--<div class="content_item">-->
            <#--<a href="http://www.baidu.com" onclick="myClick()"><img src="/templates/img/01.png" ></a>-->
            <#--<h6 class="item_title">-->
                <#--现金贷款-->
            <#--</h6>-->
            <#--<p>资料简单2000到手</p>-->
        <#--</div>-->
        <#--<div class="content_item">-->
            <#--<img src="/templates/img/02.png" onclick="myClick()">-->
            <#--<h6 class="item_title">-->
                <#--现金白卡-->
            <#--</h6>-->
            <#--<p>说到就到现金极速贷款</p>-->
        <#--</div>-->
        <#--<div class="content_item">-->
            <#--<img src="img/02.png">-->
            <#--<h6 class="item_title">-->
                <#--现金白卡-->
            <#--</h6>-->
            <#--<p>说到就到现金极速贷款</p>-->
        <#--</div>-->
        <#--<div class="content_item">-->
            <#--<img src="img/03.png">-->
            <#--<h6 class="item_title">-->
                <#--现金贷款-->
            <#--</h6>-->
            <#--<p>资料简单2000到手</p>-->
        <#--</div>-->


        <#list list1 as name>
            <div class="content_item">
                <a href="${name.productUrl}"><img src="${name.imgUrl}"  onclick="myClick(${name.id})"></a>
                <h6 class="item_title">
                    ${name.topic}
                </h6>
                <p>${name.message}</p>
            </div>
        </#list>
    </div>
</div>
</body>
</html>
