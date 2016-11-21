<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting System</title>
<link type="text/css" rel="stylesheet" href="css/web_form.css">
</head>
<body>
<div align="center"><input type="image" style="width:145px;height:50px;" src="res/button_pic/usercreate.png"></div>
<div align="center" id="site_content">

	<form name="CreateUser" method="post" action="createForm">
		<table>
			<tr>
				<td>注册用户:<input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>输入密码:<input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>确认密码:<input type="password" name="identifypassword" id="identifypassword"></td>
			</tr>
			<tr>
				<td><input type="submit" value="创建"> 
				    <input type="reset" value="清空"></td>
			</tr>
		</table>
	</form>

</div>
</body>