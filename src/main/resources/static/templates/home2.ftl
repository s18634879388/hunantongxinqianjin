<!DOCTYPE html>
<html style="font-size: 80px;" lang="en"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!-- 添加到主屏后的标题（iOS 6 新增） -->
    <meta name="apple-mobile-web-app-title" content="贷款大全">
    <!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <!-- 设置苹果工具栏颜色 -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- 忽略页面中的数字识别为电话，忽略email识别 -->
    <meta name="format-detection" content="telphone=no, email=no">
    <!--清除缓存 微信浏览器缓存严重又无刷新-->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>贷款大全</title>
    <script src="/templates/js/hm.js"></script><script src="/templates/js/hm_002.js"></script><script src="/templates/js/htmlrem.js"></script>
    <link rel="stylesheet" href="/templates/css/reset.css">
    <link rel="stylesheet" href="/templates/css/index.css">
    <script src="/templates/js/zepto.js"></script><script src="/templates/js/index.js"></script></head>
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


<body>
<div class="container">
    <header> <img src="/templates/img/sudaizhijia2x.png" alt=""> <span>贷款大全 | 速贷钱进 <em>${proUpdate?string('yyyy-MM-dd')} 更新</em></span></header>
    <section>
        <div class="centent">

        <#list list1 as name>
            <a href="${name.productUrl}" onclick="myClick(${name.id})">
                <dl> <dt><img src="${name.imgUrl}" alt="用钱宝" title="用钱宝" class="smallIcon"></dt>
                    <dd>
                        <h3>${name.topic}</h3>
                        <p>${name.message}</p>
                    </dd><span><i>2</i></span> </dl>
            </a>
        </#list>

        </div>
    </section>
</div>


</body></html>