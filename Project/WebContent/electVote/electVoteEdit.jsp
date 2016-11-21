<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="../css/web_form.css">
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css">
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/jquery-ui.js" type="text/javascript"></script>
<script src="scripts/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<script src="scripts/jquery.json-2.4.min.js" type="text/javascript"></script>

</head>
<body>
<div align="center"><H2>修改投票</H2></div>
<div id="vote">
	<input type="hidden" id="vote_id" value="${electVoteConfig.electVote.id}"/>
	<label>名称：</label><input id="vote_name" name="voteName" type="text" style="width: 150px;" value="${electVoteConfig.electVote.name}"/>
	<label>内容：</label><input id="vote_content" name="voteContent" type="text" style="width: 150px;" value="${electVoteConfig.electVote.content}"/>
	<input type="radio" name="DifferentialRadio" id="NonDifferential" value="0">等额投票
	<input type="radio" name="DifferentialRadio" id="Differential" value="1">差额投票
	<label id="may_choose_count_label">最多可以投几票：</label><input id="may_choose_count" name="mayChooseCount" type="text" style="width: 150px; " value="${electVoteConfig.electVote.mayChooseCount}"/><br>
	
	<label>决定：</label>
				<s:if test="electVoteConfig.decisionMap['agree'] != null">
					<input name="decision" class="decisions" type="checkbox" value="agree" cname="同意" checked/>同意&nbsp;&nbsp;&nbsp;
				</s:if>
				<s:else>
					<input name="decision" class="decisions" type="checkbox" value="agree" cname="同意" />同意&nbsp;&nbsp;&nbsp;
				</s:else>
				<s:if test="electVoteConfig.decisionMap['oppose'] != null">
					<input name="decision" class="decisions" type="checkbox" value="oppose" cname="反对" checked/>反对&nbsp;&nbsp;&nbsp;
				</s:if>
				<s:else>
					<input name="decision" class="decisions" type="checkbox" value="oppose" cname="反对" />反对&nbsp;&nbsp;&nbsp;
				</s:else>
				<s:if test="electVoteConfig.decisionMap['abstain'] != null">
					<input name="decision" class="decisions" type="checkbox" value="abstain" cname="弃权" checked/>弃权
				</s:if>
				<s:else>
					<input name="decision" class="decisions" type="checkbox" value="abstain" cname="弃权"  />弃权
				</s:else>
</div>
<div style="height: 40px;"></div>
<div id = "options">
	<input type="button" value="添加投票选项" onclick="add_option()"/> 
	
	<s:iterator value="electVoteConfig.options" var="option" status="optionStat">
		<div id="option_${optionStat.index }" order="${optionStat.index }" class="m_option">
			<label>投票选项</label>
			<label>名称：</label><input id="option_name_${optionStat.index }" type="text" style="width: 150px;" value="${name }"/>
			<label>内容：</label><input id="option_content_${optionStat.index }" type="text" style="width: 300px;" value="${content }"/>&nbsp;&nbsp;&nbsp;
			<input type="button" value="删除" onclick="del_option(${optionStat.index })"/>
		</div>
	</s:iterator>
</div>

<div style="height: 40px;"></div>
<div id = "save_div">
	<form id="saveForm" name="saveForm" action="" method="post">
		<input type="button" value="保存" onclick="save_info()"/>
		<input type="button" value="返回" onclick="f_back()"/>
	</form>
</div>
</body>


<script type="text/javascript">
	$(document).ready(function(){
		var isDifferential = "${electVoteConfig.electVote.isDifferential}";
		if (isDifferential == "0") {
			$("#NonDifferential").attr("checked", "checked");
			$("#may_choose_count_label").hide();
			$("#may_choose_count").hide();
		} else {
			$("#Differential").attr("checked", "checked");
			 $("#may_choose_count_label").show();
			 $("#may_choose_count").show();
		}
		
		$("[name='DifferentialRadio']").change(function() {
			 showCount();
		});
	}); 
	function showCount(){
	 switch($("[name=DifferentialRadio]:checked").attr("id")){
	  case "NonDifferential":
		   $("#may_choose_count_label").hide();
		   $("#may_choose_count").hide();
	   break;
	  case "Differential":
		  $("#may_choose_count_label").show();
		  $("#may_choose_count").show();
	   break;
	  default:
	   break;
	 }
	}

	function add_option(){
		var infoLen = $(".m_option").length;
		var last = 0;
		$(".m_option").each(function(i,e){
			var order = $(e).attr('order');
			if((infoLen-1) == i){
				last = parseInt(order)+1;
			}
		});
		
		var optionDiv = '<div id="option_'+last+'" order="'+last+'" class="m_option">'
						+'<label>投票选项</label>&nbsp;'
						+'<label>名称：</label><input id="option_name_'+last+'" type="text" style="width: 150px;"/>&nbsp;'
						+'<label>内容：</label><input id="option_content_'+last+'" type="text" style="width: 300px;"/>&nbsp;&nbsp;&nbsp;&nbsp;'
						+'<input type="button" value="删除" onclick="del_option('+last+')">'
						+'</div>';
						
		$('#options').append(optionDiv);
	}
	
	function del_option(order){
		$("#option_"+order).remove();
	}
	
	function save_info(){
		
		var vote = new Object();
		$(vote).attr('id',$('#vote_id').val());
		$(vote).attr('name',$('#vote_name').val());
		$(vote).attr('content',$('#vote_content').val());
		if ($("[name=DifferentialRadio]:checked").attr("id") == "Differential") {
			$(vote).attr('isDifferential', 1);
			$(vote).attr('mayChooseCount',$('#may_choose_count').val());
		} else {
			$(vote).attr('isDifferential', 0);
			var all_option_count = $(".m_option").length;
			$(vote).attr('mayChooseCount',all_option_count);
		}
		$(vote).attr('isPublish','0');
		
		var decisions = new Array();
		$(".decisions").each(function(i,e){
			if($(e).is(':checked')){
				var decision = new Object();
				$(decision).attr('cName',$(e).attr('cname'));
				$(decision).attr('eName',$(e).val());
				decisions.push(decision);
			}
		});
		$(vote).attr('decisions',decisions);
		
		var infoLen = $(".m_option").length;
		if(infoLen < 1){
			alert("请至少添加一个投票选项");
			return;
		}
		
		var options = new Array();
		$(".m_option").each(function(i,e){
			var option = new Object();
			var order = $(e).attr('order');
			$(option).attr('name',$('#option_name_'+order).val());
			$(option).attr('content',$('#option_content_'+order).val());
			
			options.push(option);
		});
		$(vote).attr('options',options);
		
		var voteJsonStr = $.toJSON(vote);
		
		$.post('electVoteAdd', {voteJson : voteJsonStr}, function(data) {
			data = eval('('+data+')');
	        if(data.result){
	        	alert('修改投票成功 ！');
	        	f_back();
	        }else{
	        	alert('修改投票失败！');
	        } 
		});
		
	}
	
	function f_back(){
		location.href='electVoteList.action';
	}
</script>
</html>