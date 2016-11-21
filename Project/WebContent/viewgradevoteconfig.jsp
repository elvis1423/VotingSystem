<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/web_form.css">
<style type="text/css">
    table{ border-collapse:collapse; width:100%; height:100%;}
	table td{ border-collapse:collapse; border:solid 1px #000;}
</style>
<script src="scripts/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	function deleteVote(id){
		var flag = confirm("确认删除？");
		if(flag){
		$.post('deletegradevote?gradeVoteId='+id, function(data) {
			data = eval('('+data+')');
	        if(data == "1"){
	        	alert('删除成功 ！');
	        	location.reload();
	        }else{
	        	alert('删除失败！');
	        } 
		});
		} else return false;
	}

	function depublish(DepublishButton){
		var voteName = $(DepublishButton).parent().prev().prev().prev().text();
		var flag = confirm("确定要撤回：" + voteName + "?");
		if(flag){
			var $tr = $(DepublishButton).parent().parent();
			//删除, 使用 ajax 的方式
			var url = $(DepublishButton).val();
			var args = {"time":new Date()};
			$.post(url, args, function(data){
				//若 data 的返回值为 1, 则提示 删除成功, 且把当前行删除
				if(data == "1"){
					alert("撤回成功!");
					$(DepublishButton).hide();
					$(DepublishButton).next().show();
				}else if (date == "0"){
					//若 data 的返回值不是 1, 提示删除失败. 
					alert("撤回失败!");
				}
			});	
		}
		//取消超链接的默认行为
		return false;
	}
	
	function publish(PublishButton) {
		var voteName = $(PublishButton).parent().prev().prev().prev().text();
		var flag = confirm("确定要发布：" + voteName + "?");
		if(flag){
			var $tr = $(PublishButton).parent().parent();
			//删除, 使用 ajax 的方式
			var url = $(PublishButton).val();
			var args = {"time":new Date()};
			$.post(url, args, function(data){
				//若 data 的返回值为 1, 则提示 删除成功, 且把当前行删除
				if(data == "1"){
					alert("发布成功!");
					$(PublishButton).hide();
					$(PublishButton).prev().show();
				}else if (date == "0"){
					//若 data 的返回值不是 1, 提示删除失败. 
					alert("发布失败!");
				}
			});	
		}
		//取消超链接的默认行为
		return false;
	}

</script>
</head>
<body>
<div align="center"><input type="image" style="width:145px;height:50px;" src="res/button_pic/votelist.png"></div>
<div  id="site_content">
<div id="NoResultDiv" style="display:none">没有任何记录</div>
	<s:if test="allGradeVoteConfigs == null || allGradeVoteConfigs.size==0">
		<script type="text/javascript">
			$("#NoResultDiv").show();
		</script>
	</s:if>
	<s:else>
		<table id="GradeVoteConfigTable">
			<tr align="center">
				<td><gg>评分名称</gg></td>
				<td><gg>开始日期</gg></td>
				<td><gg>截止日期</gg></td>
				<td><gg>动作</gg></td>
			</tr>
			<s:iterator value="allGradeVoteConfigs" id="gradeVoteConfig" var="gradeVoteConfig">
			<s:if test="isActive == 1 || isActive == 0">
				<tr >
					<td><s:property value="voteName"></s:property></td>
					<td><s:property value="startDateStr"></s:property></td>
					<td><s:property value="completeDateStr"></s:property></td>
					<s:if test="isActive == 1">
						<td align="center">
							<button value="depublishgradevote?gradeVoteId=${id}" onclick="depublish(this)">撤回</button>
							<button value="publishgradevote?gradeVoteId=${id}" onclick="publish(this)" style="display:none">发布</button>
							<button onclick="deleteVote(${id})">删除</button>
							<input type="hidden" value="${voteName }">
						</td>
					</s:if>
					<s:else>
						<td align="center">
							<button value="depublishgradevote?gradeVoteId=${id}" onclick="depublish(this)" style="display:none">撤回</button>
							<button value="publishgradevote?gradeVoteId=${id}" onclick="publish(this)">发布</button>
							<button onclick="deleteVote(${id})">删除</button>
							<input type="hidden" value="${voteName }"></td>
					</s:else>
				</tr>
			</s:if>
			</s:iterator>
		</table>
	</s:else>
</div>
</body>
</html>