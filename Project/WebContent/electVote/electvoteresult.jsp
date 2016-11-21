<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/web_form.css">
<link type="text/css" rel="stylesheet" href="css/vs-gradevote-table.css">
<link type="text/css" rel="stylesheet" href="css/gradevoteresult-div.css">
</head>
<body>
<div align="center"><input type="image" style="width:145px;height:50px;" src="res/button_pic/electresult.png"></div>
<div  id="site_content">
<div id="lightshow" class="div_content">
	<div id="messagebox" class="div_main">
		<!-- 
		<table>
			<tr>
				<td colspan='2' style='text-align:center;font-weight:bold'>教授资格投票</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>张三</td>
							<td class='two_only_right'>李四</td>
							<td class='two_only_right'>王五</td>
							<td class='two_no_border'>赵六</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>同意</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>2</td>
							<td class='two_only_right'>1</td>
							<td class='two_only_right'>2</td>
							<td class='two_no_border'>1</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>反对</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>0</td>
							<td class='two_only_right'>2</td>
							<td class='two_only_right'>1</td>
							<td class='two_no_border'>1</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>弃权</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>1</td>
							<td class='two_only_right'>0</td>
							<td class='two_only_right'>0</td>
							<td class='two_no_border'>1</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>结果</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>通过</td>
							<td class='two_only_right'>未通过</td>
							<td class='two_only_right'>通过</td>
							<td class='two_no_border'>未通过</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td  colspan='2' align="center">
					<a href="" id="goback" class="li_link"><li class="div_li" onClick="closedivshow()">确 定</li></a>
				</td>
			</tr>
		</table>
			 -->
	</div>
</div>
<div id="fadeshow" class="div_overlay"></div>

<div id="NoResultDiv" style="display:none">没有任何记录</div>
<s:if test="electVotes == null || electVotes.size==0">
	<script type="text/javascript">
		$("#NoResultDiv").show();
	</script>
</s:if>
<s:else>
	<table>
		<tr align="center">
			<td><gg>投票名称</gg></td>
			<td><gg>创建时间</gg></td>
			<td><gg>类型</gg></td>
			<td><gg>动作</gg></td>
		</tr>
		<s:iterator value="electVotes" id="electVote" var="electVote">
			<tr>
				<td><s:property value="name"></s:property></td>
				<td><s:property value="createTimeStr"></s:property></td>
				<s:if test="isDifferential == 1">
					<td>差额投票</td>
				</s:if>
				<s:else>
					<td>等额投票</td>
				</s:else>
				<td align="center"><button value="viewelectvotedetail?electVoteId=${id}" onclick="opendivshow('1000','javascript:closedivshow()',this)">查看详情</button>
					<input type="hidden" value="${name }">
				</td>
			</tr>
		</s:iterator>
	</table>
</s:else>
 </div>
</body>
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/electvoteresult.js" type="text/javascript"></script>
</html>