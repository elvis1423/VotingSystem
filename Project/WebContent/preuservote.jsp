<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/web_form.css">
<style type="text/css">
</style>
<link rel="stylesheet" href="res/preuservote_css/zzsc.css" type="text/css" />

</head>
<body>
<h2>欢迎登录，请选择：</h2>
<div  id="site_content">
<br>
<s:if test="#parameters.hasElectVote">
<s:form action="userelectvote" method="post" target="main">
	<a href="userelectvote.action" class="button" ><span>&#10003;</span>投票</a>
</s:form>
</s:if>
<br>
<s:if test="#parameters.hasGradeVote">
<s:form action="usergradevote" method="post" target="main">
	<a href="usergradevote.action" class="button" ><span>&#10003;</span>评分</a>
</s:form>
</s:if>
<div style="text-align:center;clear:both"></div>
</div>
</body>
</html>