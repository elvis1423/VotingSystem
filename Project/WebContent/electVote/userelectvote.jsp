<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/templatemo_style.css"/>
<link rel="stylesheet" href="res/preuservote_css/zzsc.css" type="text/css" />
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/jquery.json-2.4.min.js" type="text/javascript"></script>
<style>
body {
background-color: #F0F8FD;
}
</style>
</head>
<body>
<div id="NoElectVoteDiv">没有需要投票的项目，请联系管理员</div>
<div id="templatemo_content">
<div align="center"><H2>${electVoteConfig.electVote.name }</H2></div>
-------------------------------------------------------------------------------------------
	<s:if test="electVoteConfig.electVote.isDifferential == 0">
		<div>说明: 必须为所有候选项投票（等额投票）</div>
	</s:if>
	<s:else>
		<div>说明: 只能为  ${electVoteConfig.electVote.mayChooseCount } 个候选项投票（差额投票）</div>
	</s:else>
	<br>
	<br>
			<div align="center"  class=" margin_r_30"></div>
					<div align="center"  class=" margin_r_30"></div>
	<s:iterator value="electVoteConfig.options" var="option" status="optionStat">
		
			<div align="center" class="product_box">
				<table>
					<tr>
						<td> <h3>${name }</h3></td>
					</tr>
					<tr>
						<td><p>${content }<br>
						<s:if test="imgUrl!=null">
							<a href="downloadfile.action?fileUrl=${imgUrl}">附件，请点击查看或下载</a>
						</s:if>
						</p></td>
					</tr>
					<tr id="ElectVoteOptionTR">
						<td><h4>
							<input id="ElectVoteResultIdInput" type="hidden" value="${loginStatus.userId}_${electVoteConfig.electVote.id}_${id}">
							<s:if test="electVoteConfig.decisionMap['agree'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Agree_${optionStat.index }" value="1" onclick="isCheck(this);"><label for="Radio_Agree_${optionStat.index }">同意</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['oppose'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Oppose_${optionStat.index }" value="2" onclick="isCheck(this);"><label for="Radio_Oppose_${optionStat.index }">反对</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['abstain'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Abstain_${optionStat.index }" value="3" onclick="isCheck(this);"><label for="Radio_Abstain_${optionStat.index }">弃权</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['excellent'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Excellent_${optionStat.index }" value="4" onclick="isCheck(this);"><label for="Radio_Excellent_${optionStat.index }">优</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['good'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Good_${optionStat.index }" value="5" onclick="isCheck(this);"><label for="Radio_Good_${optionStat.index }">良</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['medium'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Medium_${optionStat.index }" value="6" onclick="isCheck(this);"><label for="Radio_Medium_${optionStat.index }">中</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['soso'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Soso_${optionStat.index }" value="7" onclick="isCheck(this);"><label for="Radio_Soso_${optionStat.index }">可</label>
							</s:if>
							<s:if test="electVoteConfig.decisionMap['poor'] != null">
								<input type="radio" name="Radio_${optionStat.index }" id="Radio_Poor_${optionStat.index }" value="8" onclick="isCheck(this);"><label for="Radio_Poor_${optionStat.index }">差</label>
							</s:if>
							</h4>
						</td>
					</tr>
				</table>
		    </div>
		    <div class="cleaner_h10"></div>
		
	</s:iterator>
	<br>
	<s:if test="electVoteConfig.electVote.isAnonymous == 1">
		<div>本次投票为匿名投票</div>
	</s:if>
	<s:else>
		<div>实名投票，请输入姓名：<input id="voter" type="text" size='10'></div>
	</s:else>
	<br>
-------------------------------------------------------------------------------------------
<br><br>
    <div align="center">
	    <a onclick="saveElectVote()" class="button" ><span>&#10003;</span>确认投票</a>
    </div>
	<div class="cleaner"></div>
</div>

<s:if test="electVoteConfig == null">
		<script type="text/javascript">
			$("#NoElectVoteDiv").show();
			$("#templatemo_content").hide();
		</script>
	</s:if>
	<s:else>
		<script type="text/javascript">
			$("#NoElectVoteDiv").hide();
			$("#templatemo_content").show();
		</script>
	</s:else>

</body>
<script type="text/javascript">
	var radioArr = new Array();
	function isCheck(obj){
		var isDifferential = ${electVoteConfig.electVote.isDifferential};
		if (isDifferential == 0) {
			return;
		}
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
	}
	
	//初始化选中的radio
	function initRadioArr(){
		radioArr = new Array();
	
		//可根据实际情况修改radio的范围
		var radios = $("input[id^='Radio_']");
		for(var i=0; i<radios.length; i++){
			if(radios[i].type.toLowerCase()=="radio" && radios[i].checked){
				radioArr.push(radios[i]);
			}
		}
	}
	function saveElectVote(){
		var electVoteResults = new Array();
		var voter = $("#voter").val();
		$("tr[id^='ElectVoteOptionTR']").each(function(){
			var electVoteResult = {};
			var idArray = $(this).find("input[id='ElectVoteResultIdInput']").val().split("_");
			electVoteResult.userId = idArray[0];
			electVoteResult.electVoteId = idArray[1];
			electVoteResult.electVoteOptionId = idArray[2];
			electVoteResult.voterName = voter;
			var electedValue = $(this).find("input[id^='Radio_']:checked").next("label").text();
			electVoteResult.electVoteResult = electedValue;
			electVoteResults.push(electVoteResult);
		});
		
		var isDifferential = ${electVoteConfig.electVote.isDifferential};
		var mayChooseCount = ${electVoteConfig.electVote.mayChooseCount };
		var checkedCount = $("input[id^='Radio_']:checked").length;
		if (isDifferential == 0) {
			if (checkedCount != mayChooseCount) {
				alert("等额投票，请为所有候选项投票！");
				return;
			}
		} else {
			if (checkedCount != mayChooseCount) {
				alert("差额投票，必须投" + mayChooseCount + "张票！");
				return;
			}
		}
		
		var electVoteResultsJsonStr = $.toJSON(electVoteResults);
		$.post('electVoteResultAdd', {electVoteResultsJson: electVoteResultsJsonStr}, function(data) {
			data = eval('('+data+')');
	        if(data.result){
	        	alert('投票成功 ！');
	        	f_back();
	        }else{
	        	alert('投票失败！');
	        }
		});
	}	
</script>
</html>