<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/web_form.css">
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css">
<script src="scripts/jquery.js" type="text/javascript"></script>

<script type="text/javascript">
function del_vote(id){
	var flag = confirm("确认删除？");
	if(flag){
	$.post('electVoteDel?voteId='+id, function(data) {
		data = eval('('+data+')');
        if(data.result){
        	alert('删除投票成功 ！');
        	location.reload();
        }else{
        	alert('删除投票失败！');
        } 
	});
	} else return false;
}

function edit_vote(id){
	location.href='electVoteEdit.action?id='+id;
}

function depublish(DepublishButton){
	var voteName = $(DepublishButton).parent().prev().prev().text();
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
	var voteName = $(PublishButton).parent().prev().prev().text();
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

<style type="text/css">
    table
    {
        border-collapse: collapse;
        border: none;
        width: 100%;
    }
    td
    {
    	border-collapse:collapse;
        border: solid #000 1px;
    }
</style>
</head>
<body>
<div align="center"><input type="image" style="width:145px;height:50px;" src="res/button_pic/electlist.png"></div>
<div  id="site_content">
<s:if test="electVotes == null || electVotes.size == 0">
还没有创建投票
</s:if>
<s:else>
<table>
	<tr align="center">
		<td><gg>序号</gg></td>
		<td><gg>投票名称</gg></td>
		<td><gg>创建时间</gg></td>
		<td><gg>动作</gg></td>
	</tr>
	<s:iterator value="electVotes" var="electVote" status="electVoteStat">
		<tr>
			<td align="center"><s:property value="#electVoteStat.index+1"/>
			<td><s:property value="name"/>
			<td><s:property value="createTimeStr"/>
			<s:if test="isPublish == 1">
				<td align="center">
					<button value="depublishelectvote?voteId=${id}" onclick="depublish(this)">撤回</button>
					<button value="publishelectvote?voteId=${id}" onclick="publish(this)" style="display:none">发布</button>
					<button onclick="del_vote(${id})">删除</button>
				</td>
			</s:if>
			<s:else>
				<td align="center">
					<button value="depublishelectvote?voteId=${id}" onclick="depublish(this)" style="display:none">撤回</button>
					<button value="publishelectvote?voteId=${id}" onclick="publish(this)">发布</button>
					<button onclick="del_vote(${id})">删除</button>
				</td>
			</s:else>
		</tr>
	</s:iterator>
	
</table>
</s:else>
</div>
</body>
</html>