
//生日
(function($){ 
$.extend({ 
ms_DatePicker: function (options) { 
   var defaults = { 
         YearSelector: "#sel_year", 
         MonthSelector: "#sel_month", 
         DaySelector: "#sel_day", 
         FirstText: "--", 
         FirstValue: 0 
   }; 
   var opts = $.extend({}, defaults, options); 
   var $YearSelector = $(opts.YearSelector); 
   var $MonthSelector = $(opts.MonthSelector); 
   var $DaySelector = $(opts.DaySelector); 
   var FirstText = opts.FirstText; 
   var FirstValue = opts.FirstValue; 
 
   // 初始化 
   var str1 = "<option value=\"" + FirstValue + "\">"+'年'+"</option>";
   var str2 = "<option value=\"" + FirstValue + "\">"+'月'+"</option>"; 
   var str3 = "<option value=\"" + FirstValue + "\">"+'日'+"</option>"; 
   $YearSelector.html(str1); 
   $MonthSelector.html(str2); 
   $DaySelector.html(str3); 
 
   // 年份列表 
   var yearNow = new Date().getFullYear(); 
   var yearSel = $YearSelector.attr("rel"); 
   for (var i = yearNow; i >= 1900; i--) { 
        var sed = yearSel==i?"selected":""; 
        var yearStr = "<option value=\"" + i + "\" " + sed+">"+i+"</option>"; 
        $YearSelector.append(yearStr); 
   } 
 
    // 月份列表 
    var monthSel = $MonthSelector.attr("rel"); 
    for (var i = 1; i <= 12; i++) { 
        var sed = monthSel==i?"selected":""; 
        var monthStr = "<option value=\"" + i + "\" "+sed+">"+i+"</option>"; 
        $MonthSelector.append(monthStr); 
    } 
 
    // 日列表(仅当选择了年月) 
    function BuildDay() { 
        if ($YearSelector.val() == 0 || $MonthSelector.val() == 0) { 
            // 未选择年份或者月份 
            $DaySelector.html(str3); 
        } else { 
            $DaySelector.html(str3); 
            var year = parseInt($YearSelector.val()); 
            var month = parseInt($MonthSelector.val()); 
            var dayCount = 0; 
            switch (month) { 
                 case 1: 
                 case 3: 
                 case 5: 
                 case 7: 
                 case 8: 
                 case 10: 
                 case 12: 
                      dayCount = 31; 
                      break; 
                 case 4: 
                 case 6: 
                 case 9: 
                 case 11: 
                      dayCount = 30; 
                      break; 
                 case 2: 
                      dayCount = 28; 
                      if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) { 
                          dayCount = 29; 
                      } 
                      break; 
                 default: 
                      break; 
            } 
                     
            var daySel = $DaySelector.attr("rel"); 
            for (var i = 1; i <= dayCount; i++) { 
                var sed = daySel==i?"selected":""; 
                var dayStr = "<option value=\"" + i + "\" "+sed+">" + i + "</option>"; 
                $DaySelector.append(dayStr); 
             } 
         } 
      } 
      $MonthSelector.change(function () { 
         BuildDay(); 
      }); 
      $YearSelector.change(function () { 
         BuildDay(); 
      }); 
      if($DaySelector.attr("rel")!=""){ 
         BuildDay(); 
      } 
   } // End ms_DatePicker 
}); 
})(jQuery);

//姓名验证
function nameYanz(z_name){
	//姓名格式判断
	for (i = 0; i < arrayObj.length; i++){
		if (z_name.indexOf(arrayObj[i]) >= 0){  
			//alert('姓名中不允许出现 ' + arrayObj[i]);
			alert('请填写正确的姓名')
			return  false;
		};
	};
	//百家姓判断
	if (checkXingFun(z_name) == false) {
		alert('请填写正确的姓名');
		return  false;
	};
	//长度判断
	var w=z_name.length;
	if(w<2 || w>4){
		alert('请填写正确的姓名');
		return  false;
	};
	//是否为空
	if(z_name==''){
		alert('请填写正确的姓名');
		return  false;
	};
};

//验证手机号
function telYanz(z_tel){
	if (CheckPhone(z_tel) == false) {
	    $('#mytel').val('');
        alert('请正确填写11位手机号');
		return false;
    };
};

//判断手机号内容
function CheckPhone(phoneNo) {

    if (isNaN(phoneNo) == true) return false;
    if (phoneNo.length != 11) return false;

    var vList3 = "134*135*136*137*138*139*147*150*145*151*152*157*158*159*181*182*183*184*185*186*187*188*130*131*132*154*155*156*185*186*133*153*180*181*189*170*171*172*173*174*175*176*177*178*179";
    var str3 = phoneNo.substring(0, 3);
    var myPhone = new Array();
    myPhone = vList3.split("*");
    var ii;
    var blnValide = false;
    for (ii = 0; ii < myPhone.length; ii++) {
        if (myPhone[ii] == str3) {
            blnValide = true;
            break;
        }
    }
    if (blnValide == false)
        return false;
    //判断是否有连续的数字 
    var jj = 0;
    var xx;
    var intPre = 0;
    var intNext = 0;
    var CFCount = 1;
    var CFCount2 = 1;
    var yy = 0;
    for (ii = 6; ii < phoneNo.length; ii++) {
        xx = phoneNo.substring(ii, ii + 1);
        intPre = parseInt(xx);
        CFCount = 1;
        //if (intPre == 0) { intPre = 10; CFCount--; };
        for (jj = ii + 1; jj < phoneNo.length; jj++) {
            xx = phoneNo.substring(jj, jj + 1);
            intNext = parseInt(xx);
            if (intNext == 0) { intNext = 10; CFCount--; }
            yy = intNext - intPre;
            //yy = Math.abs(yy);
            if (yy == 1) {
                CFCount++;
                intPre = intNext;
                if (CFCount >= 5) {
                    blnValide = false;
                    break;
                }
            }else {
                CFCount = 0;
                break;
            };
        };

    };
    for (ii = 6; ii < phoneNo.length; ii++) {
        xx = phoneNo.substring(ii, ii + 1);
        intPre = parseInt(xx);
        CFCount = 1;
        if (intPre == 0) { intPre = 10; CFCount--; };
        for (jj = ii + 1; jj < phoneNo.length; jj++) {
            xx = phoneNo.substring(jj, jj + 1);
            intNext = parseInt(xx);
            if (intNext == 0) { intNext = 10; CFCount--; }
            yy = intNext - intPre;
            //yy = Math.abs(yy);
            if (yy == -1) {
                CFCount++;
                intPre = intNext;

                if (CFCount >= 5) {
                    blnValide = false;
                    break;
                }

            }
            else {
                CFCount = 0;
                break;
            }
        }

    };
    intPre = phoneNo.substring(6, 7);
    for (ii = 6; ii < phoneNo.length; ii++) {

        intNext = phoneNo.substring(ii + 1, ii + 2);

        if (intPre == intNext) {
            CFCount2++;
            if (CFCount2 >= 5) {
                blnValide = false;
                break;
            }
        }
        else {
            CFCount2 = 1;
        }
        intPre = intNext;
    }
    return blnValide;
};

function dayYanz(z_year,z_month,z_day){
	if(z_year==0 || z_month==0 || z_day==0){
		alert('请选择出生日期');
		return false;
	};
};

//倒计时
var z_set;

function setTime() {
    var setTime_time = parseInt($('#yzm-btn').val());
    if (setTime_time > 0) {

        $('#yzm-btn').val(setTime_time - 1 + "秒");
    } else {
        $('#yzm-btn').val('重新发送');
        $('#yzm-btn').removeAttr("disabled");
        clearInterval(z_set)
    };
};

var zt = true;
//发送验证码 or 验证
function sendYzm(vMobi, op) {
    var code = $("#yzm").val();
    $.ajax({
        type: "POST",
        url: "sendMsg.aspx?op=" + op + "&code=" + code,
        async: false,
        data: "mobile=" + vMobi,
        success: function (msg) {
            if (op == "1") {
                if (msg == "0#1") {
                    $('#yzm-btn').attr("disabled", "disabled");			//锁定按钮
                    $('#yzm-btn').val(60 + "秒");				//倒计时
                    z_set = setInterval("setTime()", 1000);			//倒计时
                    
                } else {
                    if (msg == "error")
                        alert("发送失败！");
                    else
                        alert(msg);
                        zt = false;
                }
            } else {
                if (msg != "0") {
                    alert("验证码错误！");
                    zt = false;
                } else {

                    zt = true;
                }
            }
        }
    });
    return zt;
};































