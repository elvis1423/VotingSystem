<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/vs-gradevote-table.css">
<link type="text/css" rel="stylesheet" href="css/gradevoteresult-div.css">
<link type="text/css" rel="stylesheet" href="css/web_form.css">
</head>
<body>
<div align="center"><input type="image" style="width:145px;height:50px;" src="res/button_pic/electresult.png"></div>
<div  id="site_content">
<div id="lightshow" class="div_content">
	<div id="messagebox" class="div_main">
		<!-- table>
			<tr>
				<td colspan='2' style='text-align:center;font-weight:bold'>2014年先进课题组评选</td>
			</tr>
			<tr>
				<td>评委</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>候选人A</td>
							<td class='two_only_right'>候选人B</td>
							<td class='two_no_border'>候选人C</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>评委1</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>88</td>
							<td class='two_only_right'>90</td>
							<td class='two_no_border'>96</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>评委2</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>92</td>
							<td class='two_only_right'>93</td>
							<td class='two_no_border'>90</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>评委3</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>80</td>
							<td class='two_only_right'>85</td>
							<td class='two_no_border'>83</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>评委4</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>84</td>
							<td class='two_only_right'>87</td>
							<td class='two_no_border'>90</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>合计</td>
				<td>
					<table>
						<tr>
							<td class='two_only_right'>86</td>
							<td class='two_only_right'>88.5</td>
							<td class='two_no_border'>90</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td  colspan='2' align="center">
					<a href="" id="goback" class="li_link"><li class="div_li" onClick="closedivshow()">确 定</li></a>
				</td>
			</tr>
		</table-->
	</div>
</div>
<div id="fadeshow" class="div_overlay"></div>

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
				<td><gg>评分日期</gg></td>
				<td><gg>动作</gg></td>
			</tr>
			<s:iterator value="allGradeVoteConfigs" id="gradeVoteConfig" var="gradeVoteConfig">
				<tr>
					<td><s:property value="voteName"></s:property></td>
					<td><s:property value="completeDateStr"></s:property></td>
					<td align="center">
						<button value="viewgradevotedetail?gradeVoteId=${id}" onclick="opendivshow('800','javascript:closedivshow()',this)">查看详情</button>
						<input type="hidden" value="${voteName }"></td>
				</tr>
			</s:iterator>
		</table>
	</s:else>
</div>
</body>
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/gradevoteresult.js" type="text/javascript"></script>
</html>