<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script src="scripts/jquery.js" type="text/javascript"></script>
<style type="text/css">
    table{ border-collapse:collapse; width:100%; height:100%;}
	table td{ border-collapse:collapse; border:solid 1px #000;}
	table table td{ border-collapse:collapse; border:solid 1px #000; width:15%}
	table table td.two_only_right{border-collapse:collapse; border-top:none;border-left:none;border-bottom:none;}
	table table td.two_only_left{border-collapse:collapse; border-top:none;border-right:none;border-bottom:none;}
	table table td.two_no_border{border-collapse:collapse; border:none;}
	table table table td.three_bottom_right{border-collapse:collapse; border-top:none;border-left:none;}
	table table table td.three_only_bottom{border-collapse:collapse; border-top:none;border-left:none;border-right:none;}
	table table table td.three_only_right{border-collapse:collapse; border-top:none;border-left:none;border-bottom:none;}
	table table table td.three_no_border{border-collapse:collapse; border:none;}
</style>
</head>
<body>
	  <table width="80%" border="1">
			<tr>
				<td >
					是 <input name="radiobutton1" type="radio" value="20" onclick="isCheck(this);" />
					否 <input name="radiobutton1" type="radio" value="0" onclick="isCheck(this);"/>  （20，0）
				</td>
			</tr>
			<tr>
				<td>
					是 <input name="radiobutton2" type="radio" value="50" onclick="isCheck(this);"/>
					否 <input name="radiobutton2" type="radio" value="0"  onclick="isCheck(this);"/> （50，0）
				</td>
			</tr>
	</table>
<script type="text/javascript">
	var radioArr = new Array();
	function isCheck(obj){
		var hasCheck = false;
		
		for(var i=0; i<radioArr.length; i++){
			if(radioArr[i]==obj){
				radioArr[i].checked = false;
				radioArr.splice(i,1); //移除对象
				hasCheck = true;
				break;
			}
		}

		if(!hasCheck){
			initRadioArr();
		}
		//alert(radioArr.join("-"));
	}
	//初始化选中的radio
	function initRadioArr(){
		radioArr = new Array();

		//可根据实际情况修改radio的范围。
		var radios = document.getElementsByTagName("input");
		for(var i=0; i<radios.length; i++){
			if(radios[i].type.toLowerCase()=="radio" && radios[i].checked){
				radioArr.push(radios[i]);
			}
		}
	}

</script>
	
</body>
</html>